package com.project.springbootstudy.service;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // 로그인 프로세스
    public String loginCheck(@RequestParam(name="userId") String userId
                            ,@RequestParam(name="password") String password
                            ,Model model){

        User user = userRepository.loginCheck(userId, password);

        if(user != null){
            return "home";
            }

        //사용자가 존재하지 않을 경우
        model.addAttribute("error", "없는 회원입니다.");
        return "user/login";
    }

    //로그인
    public boolean login(String userId , String password) throws RuntimeException {

        User user = userRepository.loginCheck(userId, password);

        if (user == null){
            return false;
        }
        return true;
    }

    // 사용자 회원가입
    public void join(User user) throws RuntimeException{

        //사용자 ID는 최대 10자리다.
        if(user.getUserId().length() > 10){
            throw new RuntimeException("사용자ID는 최대10자리여야 합니다,");
        }

        if(user.getPassword().length() < 8 || user.getPassword().length() > 10){
            throw new RuntimeException("비밀번호는 최소 8자리, 최대 10자리입니다.");
        }

        //비밀번호는 영문자, 숫자 혼용이어야 한다.
        int characterCount = 0;
        int numberCount = 0;

        for(int i = 0; i<user.getPassword().length(); i++){

            if(user.getPassword().charAt(i) >= 'a' && user.getPassword().charAt(i) <= 'z'){
                characterCount++;
            } else if (user.getPassword().charAt(i) >= 'A' && user.getPassword().charAt(i) <= 'Z') {
                characterCount++;
            } else if (user.getPassword().charAt(i) >= '0' && user.getPassword().charAt(i) <= '9') {
                numberCount++;
            }
        }

        if(characterCount == 0 || numberCount == 0){
            throw new RuntimeException("비밀번호는 영문자, 숫자가 혼용되어야 합니다.");
        }

        //연락처에 '-'가 있다면 빼준다. (이경우에는 오류가 아닌 걸로 처리)
        user.setPhone(user.getPhone().replace("-",""));

        for (int i = 0 ; i<user.getPhone().length(); i++){
            if(user.getPhone().charAt(i) >= '0' && user.getPhone().charAt(i) <= '9'){
                //정상케이스
            } else{
                //정상이 아닌 케이스
                throw new RuntimeException("연락처는 숫자와 -만 가능합니다.");
            }
        }
        //사용자 요구사항 끝
        userRepository.save(user);

    }








}
