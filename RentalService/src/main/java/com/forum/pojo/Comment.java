package com.forum.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
//评论表
@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Comment {
    private Integer id;
    private Integer user_id;
    private Integer p_id;
    private Integer e_id;
    private String content;
    private String un;
    private String en;
}
