package com.example.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {


//    @Select("select * from tb_user where username = #{username}")
//    int selectUserByUsername(String username);


    @Select("select password from tb_user where username = #{username}")
    String selectUserPasswdByUsername(String username);

    @Select("select username from tb_user where username = #{username}")
    String selectUsernameByUsername(String username);
}
