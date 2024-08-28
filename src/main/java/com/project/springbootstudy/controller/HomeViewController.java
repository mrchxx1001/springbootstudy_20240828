package com.project.springbootstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 이 클래스는 컨트롤러입니다. 반환을 화면(html)으로 합니다 <-> @RestController //컨트롤러, 반환이 데이터 자체
public class HomeViewController {

    @GetMapping("/") //localhost:8087
    public String gologin(){
        return "user/login"; // 앞에는 templates/ 원하는 화면이름.html
    }
}
