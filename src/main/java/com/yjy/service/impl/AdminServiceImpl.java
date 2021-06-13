package com.yjy.service.impl;

import com.yjy.mapper.AdminMapper;
import com.yjy.pojo.Admin;
import com.yjy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdmin(Admin admin) {
        return adminMapper.getAdmin(admin);
    }
}
