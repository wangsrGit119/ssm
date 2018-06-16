package cn.itcast.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.itcast.bean.Employee;
import cn.itcast.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	//@ResponseBody
	@GetMapping("/emps")
	public String  getAllEmp(@RequestParam(value = "pn", defaultValue = "1") Integer pn,Model model) {
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		List<Employee> mList=employeeService.getAllEmp();
		
		// 封装分页信息 查询出来的数据，传入连续显示页数
		PageInfo page = new PageInfo(mList, 5);
		
		model.addAttribute("pageInfo", page);
		
		return "list";
	} 
	
	/**
	 * 添加数据
	 * @param employee
	 * @return
	 */
//	@PostMapping("/emp")
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public String  addEmpl(Employee employee) {
		System.out.println("添加的员工数据："+employee);
		employeeService.addEmp(employee);
//		 view.setViewName("redirect:/emps");
		return "添加成功";
	}
	
	
	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	@DeleteMapping("/emp/{id}")
	@ResponseBody
	public String deleteEmp(@PathVariable("id")Integer id) {
		System.out.println("要删除的i:"+id);
		System.out.println("删除执行");
		employeeService.deleteEmp(id);
		System.out.println("删除成功");
		return "删除成功";
	}
	
	/**
	 * 更新数据
	 * 更新操作前要回显数据  查处当前id下的数据
	 * 然后提交更新后的数据
	 */
	@ResponseBody
	@GetMapping("/emp/{id}")
	public Employee getEmpById(@PathVariable("id")Integer id) {
		Employee employee=employeeService.getEmpById(id);
		System.out.println("要修改的数据"+employee);
		return employee;
		
	}
	
	
	@ResponseBody
	@PutMapping("/emp/{empId}")//携带参数是为了将更新id带过来  否则id为空
	public String update(Employee employee) {
		System.out.println("修改后的数据："+employee);
		employeeService.updateEmp(employee);
		return "执行成功";
	}
	
}
