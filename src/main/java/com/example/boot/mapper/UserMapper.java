package com.example.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.pojo.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.DeleteMapping;

@Mapper
public interface UserMapper extends BaseMapper<User> {


//    @Select("select * from tb_user where username = #{username}")
//    int selectUserByUsername(String username);

    @Select("select password from tb_user where username = #{username}")
    String selectUserPasswdByUsername(String username);

    @Select("select username from tb_user where username = #{username}")
    String selectUsernameByUsername(String username);

    @Delete("delete from tb_user where username = #{username}")
   Boolean removeUserByUsername(String username);




}
