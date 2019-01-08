package com.elastic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

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
	
	// endregion Repository Methods
	
	// region Template Methods

	public void save(String indexname, String type, Object obj);
	
	public void delete(String indexname, String type, String id);
	
	public void update(String indexname, String type, Object obj);

	public <T> List<T> findBySort(Class<T> clazz);
	
	// endregion Template Methods

}
