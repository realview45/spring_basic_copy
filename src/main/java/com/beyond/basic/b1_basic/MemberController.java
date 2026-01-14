package com.beyond.basic.b1_basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//    get요청 return의 case : text, json, html(mvc)
@Controller//실무에서는 RestController를 사용할것
@RequestMapping("member/")
public class MemberController{
//    //@GetMapping("member/info/1")
//    @GetMapping("info/1")
//    @ResponseBody //ResponseBody가 없으면 templates밑에 html파일 찾을것임?
//    public String textReturn(){
//        return "textReturn";
//    }
//
//    @GetMapping("info/json")
//    @ResponseBody
//    public Member jsonReturn(){
//        return new Member("김진경", 30);
//    }

    @GetMapping("info/html")
    public String htmlReturn(){
        return "abc";
    }
}