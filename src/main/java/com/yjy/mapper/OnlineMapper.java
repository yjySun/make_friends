package com.yjy.mapper;

import com.yjy.pojo.Online;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface OnlineMapper extends Mapper<Online> {

    Online getOnline(Integer hour);

    void editOnlineCount(@Param(value = "personCount") Integer personCount,@Param(value = "onlineHour") Integer onlineHour);

}
