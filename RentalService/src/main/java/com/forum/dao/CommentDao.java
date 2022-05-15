package com.forum.dao;
import com.forum.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collection;
//评论Dao
@Repository
public class CommentDao {
    private static Integer id;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    UserDao userdao;
    @Autowired
    PostDao postdao;

    public Collection<Comment> getA(Integer p_id){
        String sql="select comment.id,user_id,p_id,e_id,content,a.username as un,b.username as en from comment,user a,user b where a.id=comment.user_id and b.id=comment.e_id and p_id=?";
        return jdbcTemplate.query(sql,new Object[]{p_id},new BeanPropertyRowMapper<Comment>(Comment.class));
    }
    private static Integer initId=2;
    public int save(Comment coms){
        if(coms.getId()==null){
            coms.setId(initId++);
        }
        String sql="insert into comment values(?,?,?,?,?)";
        return jdbcTemplate.update(sql,initId,userdao.getId(),postdao.getId(),getId(),coms.getContent());
    }
    public void setId(Integer pid){
        id = pid;
        return ;
    }
    public Integer getId(){return id;}

    public int delete(Integer id){
        BeanPropertyRowMapper<Comment> rowMapper = new BeanPropertyRowMapper<>(Comment.class);
        String sql="delete from comment where id=? ";
        int a = jdbcTemplate.update(sql,id);
        return a;
    }
}
