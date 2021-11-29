package com.fullstackmusic.server.controllers;

import com.fullstackmusic.server.models.User;
import com.fullstackmusic.server.utils.JwtUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class UserController {
    @RequestMapping("/login")
    public User login(@RequestBody User user, HttpServletResponse response){
        System.out.println(user.getUsername()+", "+user.getPassword());
        String token = JwtUtil.sign(user.getUsername());
        response.setHeader("Authorization", token);
        return user;
    }
    @RequestMapping("/logout")
    public void logout(){
        System.out.println("退出登录！");
    }
}
