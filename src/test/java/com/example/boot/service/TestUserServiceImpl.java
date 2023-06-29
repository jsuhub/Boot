package com.example.boot.service;

import com.example.boot.service.impl.UserServicelmpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserServiceImpl {

    @Autowired
    private UserServicelmpl userServicelmpl;

    @Test
    void testSe() {
        Boolean zhouxin = userServicelmpl.getUserByUsername("zhouxin", "123456");
        System.out.println(zhouxin);
    }





}
