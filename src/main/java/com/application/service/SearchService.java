package com.application.service;

import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.stereotype.Service;

import com.application.Elasticsearch.ElasticsearchConnect;
import com.application.vo.Devices;
import com.google.gson.Gson;

@Service
public class SearchService implements SearchServiceImpl {

	public void searchDevices() {
		TransportClient client = ElasticsearchConnect.getClient();
		GetResponse getResponse = client.prepareGet("devices", "device", "1").setOperationThreaded(false).get();
		Map<String, Object> output = getResponse.getSource();
	
		Gson gson = new Gson();
		Devices[] devices = gson.fromJson((String) output.get("devices"), Devices[].class);
		System.out.println(devices);
	}

	public static void main(String[] args) {
		SearchService searchService = new SearchService();
		searchService.searchDevices();
		
	}

}
