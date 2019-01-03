package com.elastic.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elastic.bean.Stu;
import com.elastic.service.EsService;

@Controller
@RequestMapping("/es")
public class EsController {
	
	@Resource
	private EsService esService;
	
	@RequestMapping("/elastic")
	public void elastic(){
		Stu st = new Stu(1L, "006", "小陈", new Date());
		esService.createDocument(st);
		Stu stu = esService.getByStuId("016");
		if(stu != null){
			System.out.println(stu.getStuName());
		}
		esService.searchAll();
		List<Stu> lists = esService.queryAll();
		for (Stu item : lists) {
			System.out.println(item.getCreateTime());
			System.out.println(new Date());
		}
	}
	
}
