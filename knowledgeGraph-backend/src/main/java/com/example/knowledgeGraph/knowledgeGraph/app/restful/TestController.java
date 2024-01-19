package com.example.knowledgeGraph.knowledgeGraph.app.restful;


import com.example.knowledgeGraph.knowledgeGraph.app.entity.TestPostQuery;
import com.example.knowledgeGraph.knowledgeGraph.app.vo.CommonResult;
import com.example.knowledgeGraph.knowledgeGraph.app.vo.TestVO;
import org.springframework.web.bind.annotation.*;

// 测试用的Controller，可以通过postman和浏览器URL进行测试
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // 1.get方法的测试案例:直接返回
    @GetMapping("/get")
    public CommonResult<TestVO> get() {
        TestVO testVO = new TestVO();
        testVO.setUserId("123");
        testVO.setUserName("wangyingcan");
        return new CommonResult<TestVO>().makeSuccess().addData(testVO);
    }

    // 2.post方法的测试案例
    @PostMapping("/post")
    public CommonResult<TestVO> post(@RequestBody TestPostQuery testPostQuery) {
        if (testPostQuery != null) {
            if (testPostQuery.getUserId().equals("123")) {
                TestVO result = new TestVO("wangyingcan", "123");
                return new CommonResult<TestVO>().makeSuccess().addMessage("post方法成功").addData(result);
            } else {
                return new CommonResult<TestVO>().makeFail().addMessage("post方法失败");
            }
        }
        return new CommonResult<TestVO>().makeFail().addMessage("post方法失败,没有参数!!!");
    }
}
