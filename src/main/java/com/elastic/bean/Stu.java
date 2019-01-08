package com.elastic.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 通过注解配置，与Repository联用时，可以直接将数据结构映射到ElasticSearch上
 * index：是否设置索引， store是否存储数据，type:数据类型，analyzer：分词粒度选择，searchAnalyzer：查询进行分词处理
 * ik_smart：进行最小粒度分词，ik_max_word进行最大粒度分词
 * @author Derlin
 *
 */
@Document(indexName = "stu", type = "doc")
public class Stu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Field(index=true, store = true, type = FieldType.Long)
	private Long id;

	@Field(index = true, analyzer = "ik_max_word", store = true, searchAnalyzer = "ik_max_word", type = FieldType.Text)
	private String stuId;

	@Field(index = true, analyzer = "ik_max_word", store = true, searchAnalyzer = "ik_max_word", type = FieldType.Text)
	private String stuName;

	@Field(index = true, store = true, type = FieldType.Date)
	private Date createTime;

	public Stu() {
	}
	
	public Stu(Long id, String stuId, String stuName, Date createTime) {
		this.id = id;
		this.stuId = stuId;
		this.stuName = stuName;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
