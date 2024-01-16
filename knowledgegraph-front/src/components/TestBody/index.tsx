import {Menu, MenuProps} from 'antd';
import React from 'react';
import {useNavigate} from 'react-router-dom';
import {menuItems} from "./data";

export const TestBody: React.FunctionComponent = () => {

    // 1.创建Menu组件所需的状态变量
    let [selectedKeys, setSelectedKeys] = React.useState<string[]>([]);
    let [openKeys, setOpenKeys] = React.useState<string[]>([]);

    // 2.创建地址栏URL变化的导航函数（react-router-dom提供）
    const navigate = useNavigate();

    // 3.创建菜单项的点击事件处理函数
    const menuOnClickHandler: MenuProps["onClick"] = () => {
    }

    // 4.创建菜单项的展开事件处理函数
    const menuOnOpenChangeHandler:MenuProps["onOpenChange"]=()=>{

    }

    // 0.构建返回组件
    return (
        <Menu
            mode={"inline"}
            selectedKeys={selectedKeys}
            openKeys={openKeys}
            items={menuItems}
            onClick={menuOnClickHandler}
            onOpenChange={menuOnOpenChangeHandler}
        />
    );
}