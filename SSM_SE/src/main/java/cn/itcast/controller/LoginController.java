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
        	System.out.println("开始认证");
            subject.login(token);
            System.out.println("认证成功");
        } catch (UnknownAccountException e) {
           System.out.println("用户名错误");
            model.addAttribute("username", "用户名错误！");
            return "loginerror";
        } catch (IncorrectCredentialsException e) {
        	  System.out.println("密码错误");
            model.addAttribute("password", "密码错误");
            return "loginerror";
        }
        return "redirect:/index.jsp";
    }
	

}
