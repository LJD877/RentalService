package com.forum.pojo;
import com.forum.dao.UserDao;
import com.forum.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//帖子表
@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Post {
    private Integer id;
    private Integer user_id;
    private String title;
    private String content;
    private Integer type;
    private String username;
}