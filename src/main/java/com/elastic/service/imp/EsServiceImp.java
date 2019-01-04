package com.elastic.service.imp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.elastic.bean.Book;
import com.elastic.bean.Stu;
import com.elastic.repository.StudentRepository;
import com.elastic.service.EsService;

@Service("esService ")
public class EsServiceImp implements EsService {

	// region Repository Methods

	@Resource
	private StudentRepository repository;

	@Override
	public void save(Stu stu) {
		repository.save(stu);
	}

	@Override
	public Stu findById(Long id) {
		Stu rtn = null;
		Optional<Stu> stu = repository.findById(id);
		if (stu.isPresent()) {
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
	public List<Stu> getPageByStuName(String stuName, Pageable pageable) {
		Page<Stu> stus = repository.getPageByStuName(stuName, pageable);
		return stus.getContent();
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

	// region Template Methods

	@Autowired
	private ElasticsearchTemplate template;

	@Override
	public void save(Book book) {
		IndexQuery indexquery = new IndexQueryBuilder()
				.withIndexName("library").withType("book")
				.withId(book.getId() + "").withObject(book).build();
		template.index(indexquery);
	}

	@Override
	public void saveObject(String indexname, String type, Object obj) {
		IndexQuery indexquery = new IndexQueryBuilder()
				.withIndexName(indexname).withType(type).withObject(obj)
				.build();
		template.index(indexquery);
	}
	
	@Override
	public <T> void update(Object obj, Class<T> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		
		UpdateRequest updateRequest = new UpdateRequest();
		try {
			XContentBuilder xcb = XContentFactory.jsonBuilder().startObject();
			
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(obj);
				if(value != null){
					xcb.field(field.getName(), value);
				}
			}
			xcb.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		template.update(updateQuery);
	}
	
//	@Override
//	public void update(Object obj, Class<T> clazz) {
//		UpdateRequest updateRequest = new UpdateRequest();
//		try {
//			updateRequest
//			.doc(XContentFactory.jsonBuilder()
//			        .startObject()
//			        .field("tag", "1")//指定更新的字段和更新的值，可指定多个字段
//			        .endObject());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		UpdateQuery updateQuery = new UpdateQueryBuilder()
//        .withIndexName("conversation_abcde").withType("conversation_content_abcde")//声明索引名和索引类型
//        .withClass(String.class)
//        .withId(String.valueOf(id))//声明文档id
//        .withUpdateRequest(updateRequest)
//        .build();
//		template.update(updateQuery);
//	}

	@Override
	public <T> List<T> findBySort(Class<T> clazz) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(0, 10, sort);
		SearchQuery searchquery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.matchAllQuery())
				.withPageable(pageable).build();
		Page<T> items = template.queryForPage(searchquery, clazz);
		return items.getContent();
	}

	// endregion Template Methods

}
