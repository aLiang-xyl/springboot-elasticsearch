package com.framework.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.framework.example.entity.Person;
import com.framework.example.repository.PersonRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/person")
@Log4j2
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@PostMapping("/save")
	public Boolean saveTest(@RequestBody Person person) {
//		Person p1 = new Person(1L, "张三", "北京市海淀区和平街道和盛大厦", "18518751234", "12345@qq.com", LocalDate.now(),
//				"421302199001011478");
//		Person p2 = new Person(2L, "张四", "广东省深圳市福田区梅林街道卓悦汇大厦", "19518741234", "84645@qq.com", LocalDate.of(1999, 2, 12),
//				"621302199902121478");
		log.info(JSONObject.toJSONString(person));
		personRepository.save(person);
		return true;

	}

	@GetMapping("/findAll")
	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}

	@GetMapping("/findById")
	public Person findById(Long id) {
		Optional<Person> optional = personRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@PutMapping("/deleteById")
	public Boolean deleteById(Long id) {
		personRepository.deleteById(id);
		return true;
	}
	
	@GetMapping("/page")
	public Page<Person> page(Pageable pageable) {
		return personRepository.findAll(pageable);
	}
}
