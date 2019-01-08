package com.elastic.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elastic.bean.Book;
import com.elastic.bean.Stu;
import com.elastic.service.EsService;

@Controller
@RequestMapping("/es")
public class EsController {
	
	@Resource
	private EsService esService;
	
	// region Repository Methods
	
	@RequestMapping("/save")
	public void save(){
		String[] names = {"诸葛亮","司马懿","典韦","马超","关羽","孙权","周瑜","司马懿"};
		for(int i = 0; i < names.length; i++){
			Stu st = new Stu(new Long((i+1)), "00" + (i + 1), names[i], new Date());
			esService.save(st);
		}
	}
	
	@RequestMapping("/find")
	public void find(){
		List<Stu> sts = esService.getByStuId("006");
		List<Stu> stus = esService.getByStuName("司马懿");
		List<Stu> lists = esService.findAll();
		// 分页参数，排序
		Sort sort = new Sort(Sort.Direction.ASC, "id");
		Pageable pageable = PageRequest.of(0, 2, sort);
		List<Stu> listpages = esService.getPageByStuName("司马懿", pageable);
		
		System.out.println("bystuid查询");
		for (Stu item : sts) {
			System.out.println(item.getStuId() + "===" + item.getStuName());
		}
		System.out.println("----------------------");
		
		System.out.println("bystuname 查询");
		for (Stu item : stus) {
			System.out.println(item.getStuId() + "===" + item.getStuName());
		}
		System.out.println("----------------------");
		
		System.out.println("findall 查询");
		for (Stu item : lists) {
			System.out.println(item.getStuId() + "===" + item.getStuName());
		}
		System.out.println("----------------------");
		
		System.out.println("page 排序 查询");
		for (Stu item : listpages) {
			System.out.println(item.getStuId() + "===" + item.getStuName());
		}
	}
	
	@RequestMapping("/update")
	public void update(){
		List<Stu> items = esService.getByStuId("006");
		for (Stu item : items) {
			item.setStuName("张良");
			esService.save(item);
		}
	}
	
	@RequestMapping("/delete")
	public void delete(){
		Stu stu = esService.findById(7L);
		esService.delete(stu);
	}
	
	// endregion Repository Methods
	
	// region Template Methods
	
	@RequestMapping("/savebook")
	public void savebook(){
		String[] names = {"《西游记》","《红楼梦》","《三国演义》","《水浒传》","《西厢记》"};
		String[] persons = {"猴子","宝玉","曹操","武松","张生"};
		for(int i = 0; i < names.length; i++){
			Book book = new Book(i+1, names[i], persons[i]);
			esService.save(book);
		}
	}
	
	@RequestMapping("/updatebook")
	public void updatebook(){
		Book book = new Book(1,"《西游记》","猪小戒");
		esService.saveObject("library", "book", book);
	}
	
	@RequestMapping("/findbook")
	public void findbook(){
		List<Book> books = esService.findBySort(Book.class);
		System.out.println(books.size());
	}
	
	// endregion Template Methods
	
}
