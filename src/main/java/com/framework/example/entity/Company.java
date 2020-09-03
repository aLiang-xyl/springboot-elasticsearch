package com.framework.example.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 描述:
 * </p>
 * 
 * @author aLiang
 * @date 2020年9月2日 下午3:34:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Company {

	@Field(type = FieldType.Keyword, analyzer = "ik_max_word")
	private String name;

	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String address;

	@Field(type = FieldType.Keyword, analyzer = "ik_max_word")
	private String mobile;

	@Field(type = FieldType.Keyword, analyzer = "ik_max_word")
	private String email;

	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String nature;
	
	@Field(type = FieldType.Text)
	private String website;
}
