package com.elastic.service;

import java.util.List;

import com.elastic.bean.Stu;


public interface EsService {
	
	public <T> void createIndex(Class<T> clazz);
	
	public void createDocument(Stu stu);
	
	public Stu getByStuId(String stuId);

	public void searchAll();

	public List<Stu> queryAll();
}
