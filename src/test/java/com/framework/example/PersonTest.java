package com.framework.example;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.data.util.Streamable;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.framework.example.entity.Company;
import com.framework.example.entity.Person;
import com.framework.example.repository.PersonRepository;

import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 描述:
 * </p>
 * 
 * @author aLiang
 * @date 2020年9月2日 下午4:53:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class PersonTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void saveTest() {
		Company c = new Company("百度在线网络技术（北京）有限公司", "北京市海淀区中关村街道", "18510367878", "123@baidu.com", "互联网",
				"https://www.baidu.com");

		Person p = new Person(1L, "李四", "北京市海淀区中关村街道", "165187413544", "123@baidu.com", LocalDate.of(1999, 2, 12),
				"321302199902121478", c);
		Person save = personRepository.save(p);
		log.info("保存后的结果 {} ", JSONObject.toJSONString(save, true));

		Person p1 = new Person(2L, "张三", "北京市海淀区中关村街道", "17518841234", "234554@baidu.com", LocalDate.of(1989, 2, 12),
				"321302199902121478", c);
		Person save1 = personRepository.save(p1);
		log.info("保存后的结果 {} ", JSONObject.toJSONString(save1, true));

		Person p2 = new Person(3L, "李四", "北京市通州区", "16518741234", "fgwe@baidu.com", LocalDate.of(1979, 2, 12),
				"321302199902121478", c);
		Person save2 = personRepository.save(p2);
		log.info("保存后的结果 {} ", JSONObject.toJSONString(save2, true));

		Company c2 = new Company("华为技术有限公司", "广东省深圳市龙岗区", "17510366666", "huawei@huawei.com", "硬件，信息通信",
				"https://www.huawei.com");

		Person p3 = new Person(4L, "阿良", "广东省深圳市福田区莲花街道", "18518766666", "aliang@huawei.com", LocalDate.of(1979, 2, 12),
				"321302199902121478", c2);
		Person save3 = personRepository.save(p3);
		log.info("保存后的结果 {} ", JSONObject.toJSONString(save3, true));

		Person p4 = new Person(5L, "周柳柳", "广东省深圳市福田区上梅林街道", "19518743234", "zhouliuliu@huawei.com",
				LocalDate.of(1979, 2, 12), "321302199902121478", c2);
		Person save4 = personRepository.save(p4);
		log.info("保存后的结果 {} ", JSONObject.toJSONString(save4, true));

		Person p5 = new Person(6L, "李四", "广东省深圳市福田区莲花街道", "14518741234", "lisi@huawei.com", LocalDate.of(1979, 2, 12),
				"321302199902121478", c2);
		Person save5 = personRepository.save(p5);
		log.info("保存后的结果 {} ", JSONObject.toJSONString(save5, true));

		Person p6 = new Person(7L, "李依依", "广东省深圳市福田区莲花街道", "16516941266", "liyiyi@huawei.com",
				LocalDate.of(1979, 2, 12), "321302199902121478", c2);
		Person save6 = personRepository.save(p6);
		log.info("保存后的结果 {} ", JSONObject.toJSONString(save6, true));
	}

	@Test
	public void deleteByIdTest() {
		long id = 0L;
		personRepository.deleteById(id);
		log.info("删除数据id {}", id);
	}

	@Test
	public void findAllSortTest() {
		// 排序的列
		Sort sort = Sort.by("birthday");
		// 升序
		log.info("升序 {}", JSONObject.toJSONString(personRepository.findAll(sort), true));
		// sort.descending() 倒序
		log.info("倒序 {}", JSONObject.toJSONString(personRepository.findAll(sort.descending()), true));
	}

	@Test
	public void countByNameTest() {
		String name = "良";
		long count = personRepository.countByName(name);
		log.info("姓名：{}，数量: {}", name, count);
	}

	@Test
	public void countByAddressTest() {
		String address = "深圳";
		long count = personRepository.countByAddress(address);
		log.info("地址：{}，数量: {}", address, count);
	}

	@Test
	public void deleteByNameTest() {
		String name = "张四";
		long count = personRepository.deleteByName(name);
		log.info("删除姓名：{}，删除数量: {}", name, count);
	}

	@Test
	public void findByNameTest() {
		String name = "李四";
		List<Person> list = personRepository.findByName(name);
		log.info("姓名：{}，结果: {}", name, JSONObject.toJSONString(list, true));
	}

	@Test
	public void findByEmailAndNameTest() {
		String name = "阿良";
		String email = "aliang@huawei.com";
		List<Person> list = personRepository.findByEmailAndName(email, name);
		log.info("email：{}，name: {}，结果: {}", email, name, JSONObject.toJSONString(list, true));
	}

	@Test
	public void findDistinctByEmailOrMobile() {
		String email = "liyiyi@huawei.com";
		String mobile = "16518741234";
		List<Person> list = personRepository.findDistinctByEmailOrMobile(email, mobile);
		log.info("email：{}，mobile: {}，结果: {}", email, mobile, JSONObject.toJSONString(list, true));
	}

	@Test
	public void findByNamePageTest() {
		// 分页查询
		String name = "李四";
		// 从0开始
		int page = 0;
		// 必须大于0
		int size = 2;

		// 排序可以这样
		// 方式1
//		Sort sort = Sort.by("id");
//		PageRequest.of(page, size, sort);

		// 方式2
		TypedSort<Person> typedSort = Sort.sort(Person.class);
		Sort sort = typedSort.by(Person::getId).ascending().and(typedSort.by(Person::getBirthday).descending());

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Person> result = personRepository.findByName(name, pageable);
		log.info("name: {}, 分页结果: {}", name, JSONObject.toJSONString(result, true));
	}

	@Test
	public void findByNameNoPageTest() {
		// 不分页
		String name = "李四";
		Pageable unpaged = Pageable.unpaged();
		Page<Person> result = personRepository.findByName(name, unpaged);
		log.info("name: {}, 分页结果: {}", name, JSONObject.toJSONString(result, true));
	}

	@Test
	public void findByNameOrderByIdDescTest() {
		String name = "李四";
		// 从0开始
		int page = 0;
		// 必须大于0
		int size = 2;
		Pageable pageable = PageRequest.of(page, size);
		Page<Person> result = personRepository.findByNameOrderByIdDesc(name, pageable);
		log.info("name: {}, 分页结果: {}", name, JSONObject.toJSONString(result, true));
	}

	@Test
	public void findByCompanyTest() {
		// 从0开始
		int page = 0;
		// 必须大于0
		int size = 2;
		Pageable pageable = PageRequest.of(page, size);
		String companyName = "华为";
		Page<Person> result = personRepository.findByCompanyName(companyName, pageable);
		log.info("分页结果: {}", JSONObject.toJSONString(result, true));
	}

	@Test
	public void findByCompanyNameOrCompanyNatureTest() {
		String companyName = "度";
		String ompanyNature = "硬件";
		List<Person> list = personRepository.findByCompanyNameOrCompanyNature(companyName, ompanyNature);
		log.info("结果: {}", JSONObject.toJSONString(list, true));
	}

	@Test
	public void findByIdBetweenTest() {
		long start = 1L;
		long end = 3L;
		List<Person> list = personRepository.findByIdBetween(start, end);
		log.info("结果: {}", JSONObject.toJSONString(list, true));
	}

	@Test
	public void findByIdLessThanTest() {
		long id = 3L;
		List<Person> list = personRepository.findByIdLessThan(id);
		log.info("结果: {}", JSONObject.toJSONString(list, true));
	}

	@Test
	public void findByIdBetweenAndNameTest() {
		long start = 1L;
		long end = 5L;
		String name = "李";
		List<Person> list = personRepository.findByNameLikeAndIdBetween(name, start, end);
		log.info("结果: {}", JSONObject.toJSONString(list, true));
	}

	@Test
	public void findByEmailIgnoreCaseTest() {
		String email = "Huawei";
		List<Person> list = personRepository.findByEmailIgnoreCaseLike(email);
		log.info("结果: {}", JSONObject.toJSONString(list, true));
	}

	@Test
	public void streamableTest() {
		// 将结果合并
		Streamable<Person> streamable = personRepository.queryByIdGreaterThan(5L)
				.and(personRepository.queryByIdLessThan(2L));
		List<Person> list = streamable.toList();
		log.info("结果: {}", JSONObject.toJSONString(list, true));
	}

	@Test
	public void findOneByNameTest() throws InterruptedException, ExecutionException {
		String name = "李四";
		CompletableFuture<Person> future = personRepository.findOneByName(name);
		Person person = future.get();
		log.info("结果: {}", JSONObject.toJSONString(person, true));

	}

	@Test
	public void queryBySqlTest() {
		List<Person> list = personRepository.queryByIdSql(1L, 3L);
		log.info("结果: {}", JSONObject.toJSONString(list, true));
	}
	
	@Test
	public void queryByNameSqlTest() {
		List<Person> list = personRepository.queryByNameSql("李四");
		log.info("结果: {}", JSONObject.toJSONString(list, true));
	}
}
