package cn.itcast.test;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.bean.Department;
import cn.itcast.bean.Employee;
import cn.itcast.dao.DepartmentMapper;
import cn.itcast.dao.EmployeeMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class DaoTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession  sqlSession;
	@Test
	public void test01() {
//		ut.println(employee.getDepartment().getDeptName());
		
		int nums[]= {1,2,3};
		Random random=new Random();
		/*//��ͨ�������
		
				long starttime1=System.currentTimeMillis();
				System.out.println("��ͨ��ӿ�ʼ"+starttime1);
				for(int j=0;j<10000;j++) {
					String str=UUID.randomUUID().toString().substring(0, 6)+j;
					employeeMapper.insert(new Employee(null, str, "M", str+"@qq.com",nums[random.nextInt(nums.length-1)]));
				}
				long endtime1=System.currentTimeMillis();
				System.out.println("��ͨ��ӳɹ�"+endtime1+"�ܺ�ʱ��"+(endtime1-starttime1));
				*/
		
	/*	//����д��Ա������
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		
		long starttime=System.currentTimeMillis();
		System.out.println("������ӿ�ʼ��"+starttime);
		for(int i=0;i<1000;i++) {
			String str=UUID.randomUUID().toString().substring(0, 6)+i;
			mapper.insert(new Employee(null, str, "M", str+"@qq.com",nums[random.nextInt(nums.length-1)]));
		}
		long endtime=System.currentTimeMillis();
		System.out.println("�������ִ�гɹ�"+endtime+"�ܺ�ʱ��"+(endtime-starttime));
		*/
		Employee employee=new Employee();
		employee.setdId(1);
		employee.setEmpId(45004);
		employee.setEmail("gdasbcjhbdhj");
		employee.setGender("f");
		employee.setEmpName("dash");
		employeeMapper.updateEmpl(employee);
		System.out.println("zhixingchenggong");
	}
	
}
