package com.framework.example.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.util.Streamable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.framework.example.entity.Person;

/**
 * <p>
 * 描述:person数据查询
 * </p>
 * 
 * @author aLiang
 * @date 2020年9月2日 下午3:41:40
 */
@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	/**
	 * 根据名字查询数量
	 * 
	 * @param name
	 * @return
	 */
	long countByName(String name);

	/**
	 * 根据地址查询
	 * 
	 * @param address
	 * @return
	 */
	long countByAddress(String address);

	/**
	 * 根据名字删除，并返回删除数量
	 * 
	 * @param name
	 * @return
	 */
	long deleteByName(String name);

	/**
	 * 根据名字删除并返回删除对象
	 * 
	 * @param name
	 * @return
	 */
	List<Person> removeByName(String name);

	/**
	 * 根据名字查询
	 * 
	 * @param name
	 * @return
	 */
	List<Person> findByName(String name);

	/**
	 * 根据eamil和名字查询
	 * 
	 * @param email
	 * @param name
	 * @return
	 */
	List<Person> findByEmailAndName(String email, String name);

	/**
	 * 根据email或者手机号查询
	 * 
	 * @param email
	 * @param mobile
	 * @return
	 */
	List<Person> findDistinctByEmailOrMobile(String email, String mobile);

	/**
	 * 根据名字分页查询
	 * 
	 * @param name
	 * @param page
	 * @return
	 */
	Page<Person> findByName(String name, Pageable page);

	/**
	 * 根据名字按照id倒序排列查询
	 * 
	 * @param name
	 * @param page
	 * @return
	 */
	Page<Person> findByNameOrderByIdDesc(String name, Pageable page);

	/**
	 * 根据company对象下的name属性查询
	 * 
	 * @param companyName
	 * @param page
	 * @return
	 */
	Page<Person> findByCompanyName(String companyName, Pageable page);

	/**
	 * 根据company对象下的name或者根据company对象下的nature属性查询
	 * 
	 * @param companyName
	 * @param ompanyNature
	 * @return
	 */
	List<Person> findByCompanyNameOrCompanyNature(String companyName, String ompanyNature);

	/**
	 * 根据id范围查询
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	List<Person> findByIdBetween(Long start, Long end);

	/**
	 * id小于参数查询
	 * 
	 * @param id
	 * @return
	 */
	List<Person> findByIdLessThan(Long id);

	/**
	 * 名字模糊查询并且根据id范围查询
	 * 
	 * @param name
	 * @param from
	 * @param to
	 * @return
	 */
	List<Person> findByNameLikeAndIdBetween(String name, Long from, Long to);

	/**
	 * 忽略email大小写查询
	 * 
	 * @param email
	 * @return
	 */
	List<Person> findByEmailIgnoreCaseLike(String email);

	/**
	 * 查询前10
	 * 
	 * @param name
	 * @param sort
	 * @return
	 */
	List<Person> findFirst10ByName(String name, Sort sort);

	/**
	 * 查询前3
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	Slice<Person> findTop3ByName(String name, Pageable pageable);
	
	/**
	 * 根据名字查询前10分页
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<Person> queryFirst10ByName(String name, Pageable pageable);
	
	/**
	 * 小于参数id查询
	 * 
	 * @param id
	 * @return
	 */
	Streamable<Person> queryByIdLessThan(Long id);
	
	/**
	 * 大于id参数查询
	 * 
	 * @param id
	 * @return
	 */
	Streamable<Person> queryByIdGreaterThan(Long id);

	/**
	 * 根据名字异步查询
	 * 
	 * @param name
	 * @return
	 */
	@Async
	CompletableFuture<Person> findOneByName(String name); 
	
	@Query("{\"range\":{\"id\":{\"from\":\"?0\",\"to\":\"?1\"}}}")
	List<Person> queryByIdSql(Long start, Long end);
	
	@Query("{\"match\": {\"name\": {\"query\": \"?0\"}}}")
	List<Person> queryByNameSql(String name);
}
