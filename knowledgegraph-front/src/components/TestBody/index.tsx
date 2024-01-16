import {Layout, Menu, MenuProps} from 'antd';
import React from 'react';
import {useNavigate} from 'react-router-dom';
import {menuItems} from "./data";
import {MenuInfo} from "rc-menu/lib/interface";
import {RouteView} from "./RouteView";
import Sider from 'antd/es/layout/Sider';
import { Content } from 'antd/es/layout/layout';

export const TestBody: React.FunctionComponent = () => {

    // 1.创建Menu组件所需的状态变量
    let [selectedKeys, setSelectedKeys] = React.useState<string[]>([]);
    let [openKeys, setOpenKeys] = React.useState<string[]>([]);

    // 2.创建地址栏URL变化的导航函数（react-router-dom提供）
    const navigate = useNavigate();

    // 3.创建菜单项的点击事件处理函数
    const menuOnClickHandler: MenuProps["onClick"] = (info: MenuInfo) => {
        // 3.1 获取info中的key keyPath属性
        let {key, keyPath} = info;
        // 3.2 设置父菜单的state（此处可能存在一级、二级父菜单）
        if (keyPath.length > 1) {
            setOpenKeys(keyPath.slice(1, keyPath.length))  // 此处的数组元素是逆序的
        }
        // 3.3 设置当前菜单项的state
        setSelectedKeys([key]);
        // 3.4 构建跳转地址
        // 3.4.1 声明初始化变量保存跳转地址
        let urlPath = "";
        // 3.4.2 逆序遍历keyPath
        for (let i = keyPath.length - 1; i >= 0; i--) {
            // 3.4.3 拼接跳转地址
            urlPath += "/" + keyPath[i];
        }
        // 3.4.4 利用navigate函数进行URL跳转
        navigate(urlPath);
    }

    // 4.创建菜单项的展开事件处理函数
    const menuOnOpenChangeHandler: MenuProps["onOpenChange"] = (openKeys: string[]) => {
        // 4.1 更新openKeys状态变量
        setOpenKeys(openKeys);
    }

    // 0.构建返回组件
    return (
        <Layout>
            <Sider width={150}>
                <Menu
                    mode={"inline"}
                    selectedKeys={selectedKeys}
                    openKeys={openKeys}
                    items={menuItems}
                    style={{height: '100%', borderRight: 0}}
                    onClick={menuOnClickHandler}
                    onOpenChange={menuOnOpenChangeHandler}
                />
            </Sider>
            <Layout style={{padding: "10px 10px 10px 10px"}}>
                <Content className={"main-body-content"}>
                    <RouteView/>
                </Content>
            </Layout>
        </Layout>
    );
}
