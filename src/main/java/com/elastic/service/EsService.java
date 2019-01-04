package com.elastic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.elastic.bean.Book;
import com.elastic.bean.Stu;


public interface EsService {
	
	// region Repository Methods
	
	public void save(Stu stu);
	
	public Stu findById(Long id);
	
	public List<Stu> getByStuId(String stuId);
	
	public List<Stu> getByStuName(String stuName);
	
	public List<Stu> getPageByStuName(String stuName, Pageable pageable);

	public List<Stu> findAll();
	
	public void delete(Stu item);

	public void save(Book book);
	
	public <T> void  update(Object obj, Class<T> clazz);
	
	public void saveObject(String indexname, String type, Object obj);

	public <T> List<T> findBySort(Class<T> clazz);
	
	// endregion Repository Methods

}
