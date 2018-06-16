package cn.itcast.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username")String username, @RequestParam("password")String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
        	System.out.println("��ʼ��֤");
            subject.login(token);
            System.out.println("��֤�ɹ�");
        } catch (UnknownAccountException e) {
           System.out.println("�û�������");
            model.addAttribute("username", "�û�������");
            return "loginerror";
        } catch (IncorrectCredentialsException e) {
        	  System.out.println("�������");
            model.addAttribute("password", "�������");
            return "loginerror";
        }
        return "redirect:/index.jsp";
    }
	

}
