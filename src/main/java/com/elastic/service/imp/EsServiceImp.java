package com.elastic.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.elastic.bean.Stu;
import com.elastic.repository.StudentRepository;
import com.elastic.service.EsService;

@Service("esService ")
public class EsServiceImp implements EsService {
	
	// region Repository Methods
	
	@Resource
	private StudentRepository repository;
	
	@Override
	public  void save(Stu stu) {
		repository.save(stu);
	}
	
	@Override
	public Stu findById(Long id) {
		Stu rtn = null;
		Optional<Stu> stu = repository.findById(id);
		if(stu.isPresent()){
			rtn = stu.get();
		}
		return rtn;
	}
	
	@Override
	public List<Stu> getByStuId(String stuId) {
		return repository.getByStuId(stuId);
	}
	
	@Override
	public List<Stu> getByStuName(String stuName) {
		return repository.getListByStuName(stuName);
	}
	
	@Override
	public List<Stu> findAll() {
		List<Stu> list = new ArrayList<Stu>();
		Iterable<Stu> iterable = repository.findAll();
		for (Stu item : iterable) {
			list.add(item);
		}
		return list;
	}

	@Override
	public void delete(Stu item) {
		repository.delete(item);
	}
	
	// endregion Repository Methods
	
}
