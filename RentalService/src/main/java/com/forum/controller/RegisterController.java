package com.forum.controller;

import com.forum.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class RegisterController {

    @Autowired
    UserDao userdao;
    @GetMapping("/reg")
    public String Register(Model model) {
        model.addAttribute("name","Welcome");
        return "register";
    }
    @PostMapping("/reg")
    public String addPos(@RequestParam("username") String username, @RequestParam("password") String password,
                         @RequestParam("ppassword") String ppassword, Model model){
        System.out.println("regtest");
        System.out.println(username);
        System.out.println(password);
        System.out.println(ppassword);
        if(username==""){
            model.addAttribute("msg","用户名不能为空，请重新输入");
            model.addAttribute("name","Welcome");
            return "register";
        }else if(!userdao.exist(username)) {
            model.addAttribute("msg", "用户名已存在，请重新输入");
            model.addAttribute("name", "Welcome");
            return "register";
        }else if(!(password.equals(ppassword))){
            model.addAttribute("msg","两次密码不一致，请重新输入");
            model.addAttribute("name","Welcome");
            return "register";
        }else{
            int a = userdao.save(username, password);
            model.addAttribute("msg","注册成功，请登录");
            return "index";
        }
    }

    @GetMapping("/bac")
    public String Back(Model model) {
        return "index";
    }

}
