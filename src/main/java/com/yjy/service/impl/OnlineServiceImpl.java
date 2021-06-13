package com.yjy.service.impl;

import com.yjy.mapper.OnlineMapper;
import com.yjy.pojo.Online;
import com.yjy.service.OnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlineServiceImpl implements OnlineService {

    @Autowired
    private OnlineMapper onlineMapper;

    @Override
    public Online getOnline(Integer hour) {
        return onlineMapper.getOnline(hour);
    }

    @Override
    public void editOnlineCount(Integer personCount, Integer onlineHour) {
        onlineMapper.editOnlineCount(personCount, onlineHour);
    }

    @Override
    public void addOnline(Online online) {
        onlineMapper.insert(online);
    }

    @Override
    public List<Online> getAllOnline() {
        return onlineMapper.selectAll();
    }

}
