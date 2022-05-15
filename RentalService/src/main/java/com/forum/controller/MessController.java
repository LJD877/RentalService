package com.forum.controller;

import com.forum.dao.CommentDao;
import com.forum.dao.MessageDao;
import com.forum.dao.PostDao;
import com.forum.dao.UserDao;
import com.forum.pojo.Message;
import com.forum.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class MessController {
    @Autowired
    UserDao userdao;
    @Autowired
    PostDao postdao;
    @Autowired
    CommentDao commentdao;
    @Autowired
    MessageDao messagedao;
    @RequestMapping("/mess")
    public String list(Model model){
        Collection<Message> mess = messagedao.getMy();
        model.addAttribute("mess", mess);
        model.addAttribute("name",userdao.getName());
        return "mes/list";
    }
    @RequestMapping("/tmess")
    public String tlist(Model model){
        Collection<Message> mess = messagedao.getAll();
        model.addAttribute("mess", mess);
        return "mes/tlist";
    }
    @PostMapping("/amess")
    public String add(String content){
        int a = messagedao.save(content);
        return "redirect:/mess";
    }
    @RequestMapping("/mes/{id}")
    public String Change(@PathVariable("id")Integer id){
        int a = messagedao.change(id);
        return "redirect:/tmess";
    }
    @GetMapping("/delmes/{id}")
    public String deleteMes(@PathVariable("id")int id){
        int a = messagedao.delete(id);
        return "redirect:/mess";
    }
    @GetMapping("/tdelmes/{id}")
    public String TdeleteMes(@PathVariable("id")int id) {
        int a = messagedao.delete(id);
        return "redirect:/tmess";
    }
}
