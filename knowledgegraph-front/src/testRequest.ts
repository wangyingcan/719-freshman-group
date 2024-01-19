import {TestVO} from "./data/testVO";
import {TestAPI} from "./api/apiUrls";
import {request} from "./api/request";

// 1.get方法测试异步方法
export async function asyncTestGet():Promise<TestVO>{
    // 1.1 初始化响应结果
    let result:TestVO={} as TestVO;
    // 1.2 模拟发送前端get请求
    await request.get(TestAPI.TEST_GET_URL).then((data)=>{
        // 1.3 响应成功直接获取response.data,拦截器会将响应成功时的data返回过来
        result=data;
    })
    return result;
}

// 2.post方法测试异步方法
export async function asyncTestPost(userId:string):Promise<TestVO>{
    let result:TestVO={} as TestVO;
    await request.post(TestAPI.TEST_POST_URL,{
        userId:userId
    }).then((data)=>{
        result=data;
    })
    return result;
}