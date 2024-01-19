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
        // 1.1 初始化VO对象，最后需要作为响应体中data属性进行返回
        TestVO testVO = new TestVO();
        // 1.2 跳过service、dao层，直接设置测试属性
        testVO.setUserId("123");
        testVO.setUserName("wangyingcan");
        // 1.3 封装响应体
        return new CommonResult<TestVO>().makeSuccess().addData(testVO).addMessage("get方法测试成功");
    }

    // 2.post方法的测试案例
    @PostMapping("/post")
    // 2.1 @RequestBody注解表示从请求体中获取query对象
    public CommonResult<TestVO> post(@RequestBody TestPostQuery testPostQuery) {
        // 2.2 判断是否获取到了请求体的Query对象！！！
        if (testPostQuery != null) {
            // 2.3 查询数据库，判断是否存在该用户
            if (testPostQuery.getUserId().equals("123")) {
                TestVO result = new TestVO("wangyingcan", "123");
                // 2.4 封装响应体
                return new CommonResult<TestVO>().makeSuccess().addMessage("post方法成功").addData(result);
            } else {
                // 2.5 封装失败响应体
                return new CommonResult<TestVO>().makeFail().addMessage("post方法失败");
            }
        }
        // 2.6 封装失败响应体
        return new CommonResult<TestVO>().makeFail().addMessage("post方法失败,没有成功获取参数!!!");
    }
}
