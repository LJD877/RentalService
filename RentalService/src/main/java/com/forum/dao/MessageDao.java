package com.forum.dao;

import com.forum.pojo.Message;
import com.forum.pojo.Post;
import com.forum.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;
//私信Dao
@Repository
public class MessageDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    UserDao userdao;
    public Collection<Message> getAll(){
        return jdbcTemplate.query("select * from message",new Object[]{},new BeanPropertyRowMapper<Message>(Message.class));
    }
    public Collection<Message> getMy(){
        return jdbcTemplate.query("select * from message where sent_id=?",new Object[]{userdao.getId()},new BeanPropertyRowMapper<Message>(Message.class));
    }
    private static Integer initId=2;
    public int save(String cont){
        initId++;
        String sql="insert into message values(?,?,?,?)";
        return jdbcTemplate.update(sql,initId,userdao.getId(),cont,0);
    }
    public int change(Integer id){
        String sql="update message set type=1 where id=?";
        return jdbcTemplate.update(sql,id);
    }
    public int delete(Integer id){
        BeanPropertyRowMapper<Message> rowMapper = new BeanPropertyRowMapper<>(Message.class);
        String sql="delete from message where id=? ";
        int a = jdbcTemplate.update(sql,id);
        return a;
    }
}
