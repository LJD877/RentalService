package com.forum.controller;
import com.forum.dao.UserDao;
import com.forum.dao.CommentDao;
import com.forum.pojo.Comment;
import com.forum.dao.PostDao;
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
public class PosController {
    @Autowired
    UserDao userdao;
    @Autowired
    PostDao postdao;
    @Autowired
    CommentDao commentdao;
    @RequestMapping("/poss")
    public String list(Model model){
        Collection<Post> posts = postdao.getAll();
        model.addAttribute("poss", posts);
        model.addAttribute("name",userdao.getName());
        return "pos/list";
    }
    @RequestMapping("/mposs")
    public String mlist(Model model){
        Collection<Post> posts = postdao.getMy();
        model.addAttribute("poss", posts);
        model.addAttribute("name",userdao.getName());
        return "pos/mlist";
    }
    @RequestMapping("/tposs")
    public String tlist(Model model){
        Collection<Post> posts = postdao.getAll();
        model.addAttribute("poss", posts);
        return "pos/tlist";
    }
    @RequestMapping("/sposs")
    public String slist(String some,Model model){
        System.out.println("ooooooo"+some);
        Collection<Post> posts = postdao.getSome(some);
        model.addAttribute("poss", posts);
        model.addAttribute("name",userdao.getName());
        return "pos/list";
    }

    @GetMapping("/pos")
    public String toAddpage(Model model){
        model.addAttribute("name",userdao.getName());
        return "pos/add";
    }

    //添加
    @PostMapping("/pos")
    public String addPos(Post poss){
        int a = postdao.save(poss);
        return "redirect:/poss";
    }

    //去浏览页面
    @GetMapping("/pos/{id}")
    public String toWatchPost(@PathVariable("id")Integer id, Model model){
        postdao.setId(id);
        Post post = postdao.getPostById(id);
        model.addAttribute("pos",post);
        Collection<Comment> comms = commentdao.getA(id);
        System.out.println(comms);
        model.addAttribute("coms", comms);
        model.addAttribute("name",userdao.getName());
        return "pos/watch";
    }
    @GetMapping("/tpos/{id}")
    public String TWatchPost(@PathVariable("id")Integer id, Model model){
        postdao.setId(id);
        Post post = postdao.getPostById(id);
        model.addAttribute("pos",post);
        Collection<Comment> comms = commentdao.getA(id);
        model.addAttribute("coms", comms);
        return "pos/twatch";
    }
    @GetMapping("/comm")
    public String toWatchPost2(Model model){
        int id=postdao.getId();
        postdao.setId(id);
        Post post = postdao.getPostById(id);
        model.addAttribute("pos",post);
        Collection<Comment> comms = commentdao.getA(id);
        System.out.println("debug:com");
        model.addAttribute("coms", comms);
        model.addAttribute("name",userdao.getName());
        return "pos/watch";
    }
    @GetMapping("/tcomm")
    public String TWatchPost2(Model model){
        int id=postdao.getId();
        postdao.setId(id);
        Post post = postdao.getPostById(id);
        model.addAttribute("pos",post);
        Collection<Comment> comms = commentdao.getA(id);
        System.out.println("debug:com");
        model.addAttribute("coms", comms);
        return "pos/twatch";
    }
    @GetMapping("/dpos/{id}")
    public String DPost(@PathVariable("id")Integer id, Model model){
        int a = postdao.delete(id);
        Collection<Post> posts = postdao.getMy();
        model.addAttribute("poss", posts);
        model.addAttribute("name",userdao.getName());
        return "pos/mlist";
    }
    @GetMapping("/tdpos/{id}")
    public String TDPost(@PathVariable("id")Integer id, Model model){
        int a = postdao.delete(id);
        Collection<Post> posts = postdao.getAll();
        model.addAttribute("poss", posts);
        return "pos/tlist";
    }
    @GetMapping("/ttpos/{id}")
    public String TTopPost(@PathVariable("id")Integer id, Model model){
        int a = postdao.top(id);
        Collection<Post> posts = postdao.getAll();
        model.addAttribute("poss", posts);
        return "pos/tlist";
    }
}
