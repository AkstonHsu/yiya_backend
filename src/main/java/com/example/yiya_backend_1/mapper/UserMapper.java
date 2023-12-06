package com.example.yiya_backend_1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yiya_backend_1.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE uname = #{uname}")
    User serchByUname(String uname);
    @Select("SELECT * FROM user WHERE uname = #{uname} AND password = #{password}")
    User findByUnameAndPassword(String uname, String password);
    @Select("SELECT uid FROM user")
    List<Long> getAllUids();
}
