package com.forum;

import com.forum.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserDao userdao;
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Model model, HttpSession session){
        //具体的业务
        System.out.println(username);
        System.out.println(password);
        System.out.println(userdao.getType(username));
        if(userdao.getPassword(username).equals(password)){
            session.setAttribute("loginUser",username);
            userdao.set(username);
            userdao.setid(username);
            model.addAttribute("name",userdao.getName());
            if(userdao.getType(username)==1) return "redirect:/main.html";
            else if(userdao.getType(username)==0) return "redirect:/mmain";
            else {
                model.addAttribute("msg","你的账号已被封禁");
                return "index";
            }
        }
        else {
            model.addAttribute("msg","用户名或者密码错误");
            return "index";
        }

    }

}