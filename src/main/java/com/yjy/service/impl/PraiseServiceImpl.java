package com.yjy.service.impl;

import com.yjy.mapper.PraiseMapper;
import com.yjy.pojo.Praise;
import com.yjy.service.PraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PraiseServiceImpl implements PraiseService {

    @Autowired
    private PraiseMapper praiseMapper;

    @Override
    public List<String> getPraiseImages(int trendsId) {
        return praiseMapper.getPraiseImages(trendsId);
    }

    @Override
    public void deletePraise(int trendsId) {
        praiseMapper.deletePraise(trendsId);
    }

    @Override
    public Praise getPraise(Praise praise) {
        return praiseMapper.getPraise(praise);
    }

    @Override
    public Integer addPraise(Praise praise) {
        return praiseMapper.insert(praise);
    }

}
