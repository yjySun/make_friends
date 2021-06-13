package com.yjy.mapper;


import com.yjy.pojo.RegisterStatistics;
import com.yjy.pojo.Statistics;
import tk.mybatis.mapper.common.Mapper;

public interface StatisticsMapper extends Mapper<Statistics> {

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
