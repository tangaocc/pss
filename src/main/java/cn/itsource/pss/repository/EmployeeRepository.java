package cn.itsource.pss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.itsource.pss.domain.Employee;
/*
	JpaRepository:它是springdatajpa给我们提供好的一个接口，该接口拥有最基本的crud
	T：你要操作的目标类型是哪个
	Serializable：你要操作的操作的主键是什么类型，  你的主键必须是实现了Serializable接口
*/
public interface EmployeeRepository extends BaseRepository<Employee, Long> {
	
	 
}
