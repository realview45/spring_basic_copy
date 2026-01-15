package com.beyond.basic.b1_basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController{
    //3가지 get요청에대한 response
    // 1)text
    // 2)json
    // 3)html
    //1)
    @GetMapping("/textReturn")
    @ResponseBody
    public String textReturn(){
        return "hongildong";
    }
    //2)
    @GetMapping("/jsonReturn")
    @ResponseBody
    public Member jsonReturn(){
        Member m = new Member("hong",30);
        return m;
    }
    //3)
    @GetMapping("/htmlReturn")
    public String htmlReturn(){
        return "abc";
    }


    // 2가지 get요청 받기(start line의 url에서 데이터추출)패턴
    // 1)pathVariable일때
    // 2)queryParameter일때

    // 1)
    @GetMapping("/path-variable/{id}")
    @ResponseBody
    public String path(@PathVariable Long id){
        System.out.println(id);
        return "ok";
    }
    // 2-1)매개변수 한개
    //http://localhost:8081/member/query-parameter?name=hong
    @GetMapping("/query-parameter")
    @ResponseBody
    public String param1(@RequestParam(value="name") String name){
        System.out.println(name);
        return "ok";
    }
    // 2-2)매개변수 두개
    //http://localhost:8081/member/query-parameter2?name=hong&age=30
    @GetMapping("/query-parameter2")
    @ResponseBody
    public String param2(@RequestParam(value="name") String name,
                         @RequestParam(value="age") Integer age){
        System.out.println(name);
        System.out.println(age);
        return "ok";
    }
    // 2-3)매개변수 여러개 -> 객체로 데이터 추출
    //http://localhost:8081/member/query-parameter3?name=hong&age=30
    //파싱시에 getter, 기본생성자 필요! lombok라이브러리
    @GetMapping("/query-parameter3")
    @ResponseBody       //데이터바인딩(데이터묶기)
    public String param3(@ModelAttribute Member member){
        System.out.println(member);
        return "ok";
    }

    // 3가지 post요청에서 (body의 데이터 추출) 패턴
    // 1)url-encoded
    // 2)multipart-formdata
    // 3)json
    //1)
    //http://localhost:8081/member/url-encoded
    @PostMapping("/url-encoded")
    @ResponseBody
    public String urlEncoded(@RequestParam(value="name")String name,
                             @RequestParam(value="age")Integer age){
        System.out.println(name);
        System.out.println(age);
        return "ok";
    }
    //1-2) 객체로 데이터 추출
    //http://localhost:8081/member/url-encoded2
    //http파라미터데이터 파싱시 setter필요..
    @PostMapping("/url-encoded2")
    @ResponseBody
    public String urlEncoded2(@ModelAttribute Member member){
        System.out.println(member);
        return "ok";
    }
    //2-1)
    @PostMapping("/multipart-formdata1")
    @ResponseBody
    public String multiPartFormData1(@ModelAttribute MImage member){
        System.out.println(member.getName());
        System.out.println(member.getEmail());
        System.out.println(member.getProfileImage().getOriginalFilename());
        return "ok";
    }
    //2-2)
    @PostMapping("/multipart-formdata2")
    @ResponseBody
    public String multiPartFormData2(@ModelAttribute MImage member, @RequestParam(value="images")List<MultipartFile>list){
        System.out.println(member.getName());
        System.out.println(member.getEmail());
        System.out.println(member.getProfileImage().getOriginalFilename());
        System.out.println(list.size());
        return "ok";
    }
    //3-1)
    //{"name":"hong","age":30}
    @PostMapping("/json")
    @ResponseBody
    public String singleJson(@RequestBody Member member){
        System.out.println(member);
        return "ok";
    }
    //3-2)
    //[{"name":"hong","age":30},{"name":"hong","age":33},{"name":"hong","age":35}]
    @PostMapping("/jsonList")
    @ResponseBody
    public String listJson(@RequestBody List<Member> member){
        System.out.println(member);
        return "ok";
    }
    //3-3)
    //{"name":"hong","age":30,"scores" : [{"subject" : "math", "score":30}, {"subject" : "physics", "score":100}]}
    @PostMapping("/doubleJson")
    @ResponseBody
    public String doubleJson(@RequestBody Student member){
        System.out.println(member);
        return "ok";
    }
    //4)json과이미지 같이요청옴
    //
    @PostMapping("/multiJson")
    @ResponseBody
    public String multiJson(@RequestPart MultipartFile file, @RequestPart Student member){
        System.out.println(file.getOriginalFilename());
        System.out.println(member);
        return "ok";
    }

}