package com.team.service;


import com.team.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {
    void add(UserInfo userInfo);
    List<UserInfo> list();
    UserInfo get(String userName);

}
