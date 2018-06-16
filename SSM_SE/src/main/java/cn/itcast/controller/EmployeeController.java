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
		// �ڲ�ѯ֮ǰֻ��Ҫ���ã�����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		List<Employee> mList=employeeService.getAllEmp();
		
		// ��װ��ҳ��Ϣ ��ѯ���������ݣ�����������ʾҳ��
		PageInfo page = new PageInfo(mList, 5);
		
		model.addAttribute("pageInfo", page);
		
		return "list";
	} 
	
	/**
	 * �������
	 * @param employee
	 * @return
	 */
//	@PostMapping("/emp")
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public String  addEmpl(Employee employee) {
		System.out.println("��ӵ�Ա�����ݣ�"+employee);
		employeeService.addEmp(employee);
//		 view.setViewName("redirect:/emps");
		return "��ӳɹ�";
	}
	
	
	/**
	 * ɾ������
	 * @param id
	 * @return
	 */
	@DeleteMapping("/emp/{id}")
	@ResponseBody
	public String deleteEmp(@PathVariable("id")Integer id) {
		System.out.println("Ҫɾ����i:"+id);
		System.out.println("ɾ��ִ��");
		employeeService.deleteEmp(id);
		System.out.println("ɾ���ɹ�");
		return "ɾ���ɹ�";
	}
	
	/**
	 * ��������
	 * ���²���ǰҪ��������  �鴦��ǰid�µ�����
	 * Ȼ���ύ���º������
	 */
	@ResponseBody
	@GetMapping("/emp/{id}")
	public Employee getEmpById(@PathVariable("id")Integer id) {
		Employee employee=employeeService.getEmpById(id);
		System.out.println("Ҫ�޸ĵ�����"+employee);
		return employee;
		
	}
	
	
	@ResponseBody
	@PutMapping("/emp/{empId}")//Я��������Ϊ�˽�����id������  ����idΪ��
	public String update(Employee employee) {
		System.out.println("�޸ĺ�����ݣ�"+employee);
		employeeService.updateEmp(employee);
		return "ִ�гɹ�";
	}
	
}
