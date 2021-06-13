package com.yjy.mapper;

import com.yjy.pojo.LostImages;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface LostImagesMapper extends Mapper<LostImages> {

    List<String> getLostImages(@Param(value = "uuid") String uuid);

    Integer deleteLostImages(@Param(value = "uuid") String uuid);
}
