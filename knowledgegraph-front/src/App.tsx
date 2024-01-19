import { Layout } from 'antd';
import React, { useEffect } from 'react';
import './App.css';
import TestHeader from "./components/TestHeader";
import {TestBody} from "./components/TestBody";
import {asyncTestGet, asyncTestPost} from "./testRequest";

const App: React.FunctionComponent = () => {
    // 1.测试前后端连通性的状态变量
    const [userid, setUserid] = React.useState<string>("");
    const [userName,setUserName] = React.useState<string>("");

    // 2.初始化的钩子函数
    useEffect(()=>{
        //getTest()
        postTest()
    },[])

    //3.异步方法
    const getTest=async()=>{
        await asyncTestGet().then((res)=>{
            setUserid(res.userId);
            setUserName(res.userName);
        })
    }

    const postTest =async()=>{
        await asyncTestPost("123").then((res)=>{
            setUserid(res.userId);
            setUserName(res.userName)
        })
    }

    /*测试一下antd组件是否可以引入*/
    return (
        <div>
            <h3>{userid}</h3>
            <h3>{userName}</h3>
          <Layout>
            <TestHeader/>
            <TestBody/>
          </Layout>
        </div>
    );
}

export default App;
