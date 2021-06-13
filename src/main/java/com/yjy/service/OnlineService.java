package com.yjy.service;

import com.yjy.pojo.Online;

import java.util.List;

public interface OnlineService {

    Online getOnline(Integer hour);

    void editOnlineCount(Integer personCount, Integer onlineHour);

    void addOnline(Online online);

    List<Online> getAllOnline();
}
