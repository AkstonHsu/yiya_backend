package com.example.yiya_backend_1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yiya_backend_1.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE uname = #{uname}")
    User serchByUname(String uname);
    @Select("SELECT * FROM user WHERE uname = #{uname} AND password = #{password}")
    User findByUnameAndPassword(String uname, String password);
    @Select("SELECT * FROM user WHERE uid = #{uid}")
    User selectByUid(Long uid);
    @Insert("INSERT INTO user (uname, password ,role) VALUES (#{uname}, #{password},#{role})")
    @Options(useGeneratedKeys = true, keyProperty = "uid", keyColumn = "uid")
    void insertUser(User user);
}
