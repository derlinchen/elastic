package com.elastic.service;

import com.elastic.bean.Stu;


public interface EsService {
	
	public <T> void createIndex(Class<T> clazz);
	
	public void createDocument(Stu stu);
	
	public Stu getByStuId(String stuId);
}
