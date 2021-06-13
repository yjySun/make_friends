package com.yjy.service;


import com.yjy.pojo.RegisterStatistics;
import com.yjy.pojo.Statistics;

public interface StatisticsService {

    Integer editAllUser();

    Integer editAllTrends();

    Integer editAllLost();

    Integer editManCount();

    Integer editManRatio();

    Statistics getStatistics();

    Integer getRegisterCount();

    RegisterStatistics getRegisterMonth();

    Double getGrowthRatio();

}
