package cn.itcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.bean.Department;
import cn.itcast.service.DepartmentService;

@Controller
public class DeptmentController {
	
	
	@Autowired
	DepartmentService departmentService;
	
	
	@ResponseBody
	@GetMapping("/depts")
	public List<Department> getAllDepts() {
		
		
		List<Department> departments=departmentService.getDepts();
		System.out.println(departments);
//		model.addAttribute("deptlists", departments);
		return departments;
	}
	
	

}
