package com.yjy.mapper;


import com.yjy.pojo.Lost;
import com.yjy.pojo.LostGather;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface LostMapper extends Mapper<Lost> {

    List<LostGather> getAllLost();

    Integer editLookCount(@Param(value = "id") int id);

    List<LostGather> getUserLost(@Param(value = "userId") int userId);

    Integer deleteLost(@Param(value = "uuid") String uuid);
}
