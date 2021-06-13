package com.yjy.service.impl;

import com.yjy.mapper.StatisticsMapper;
import com.yjy.pojo.RegisterStatistics;
import com.yjy.pojo.Statistics;
import com.yjy.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Integer editAllUser() {
        return statisticsMapper.editAllUser();
    }

    @Override
    public Integer editAllTrends() {
        return statisticsMapper.editAllTrends();
    }

    @Override
    public Integer editAllLost() {
        return statisticsMapper.editAllLost();
    }

    @Override
    public Integer editManCount() {
        return statisticsMapper.editManCount();
    }

    @Override
    public Integer editManRatio() {
        return statisticsMapper.editManRatio();
    }

    @Override
    public Statistics getStatistics() {
        return statisticsMapper.getStatistics();
    }

    @Override
    public Integer getRegisterCount() {
        return statisticsMapper.getRegisterCount();
    }

    @Override
    public RegisterStatistics getRegisterMonth() {
        return statisticsMapper.getRegisterMonth();
    }

    @Override
    public Double getGrowthRatio() {
        return statisticsMapper.getGrowthRatio();
    }

}
