package cn.itcast.dao;

import java.util.List;


import cn.itcast.bean.Employee;

public interface EmployeeMapper {

	

	int insert(Employee employee);

	int deleteByPrimaryKey(Integer empId);

	Employee selectByPrimaryKey(Integer empId);
	
	List<Employee> selectAllEmpl();

	
	int updateEmpl(Employee employee);
	
	
}
