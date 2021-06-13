package com.yjy.service.impl;

import com.yjy.mapper.TrendsMapper;
import com.yjy.pojo.Trends;
import com.yjy.pojo.TrendsGather;
import com.yjy.service.TrendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendsServiceImpl implements TrendsService {

    @Autowired
    private TrendsMapper trendsMapper;

    @Override
    public List<TrendsGather> getUserTrends(int id) {
        List<TrendsGather> userTrends = trendsMapper.getUserTrends(id);
        return userTrends;
    }

    @Override
    public List<TrendsGather> getAllTrends() {
        List<TrendsGather> userTrends = trendsMapper.getAllTrends();
        return userTrends;
    }

    @Override
    public Integer editTrends(int trendsId, String content) {
        return trendsMapper.editTrends(trendsId, content);
    }

    @Override
    public void deleteTrends(int trendsId) {
        trendsMapper.deleteTrends(trendsId);
    }



    @Override
    public Integer addTrends(Trends trends) {
        return trendsMapper.insert(trends);
    }

    @Override
    public Integer editPraiseCount(int trendsId) {
        return trendsMapper.editPraiseCount(trendsId);
    }

    @Override
    public Integer editCommentCount(int trendsId) {
        return trendsMapper.editCommentCount(trendsId);
    }


}
