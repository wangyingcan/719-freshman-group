import {lazy, LazyExoticComponent} from "react";
import {NonIndexRouteObject} from "react-router-dom";

// 1.定义routeItem的类型
export interface RouteType {
    path: string;    //路由匹配路径
    key: string;     //对应菜单项的key属性
    label: string;   //对应菜单项的label属性
    component?: LazyExoticComponent<any>; //渲染的组件，可选是因为非底层菜单项没有component
    to?: string;     //路由跳转路径，重定向时有用，可选是因为目前只有默认路由会进行重定向
    children?: RouteType[]; //子路由项数组，可以任意级嵌套
    loader?: NonIndexRouteObject["loader"]; //该路由对应的loader
    action?: NonIndexRouteObject["action"]; //该路由对应的action
}

// 2.定义路由对象
export const AppRoutes: RouteType[] = [
    // 2.1 3个一级目录：没有component，只有children
    {
        path: '/knowledgeBaseSystem',
        key: 'knowledgeBaseSystem',
        label: '知识库系统',
        // 2.1.1 3个二级目录
        children: [
            {
                path: '/knowledgeBaseSystem/vocabularyManagement',
                key: 'vocabularyManagement',
                label: '词汇管理',
                //component:lazy(() => import('../pages/knowledgeBaseSystem/vocabularyManagement'))
            },
            {
                path: '/knowledgeBaseSystem/textManagement',
                key: 'textManagement',
                label: '文本管理',
                //component:lazy(()=>{import('../pages/knowledgeBaseSystem/textManagement')})
            },
            {
                path: '/knowledgeBaseSystem/attachmentManagement',
                key: 'attachmentManagement',
                label: '附件管理',
                //component:lazy(()=> import('../pages/knowledgeBaseSystem/attachmentManagement'))
            }
        ]
    },
    {
        path: '/knowledgeBaseManagementSystem',
        key: 'knowledgeBaseManagementSystem',
        label: '知识库管理系统',
        // 2.2.1 2个二级目录
        children: [
            {
                path: '/knowledgeBaseManagementSystem/ontologyManagement',
                key: 'ontologyManagement',
                label: '本体管理',
                // 2.2.1.1 2个三级目录
                children: [
                    {
                        path: '/knowledgeBaseManagementSystem/ontologyManagement/tripleManagement',
                        key: 'tripleManagement',
                        label: '三元组管理',
                        //component:lazy(()=>import('../pages/knowledgeBaseManagementSystem/ontologyManagement/tripleManagement'))
                    },
                    {
                        path: '/knowledgeBaseManagementSystem/ontologyManagement/ontologyGraphManagement',
                        key: 'ontologyGraphManagement',
                        label: '本体图谱管理',
                        //component:lazy(()=>import('../pages/knowledgeBaseManagementSystem/ontologyManagement/ontologyGraphManagement'))
                    }
                ]
            },
            {
                path: '/knowledgeBaseManagementSystem/entityManagement',
                key: 'entityManagement',
                label: '实体管理',
                //component:lazy(()=>import('../pages/knowledgeBaseManagementSystem/entityManagement'))
            }
        ]
    },
    {
        path: '/knowledgeBaseRetrievalSystem',
        key: 'knowledgeBaseRetrievalSystem',
        label: '知识库检索系统',
        // 2.3.1 2个二级目录
        children: [
            {
                path: '/knowledgeBaseRetrievalSystem/entityLevelSearch',
                key: 'entityLevelSearch',
                label: '实体级查询',
                //component:lazy(()=>import('../pages/knowledgeBaseRetrievalSystem/entityLevelSearch'))
            },
            {
                path: '/knowledgeBaseRetrievalSystem/entityInternalSearch',
                key: 'entityInternalSearch',
                label: '实体内查询',
                //component:lazy(()=>import('../pages/knowledgeBaseRetrievalSystem/entityInternalSearch'))
            }
        ]
    }
]