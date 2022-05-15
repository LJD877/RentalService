package com.forum.controller;

import com.forum.dao.CommentDao;
import com.forum.dao.PostDao;
import com.forum.dao.UserDao;
import com.forum.pojo.Comment;
import com.forum.controller.PosController;
import com.forum.pojo.Post;
import com.forum.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class ComController {
    @Autowired
    CommentDao commentdao;
    @Autowired
    PostDao postdao;
    @Autowired
    Post post;
    @Autowired
    UserDao userdao;
    @GetMapping ("/com/{id}")
    public String add(@PathVariable("id")Integer id,Model model){
        commentdao.setId(id);
        model.addAttribute("r_user",userdao.getr_id(id));
        model.addAttribute("name",userdao.getName());
        return "comment/add";
    }
    //添加
    @PostMapping("/com")
    public String addCom(Comment coms){
        int a = commentdao.save(coms);
        int id = postdao.getId();
        return "redirect:/comm";
    }
    @GetMapping("/tcom/{id}")
    public String TDPost(@PathVariable("id")Integer id, Model model){
        int a = commentdao.delete(id);
        return "redirect:/tcomm";
    }
}
