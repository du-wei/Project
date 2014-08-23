package com.webapp.elasticsearch;

import java.util.Collections;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;



public class ESUtils {
    
    
    public void search() {
        
        SearchResponse searchResp = getClient().prepareSearch(AppConstant.ES_INDEX)
            .setTypes(AppConstant.ES_TYPE)
            .setQuery(QueryBuilders.termQuery("title", "音乐"))
            .setFrom(0).setSize(10)
            .setExplain(true).execute().actionGet();
//        
        SearchHits hits = searchResp.getHits();
    }
    
    public Client getClient() {
        //集群配置
        Settings settings = ImmutableSettings.settingsBuilder()   
                .put("cluster.name", AppConstant.ES_CLUSTERNAME)
                //.put("client.transport.sniff", true)o
                .build(); 
     
        Client client = new TransportClient(settings)
            .addTransportAddress(new InetSocketTransportAddress(AppConstant.ES_HOSTNAME, AppConstant.ES_PORT));
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
