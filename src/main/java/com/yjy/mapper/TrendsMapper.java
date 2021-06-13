package com.yjy.mapper;

import com.yjy.pojo.Trends;
import com.yjy.pojo.TrendsGather;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TrendsMapper extends Mapper<Trends> {

    /*获取某个人的全部动态*/
    List<TrendsGather> getUserTrends(int id);

    /*获取全部动态*/
    List<TrendsGather> getAllTrends();

    Integer editTrends(@Param(value = "trendsId") int trendsId, @Param(value = "content") String content);

    void deleteTrends(int trendsId);

    Integer editPraiseCount(int trendsId);

    Integer editCommentCount(int trendsId);

}
