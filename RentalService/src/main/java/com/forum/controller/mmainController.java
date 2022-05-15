package com.forum.controller;

import com.forum.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mmainController {

    @Autowired
    UserDao userdao;
    @RequestMapping("/mmain")
    public String test(Model model) {
        model.addAttribute("name",userdao.getName());
        System.out.println("testid"+userdao.getId());
        return "ddashboard";
    }

}
