import { Layout } from 'antd';
import React from 'react';
import './App.css';
import TestHeader from "./components/test";

const App: React.FunctionComponent = () => {
    /*测试一下antd组件是否可以引入*/
    return (
        <div>
          <Layout>
            <TestHeader/>
          </Layout>
        </div>
    );
}

export default App;
