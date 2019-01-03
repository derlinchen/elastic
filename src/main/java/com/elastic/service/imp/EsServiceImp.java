package com.elastic.service.imp;

import javax.annotation.Resource;

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
	
	
}
