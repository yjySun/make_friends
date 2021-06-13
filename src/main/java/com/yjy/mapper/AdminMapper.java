package com.yjy.mapper;

import com.yjy.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

    Admin getAdmin(@Param("admin") Admin admin);

}
