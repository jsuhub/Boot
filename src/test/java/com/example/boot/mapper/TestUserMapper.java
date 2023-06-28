package com.example.boot.mapper;


import com.example.boot.pojo.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testSelectUserPasswdByUsername () {
        String zhouxin = userMapper.selectUserPasswdByUsername("zhouxin");
        System.out.println(zhouxin);
    }

    @Test
    void testSelectUsernameByUsername () {
        String zhouxin = userMapper.selectUsernameByUsername("shu12345678");
        System.out.println(zhouxin);
    }

    @Test
    void  testremoteUserByUsername(){
       userMapper.removeUserByUsername("yuliang");
    }



    @Test
    void testInsert () {
        User user = new User();
        user.setUsername("shuhao");

        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

}
