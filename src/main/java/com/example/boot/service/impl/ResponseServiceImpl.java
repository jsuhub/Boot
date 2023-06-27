package com.example.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.ResponseMapper;
import com.example.boot.pojo.entity.Response;
import com.example.boot.service.IResponseService;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl extends ServiceImpl<ResponseMapper, Response> implements IResponseService {
}
