package com.yjy.service.impl;

import com.yjy.mapper.LostImagesMapper;
import com.yjy.mapper.LostMapper;
import com.yjy.pojo.Lost;
import com.yjy.pojo.LostGather;
import com.yjy.pojo.LostImages;
import com.yjy.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostServiceImpl implements LostService {

    @Autowired
    private LostMapper lostMapper;

    @Autowired
    private LostImagesMapper lostImagesMapper;

    @Override
    public Integer addLost(Lost lost) {
        return lostMapper.insert(lost);
    }

    @Override
    public Integer addLostImages(LostImages lostImages) {
        return lostImagesMapper.insert(lostImages);
    }

    @Override
    public List<LostGather> getAllLost() {
        List<LostGather> lostGather = lostMapper.getAllLost();
        return lostGather;
    }

    @Override
    public List<String> getLostImages(String uuid) {
        return lostImagesMapper.getLostImages(uuid);
    }

    @Override
    public Integer editLookCount(int id) {
        return lostMapper.editLookCount(id);
    }

    @Override
    public List<LostGather> getUserLost(int userId) {
        return lostMapper.getUserLost(userId);
    }

    @Override
    public Integer deleteLost(String uuid) {
        return lostMapper.deleteLost(uuid);
    }

    @Override
    public Integer deleteLostImages(String uuid) {
        return lostImagesMapper.deleteLostImages(uuid);
    }

}
