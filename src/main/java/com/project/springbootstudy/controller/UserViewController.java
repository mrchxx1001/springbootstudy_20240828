package com.project.springbootstudy.controller;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewController {

    @Autowired
    UserService userService;

    //로그인
    @PostMapping("login-process")
    public String loginCheck(@RequestParam(name = "userId") String userId
                            ,@RequestParam(name = "password") String password
                            ,Model model){
        boolean errorResult = userService.login(userId, password);

        if(!errorResult){
            model.addAttribute("error", "없는 회원입니다.");
            return "user/login";
        }
        return "home";
    }

    //사용자 회원가입 화면을 반환
    @GetMapping("/user/join")
    public String join(){
        return "user/join";
    }

    //사용자 회원가입 처리에 대한 화면 반환
    @PostMapping("join-process")
    public String joinProcess(@ModelAttribute User user, Model model){

        try{
            userService.join(user);
        }catch (RuntimeException e){
            model.addAttribute("error", e.getMessage());
            return "/user/join";
        }
        System.out.println("회원가입성공");
        return "user/login";
    }

}
