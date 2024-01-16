import { MenuProps } from 'antd';
import MenuItem from 'antd/es/menu/MenuItem';
import {AppRoutes, RouteType} from '../../data/RouteItems';

// 0.定义菜单项类型
type MenuItem =Required<MenuProps>['items'][number];

// 1.将AppRoutes对象转换为MenuItem对象的函数
function transform(routes:RouteType[]):MenuProps['items']{
    // 1.1 遍历形参数组元素(route)时，构建出返回的对象(item)
    return routes.map((route:RouteType)=>{
        // 1.2 声明空的item对象
        let item : MenuItem;

        //1.4 两类route：一类是有children属性的，一类是没有children属性的
        if(route.children) {
            // 1.4.1 有children属性时
            item = {
                key: route.key,
                label: route.label,
                children: transform(route.children)
            }
        }else{
            // 1.4.2 没有children属性时
            item={
                key: route.key,
                label: route.label,
            }
        }
        // 1.3 先将返回值写好，再填充构建item的逻辑
        return item;
    });
}

// 2.将AppRoutes对象转换为RouteItem对象
export const menuItems = transform(AppRoutes);
