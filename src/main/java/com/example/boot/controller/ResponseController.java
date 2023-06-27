package com.example.boot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responses")
public class ResponseController {
    @PostMapping
    public String insertResponse(){        //增
        return "插入了一条问题回复";
    }

    @DeleteMapping
    public String removeResponse(){     //删
        return "删除了一条回复";
    }

    @GetMapping
    public String  getResponse(){          //查
        return "查询一条回复";
    }
}
