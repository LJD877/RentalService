package com.forum.dao;
import com.forum.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Map;

import static java.util.Objects.isNull;
import static org.thymeleaf.util.StringUtils.isEmpty;

//用户Dao
@Repository
public class UserDao {
    private static String name;
    private static Integer id;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public String getPassword(String username){
        String sql="select password from user where username=?";
        System.out.println("debug2");
        String password = null;
        try{
            password = jdbcTemplate.queryForObject(sql,String.class,username);
        }catch(Exception e){

        }
        return password;
    }
    public Integer getType(String username){
        String sql="select type from user where username=?";
        Integer type = 0;
        try{
            type = jdbcTemplate.queryForObject(sql,Integer.class,username);
        }catch(Exception e){

        }
        return type;
    }

    public Collection<User> getAll(){
        return jdbcTemplate.query("select * from user where type != 1",new Object[]{},new BeanPropertyRowMapper<User>(User.class));
    }

    //通过id得到
    public User getuserById(Integer id){
        BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        String sql="select * from user where id=? ";
        return  jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    public int update(User uses){/*进行数据库的update操作*/
        System.out.println(uses.getUsername());
        String sql="update user set username=?,password=?,type=? where id=?";
        return jdbcTemplate.update(sql, uses.getUsername(),uses.getPassword(),uses.getType(),uses.getId());
    }

    public int delete(Integer id){
        BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        String sql="delete from user where id=? ";
        int a = jdbcTemplate.update(sql,id);
        return a;
    }

    public String getName(){return name;}
    public Integer getId(){return id;}
    public void set(String tname)
    {
        name=tname;
        return ;
    }
    public void  setid(String username)
    {
        String sql="select id from user where username=?";
        Integer uid = null;
        try{
            uid = jdbcTemplate.queryForObject(sql,Integer.class,username);
        }catch(Exception e){

        }
        id=uid;
        return ;
    }
    public String getr_id(Integer id)
    {
        String sql="select username from user where id=?";
        String username = null;
        try{
            username = jdbcTemplate.queryForObject(sql,String.class,id);
        }catch(Exception e){

        }
        return username;
    }

    public Boolean exist(String username){
        String sql="select username from user where username=?";
        String a = null;
        try{
            a = jdbcTemplate.queryForObject(sql,String.class,username);
        }catch(Exception e){

        }
        System.out.println(a);
        return isNull(a);
    }

    private static Integer initId=4;
    public int save(String un,String pw){
        initId++;
        String sql="insert into user values(?,?,?,?)";
        return jdbcTemplate.update(sql,initId,un,pw,0);
    }

}
