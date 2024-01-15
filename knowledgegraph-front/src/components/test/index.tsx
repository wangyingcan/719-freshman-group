import React from "react";
import {Header} from "antd/es/layout/layout"
import styles from "./index.module.css"

const TestHeader:React.FunctionComponent =()=>{

    // 利用css module的方式引入样式
    return <Header className={styles["main-header"]}>
        测试header
    </Header>
}

export default TestHeader;