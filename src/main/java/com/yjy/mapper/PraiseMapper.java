package com.yjy.mapper;

import com.yjy.pojo.Praise;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PraiseMapper extends Mapper<Praise> {

    Praise getPraise(@Param("praise")Praise praise);

    void deletePraise(int trendsId);

    List<String> getPraiseImages(@Param("trendsId") int trendsId);
}
