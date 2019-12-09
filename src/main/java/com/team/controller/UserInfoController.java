package com.team.controller;

import com.team.pojo.UserInfo;
import com.team.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/aaa")
@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;


    @CrossOrigin(origins = "*")
    @RequestMapping(value="add", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void add (@RequestBody UserInfo userInfo){

        userInfoService.add(userInfo);
    }



}
