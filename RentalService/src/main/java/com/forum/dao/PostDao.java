package com.forum.dao;

import com.forum.pojo.Post;
import com.forum.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//帖子Dao
@Repository
public class PostDao {
    private static Integer id;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    UserDao userdao;

    public Collection<Post> getAll(){

        List<Post> list = new ArrayList();
        List<Post> list1 = new ArrayList();
        List<Post> list2 = new ArrayList();
        list1 = jdbcTemplate.query("select post.id,user_id,title,content,post.type,username from post,user where post.user_id=user.id and post.type=1",new Object[]{},new BeanPropertyRowMapper<Post>(Post.class));
        list2 = jdbcTemplate.query("select post.id,user_id,title,content,post.type,username from post,user where post.user_id=user.id and post.type!=1",new Object[]{},new BeanPropertyRowMapper<Post>(Post.class));
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }

    public Collection<Post> getMy(){
        return jdbcTemplate.query("select post.id,user_id,title,content,post.type,username from post,user where post.user_id=user.id and user_id=?",new Object[]{userdao.getId()},new BeanPropertyRowMapper<Post>(Post.class));
    }

    public Collection<Post> getSome(String limit){
        System.out.println(limit);
        String para = '%'+limit+'%';
        return jdbcTemplate.query("select post.id,user_id,title,content,post.type,username from post,user where post.user_id=user.id and (`title` like ? )",new Object[]{para},new BeanPropertyRowMapper<Post>(Post.class));
    }

    private static Integer initId=3;
    public int save(Post poss){
        if(poss.getId()==null){
            poss.setId(initId++);
        }
        String sql="insert into post values(?,?,?,?,?)";
        return jdbcTemplate.update(sql,initId,userdao.getId(),poss.getTitle(),poss.getContent(),0);
    }

    //通过id得到
    public Post getPostById(Integer id){
        BeanPropertyRowMapper<Post> rowMapper = new BeanPropertyRowMapper<>(Post.class);
        String sql="select * from post where id=? ";
        return  jdbcTemplate.queryForObject(sql,rowMapper,id);
    }
    public void setId(Integer pid){
        id = pid;
        return ;
    }
    public Integer getId(){return id;}

    public Integer getPoster(Integer id){
        String sql="select user_id from post where id=?";
        Integer Poster = 0;
        try{
            Poster = jdbcTemplate.queryForObject(sql,Integer.class,id);
        }catch(Exception e){

        }
        return Poster;
    }

    public int delete(Integer id){
        BeanPropertyRowMapper<Post> rowMapper = new BeanPropertyRowMapper<>(Post.class);
        String sql="delete from post where id=? ";
        int a = jdbcTemplate.update(sql,id);
        return a;
    }
    public int top(Integer id){
        List<Post> list = new ArrayList();
        list = jdbcTemplate.query("select * from post where id=?",new Object[]{id},new BeanPropertyRowMapper<Post>(Post.class));
        Post a = list.get(0);
        int b = a.getType();
        String sql="update post set type=1 where id=?";
        String sql1="update post set type=0 where id=?";
        if(b==0) return jdbcTemplate.update(sql,id);
        else return jdbcTemplate.update(sql1,id);
    }
}
