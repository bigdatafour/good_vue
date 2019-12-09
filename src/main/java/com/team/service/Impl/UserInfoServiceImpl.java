package com.team.service.Impl;

import com.team.mapper.UserInfoMapper;
import com.team.pojo.UserInfo;
import com.team.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public void add(UserInfo userInfo) {
        userInfoMapper.add(userInfo);
    }

    @Override
    public List<UserInfo> list() {
        return userInfoMapper.list();
    }

    @Override
    public UserInfo get(String userName) {
        return userInfoMapper.get(userName);
    }
}
