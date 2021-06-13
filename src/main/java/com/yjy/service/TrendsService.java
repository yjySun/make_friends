package com.yjy.service;

import com.yjy.pojo.Trends;
import com.yjy.pojo.TrendsGather;

import java.util.List;

public interface TrendsService {

    /*获取某个人的全部动态*/
    List<TrendsGather> getUserTrends(int id);

    /*获取全部动态*/
    List<TrendsGather> getAllTrends();

    Integer editTrends(int trendsId, String content);

    void deleteTrends(int trendsId);

    Integer addTrends(Trends trends);

    Integer editPraiseCount(int trendsId);

    Integer editCommentCount(int trendsId);


}
