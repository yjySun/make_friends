package com.yjy.service;

import com.yjy.pojo.Praise;

import java.util.List;

public interface PraiseService {

    List<String> getPraiseImages(int trendsId);

    void deletePraise(int trendsId);

    Praise getPraise(Praise praise);

    Integer addPraise(Praise praise);

}
