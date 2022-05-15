package com.forum.controller;

import com.forum.dao.UserDao;
import com.forum.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collection;

@Controller
public class UseController {
    @Autowired
    UserDao userdao;
    @RequestMapping("/uses")
    public String list(Model model){
        Collection<User> users = userdao.getAll();
        System.out.println(users);
        model.addAttribute("uses", users);
        return "use/list";
    }

    //去修改页面
    @GetMapping("/use/{id}")
    public String toUpdateEqu(@PathVariable("id")Integer id, Model model){
        User user = userdao.getuserById(id);
        model.addAttribute("use",user);
        return "use/update";
    }
    //更新
    @PostMapping("/updateUser")
    public String updateUser(User user){
        System.out.println(user.getUsername());
        int a = userdao.update(user);
        return "redirect:/uses";
    }

    //删除
    @GetMapping("/deluse/{id}")
    public String deleteUser(@PathVariable("id")int id){
        //EquestionDao.delete(id);
        int a = userdao.delete(id);
        return "redirect:/uses";
    }
}
