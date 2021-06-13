package com.yjy.service;

import com.yjy.pojo.Lost;
import com.yjy.pojo.LostGather;
import com.yjy.pojo.LostImages;

import java.util.List;

public interface LostService {

    Integer addLost(Lost lost);

    Integer addLostImages(LostImages lostImages);

    List<LostGather> getAllLost();

    List<String> getLostImages(String uuid);

    Integer editLookCount(int id);

    List<LostGather> getUserLost(int userId);

    Integer deleteLost(String uuid);

    Integer deleteLostImages(String uuid);

}
