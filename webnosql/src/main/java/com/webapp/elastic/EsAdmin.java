package com.webapp.elastic;

import java.net.InetSocketAddress;
import java.util.Arrays;

import org.elasticsearch.action.admin.indices.alias.IndicesAliasesResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.webapp.elastic.EsUtils.BuildMapping;

public class EsAdmin {

	private static final Logger logger = LoggerFactory.getLogger(EsAdmin.class);
	private static EsAdmin esAdmin = null;
	private TransportClient client = null;

	public static EsAdmin getEsAdmin(String host, int port){
		return getEsAdmin(new InetSocketAddress(host, port));
	}

	public static EsAdmin getEsAdmin(InetSocketAddress... inetAdd){
		if (esAdmin == null) {
			synchronized (EsAdmin.class) {
				esAdmin = new EsAdmin(inetAdd);
			}
		}
		return esAdmin;
	}

	private EsAdmin(InetSocketAddress... inetAdd) {
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch_test")
				.put("client.transport.sniff", true).build();
		client = TransportClient.builder().settings(settings).build();
		Arrays.asList(inetAdd).forEach(addr->{
			client = client.addTransportAddress(new InetSocketTransportAddress(addr));
		});
	}

	public Client getClient() {
		return client;
	}

	private IndicesAdminClient getIndices(){
		return getClient().admin().indices();
	}


	public boolean hasIndex(String index){
		return getIndices().prepareExists(index).execute().actionGet().isExists();
	}
	public boolean hasType(String type){
		return getIndices().prepareTypesExists(type).execute().actionGet().isExists();
	}
	public boolean hasAlias(String alias){
		return getIndices().prepareAliasesExist(alias).execute().actionGet().isExists();
	}

	public void viewIndexs(){
		GetIndexResponse result = getIndices().prepareGetIndex().execute().actionGet();
		String[] indices = result.getIndices();
		Arrays.asList(indices).forEach(index->{
			System.out.println("--> " + index);
		});
	}
	public void viewMapping(String index){
		GetMappingsResponse result = getIndices().prepareGetMappings(index).execute().actionGet();
		result.mappings().forEach(map->{
//			System.out.println("sort -> " + map.index);
			System.out.println("index -> " + map.key);
			map.value.forEach(prop->{
				try {
					String mapping = prop.value.source().string();
					System.out.println(JSON.toJSONString(JSON.parse(mapping), true));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		});
	}
	public boolean addAlias(String index, String alias){
		IndicesAliasesResponse result = getIndices().prepareAliases().addAlias(index, alias).execute().actionGet();
		return result.isAcknowledged();
	}
	public boolean addIndex(String index){
		if(hasIndex(index)){
			logger.warn("index {} already exists", index);
			return true;
		}
		CreateIndexResponse result = getIndices().prepareCreate(index).execute().actionGet();
		return result.isAcknowledged();
	}
	public boolean delIndex(String index){
		if(!hasIndex(index)){
			logger.warn("index {} does not exist", index);
			return true;
		}
		DeleteIndexResponse result = getIndices().prepareDelete(index).execute().actionGet();
		return result.isAcknowledged();
	}
	public boolean addMapping(String index, String type, XContentBuilder xct){
		if(!hasIndex(index)){
			addIndex(index);
		}
		PutMappingRequest source = Requests.putMappingRequest(index).type(type).source(xct);
		PutMappingResponse result = getIndices().putMapping(source).actionGet();
		return result.isAcknowledged();
	}
	public boolean addMapping(String index, BuildMapping mapping){
		return addMapping(index, mapping.getType(), mapping.toXCBuilder());
	}

	public static void main(String[] args) throws Exception {
		EsAdmin admin = EsAdmin.getEsAdmin("10.20.88.21", 9300);

//		BuildMapping done = EsUtils.buildSource().type("new_index")
//			.name("title").store("yes").type("string").done()
//			.name("name").store("no").type("string").done();


//		boolean hasAlias = admin.hasAlias("ok");
//		System.out.println(hasAlias);
		admin.viewMapping("pages");
//		System.out.println(admin.addIndex("pages"));


	}

}
