package com.example.knowledgeGraph.knowledgeGraph.app.restful;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 测试用的Controller，可以通过postman和浏览器URL进行测试
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
