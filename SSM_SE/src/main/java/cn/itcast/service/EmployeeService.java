package cn.itcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.bean.Employee;
import cn.itcast.dao.EmployeeMapper;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	public List<Employee> getAllEmp(){
		return employeeMapper.selectAllEmpl();
	}
	
	public int addEmp(Employee employee) {
	 return employeeMapper.insert(employee);
	}
	
	public int deleteEmp(Integer empId) {
		
		return employeeMapper.deleteByPrimaryKey(empId);
		
	}
	
	public Employee getEmpById(Integer empId) {
		return employeeMapper.selectByPrimaryKey(empId);
	}
	
	public int updateEmp(Employee employee) {
		
		return employeeMapper.updateEmpl(employee);
	}

}
