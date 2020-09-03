package com.framework.example.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 描述:人员实体
 * </p>
 * 
 * @author aLiang
 * @date 2020年9月2日 下午3:34:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(indexName = "person", shards = 1, replicas = 1)
@TypeAlias("person")
public class Person {

	@Id
	private Long id;

	@Field(type = FieldType.Keyword, analyzer = "ik_max_word")
	private String name;

	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String address;

	@Field(type = FieldType.Keyword, analyzer = "ik_max_word")
	private String mobile;

	@Field(type = FieldType.Keyword, analyzer = "ik_max_word")
	private String email;

	@Field(type = FieldType.Date, format = DateFormat.date)
	private LocalDate birthday;

	@Field(type = FieldType.Keyword)
	private String idCard;

	@Field
	private Company company;
}
