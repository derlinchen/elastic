package com.elastic.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.elastic.bean.Stu;
import com.elastic.repository.StudentRepository;
import com.elastic.service.EsService;

@Service("esService ")
public class EsServiceImp implements EsService {
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Resource
	private StudentRepository studentRepository;

	@Override
	public <T> void createIndex(Class<T> clazz) {
		elasticsearchTemplate.createIndex(clazz);
	}

	@Override
	public  void createDocument(Stu stu) {
		studentRepository.save(stu);
	}
	
	@Override
	public Stu getByStuId(String stuId) {
		return studentRepository.getByStuId(stuId);
	}

	@Override
	public void searchAll() {
		Client client = elasticsearchTemplate.getClient();
		SearchRequestBuilder srb = client.prepareSearch("stu").setTypes("doc");
		SearchResponse sr = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet(); // 查询所有
		SearchHits hits = sr.getHits();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (SearchHit hit : hits) {
			Map<String, Object> source = hit.getSourceAsMap();
			list.add(source);
			System.out.println(hit.getSourceAsString());
		}
		
	}

	@Override
	public List<Stu> queryAll() {
		List<Stu> list = new ArrayList<Stu>();
		Iterable<Stu> iterable = studentRepository.findAll();
		for (Stu item : iterable) {
			list.add(item);
		}
		return list;
	}
	
	
}
