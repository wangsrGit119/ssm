package cn.itcast.dao;

import java.util.List;


import cn.itcast.bean.Department;

public interface DepartmentMapper {
	

    int insert(Department department);
    
    int deleteByPrimaryKey(Integer deptId);
    
    Department selectByPrimaryKey(Integer deptId);
    
    List<Department> selectAllDept();
    
    int updateDept(Department department);
}
