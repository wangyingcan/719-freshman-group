import { Layout } from 'antd';
import React from 'react';
import './App.css';
import TestHeader from "./components/TestHeader";
import {TestBody} from "./components/TestBody";

const App: React.FunctionComponent = () => {
    /*测试一下antd组件是否可以引入*/
    return (
        <div>
          <Layout>
            <TestHeader/>
            <TestBody/>
          </Layout>
        </div>
    );
}

export default App;
