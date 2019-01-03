package com.elastic.controller;

import java.util.Date;

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
		Stu st = new Stu(6L, "006", "小陈", new Date());
		esService.createDocument(st);
		Stu stu = esService.getByStuId("006");
		System.out.println(stu.getStuName());
	}
	
}
