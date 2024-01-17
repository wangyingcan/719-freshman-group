import {AppRoutes, RouteType} from "../../data/RouteItems";
import React from "react";
import {Navigate, Outlet, Route, Routes} from "react-router-dom";

// 0.原始方法参考
// const routeList = (routeItems:RouteType[]) => {
//
//     let routes =  routeItems.map((item:RouteType,index:number) => {
//         return (
//             <Route
//                 key = {index}
//                 path = {item.path}
//                 //可选属性
//                 loader = {item.loader?item.loader:undefined}
//                 action = {item.action?item.action:undefined}
//                 element = {
//                     //如果item.component有定义，则渲染item.components
//                     //否则，在<Outlet>里嵌套渲染子路由元素
//                     item.component?(
//                         <React.Suspense fallback={<div>Loading...</div>}>
//                             <item.component/>
//                         </React.Suspense>
//                     ) : (
//                         <div>
//                             <Outlet/>
//                         </div>
//                     )
//                 }
//             >
//                 {/*如果存在item.to, 则重定向到item.to*/}
//                 {item.to && <Route path={item.path} element={<Navigate to={item.to}/>}/>}
//                 {/*如果存在item.children,则嵌套包含子路由*/}
//                 {item.children && routeList(item.children)}
//             </Route>
//         );
//     });
//     return routes;
// };


// 1.将AppRoutes对象转换为Route组件的函数
function routeList(routes: RouteType[]) {
    // 1.1遍历routes形参的时候进行<Route>组件的构建
    return routes.map((route: RouteType) => {
        // 1.2 两类情况：有子路由、无子路由；同时children属性和component属性互斥
        if (route.children !== undefined) {
            // 1.3 有子路由的情况
            return <Route
                // 1.3.1 必有属性的设置
                path={route.path}
                key={route.key}
                // 1.3.2 可选属性的设置：直接对应Route属性的loader、action
                loader={route.loader ? route.loader : undefined}
                action={route.action ? route.action : undefined}
                // 1.3.3 可选属性的设置：对应Route属性的element；加载方式是懒加载
                element={route.to?(
                    <Navigate to={route.to}></Navigate>
                ):route.component? (
                    <React.Suspense fallback={<div>Loading...</div>}>
                        <route.component/>
                    </React.Suspense>
                ):(
                    <div>
                        <Outlet/>
                    </div>
                    )}
            >
                {routeList(route.children)}
            </Route>
        } else {
            // 1.4 无子路由的情况
            return <Route
                // 1.4.1 必有属性的设置
                path={route.path}
                key={route.key}
                // 1.4.2 可选属性的设置：直接对应Route属性的loader、action
                loader={route.loader ? route.loader : undefined}
                action={route.action ? route.action : undefined}
                // 1.4.3 可选属性的设置：对应Route属性的element；加载方式是懒加载
                element={route.to ? (
                    <Navigate to={route.to}/>
                ) : route.component ? (
                        <React.Suspense fallback={<div>Loading...</div>}>
                            <route.component/>
                        </React.Suspense>
                    ) :
                    <div>
                        <Outlet/>
                    </div>
                }
            />
        }
    })
}

// 2.RouteView组件的构建
export const RouteView: React.FunctionComponent = () => {

    return (
        <Routes>
            {/*2.1 重定向到首页的Route组件*/}
            <Route path={"/"} element={<Navigate to="/knowledgeBaseSystem"/>}/>
            {/*2.2 通过AppRoutes对象批量构建Route组件*/}
            {routeList(AppRoutes)}
        </Routes>
    )
}