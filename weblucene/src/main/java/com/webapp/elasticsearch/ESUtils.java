package com.webapp.elasticsearch;

import java.util.Collections;
import java.util.List;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ESUtils {

    private static Client client;

    public static void main(String[] args) {
	    ESUtils esUtils = new ESUtils();
	    esUtils.top();
    }
    
    public static JSONArray hits2Json(SearchHit[] hits) {
        JSONArray array = new JSONArray();
        for(SearchHit hit : hits){
            JSONObject obj = JSONObject.parseObject(hit.getSourceAsString());
            System.out.println(JSON.toJSONString(obj));
            array.add(obj);
        }
        return array;
    }
    
    public void top() {
    	TermsBuilder field = AggregationBuilders.terms("terms").field("platform");
    	SearchRequestBuilder reqBuilder = getClient().prepareSearch(EsConstant.ES_INDEX).setTypes(EsConstant.ES_TYPE)
    		.setQuery(QueryBuilders.matchAllQuery())
    		.addAggregation(AggregationBuilders.max("max").field("progress"))
//    		.addSort("progress", SortOrder.DESC)
    		.setExplain(true);
    	
    	SearchResponse actionGet = reqBuilder.execute().actionGet();
    	
    	SearchHit[] hits = actionGet.getHits().getHits();
    	
    	Max max = actionGet.getAggregations().get("max");
    	System.out.println(max.getName() +"-"+max.getValue());
    	
    	
    	JSONArray hits2Json = hits2Json(hits);
    	
    }

    public boolean delById(String index, String type, String id){
        DeleteResponse resp = getClient().prepareDelete(index, type, id).execute().actionGet();
        return resp.isFound();
    }
    
    public boolean addIndex(String json, String index, String type) {
        IndexResponse response = getClient().prepareIndex(index, type)
            .setSource(json).execute().actionGet();
        return response.isCreated();
    }

    public boolean addIndex(JSONObject json, String index, String type){
        return addIndex(json.toString(), index, type);
    }
    
    public boolean addBulkIndex(JSONArray jsons, String index, String type) {
    	return bulkIndex(buildAddBuilder(jsons, index, type));
    }
    
    public boolean updBulkIndex(JSONArray jsons, String index, String type) {
    	return bulkIndex(buildUpdBuilder(jsons, index, type));
    }
    
    public boolean bulkIndex(BulkRequestBuilder builder) {
        if(builder.numberOfActions() <= 0) return true;

        BulkResponse bulkResp = builder.execute().actionGet();
        if(bulkResp.hasFailures()){
            BulkItemResponse[] items = bulkResp.getItems();
            for(BulkItemResponse item : items){
            	System.out.println(item.getFailureMessage());
            }
            return false;
        }
        return true;
    }
    
    private BulkRequestBuilder buildAddBuilder(JSONArray jsons, String index, String type) {
        BulkRequestBuilder bulk = getClient().prepareBulk();
        for(int i=0; i<jsons.size(); i++){
            JSONObject json = jsons.getJSONObject(i);
            IndexRequestBuilder builder = getClient().prepareIndex(index, type).setSource(json.toString());
            bulk.add(builder);
        }
        return bulk;
    }
    
    private BulkRequestBuilder buildUpdBuilder(JSONArray jsons, String index, String type){
        BulkRequestBuilder bulk = client.prepareBulk();
        for(int i=0; i<jsons.size(); i++){
            JSONObject json = jsons.getJSONObject(i);
            UpdateRequestBuilder builder = client.prepareUpdate(index, type, json.getString("_id")).setDoc(json.toString());
            bulk.add(builder);
        }
        return bulk;
    }
    
    public void close(){
    	if (client != null) client.close();
    }
    
    @SuppressWarnings("resource")
    public Client getClient() {
        if (client != null) return client;
        // 集群配置
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", EsConstant.ES_CLUSTERNAME)
                // .put("client.transport.sniff", true)
                 .build();

        client = new TransportClient(settings)
        	.addTransportAddress(new InetSocketTransportAddress(EsConstant.ES_HOSTNAME, EsConstant.ES_PORT));
        return client;
    }

    public List<String> getSugList(SuggestResponse sugResp, String name) {
        List<String> result = Collections.emptyList();
        CompletionSuggestion compSug = sugResp.getSuggest().getSuggestion(name);
        List<CompletionSuggestion.Entry> entries = compSug.getEntries();

        for(CompletionSuggestion.Entry entry : entries){
            List<CompletionSuggestion.Entry.Option> options = entry.getOptions();
            for(CompletionSuggestion.Entry.Option option : options){
                String temp = String.format("-->%s-->%s-->%s", option.getText(),
                    option.getScore(), option.getPayloadAsMap().values().toString());
                System.out.println(temp);
                result.add(option.getText().toString());
            }
        }
        return result;
    }

}
