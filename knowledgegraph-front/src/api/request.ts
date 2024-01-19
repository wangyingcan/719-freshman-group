import axios, {AxiosError, AxiosRequestConfig, AxiosRequestHeaders, AxiosResponse} from "axios"
import {message as Message} from "antd";
import {API_PREFIX} from "../data/constants";

// 1.RESTFUL请求发送方法的request对象,所有类型的请求仅有URL是必需的
export const request = {
    // 1.1 GET方法,axios中get方法没有请求体的data参数,T为响应体的数据类型
    get<T = any>(url: string, config?: AxiosRequestConfig): Promise<T> {
        // 1.2 设置端口转发的预留URL转换(后面设置apiPrefix)
        url = API_PREFIX + url;
        // 1.3 返回axios.get方法的处理结果
        return axios.get(url, processAxiosConfig(config));
    },

    // 1.2 POST方法
    post<T = any>(url: string, data?: object, config?: AxiosRequestConfig): Promise<T> {
        url = API_PREFIX +  url;
        return axios.post(url, data, processAxiosConfig(config));
    },

    // 1.3 PUT方法
    put<T = any>(url: string, data?: object, config?: AxiosRequestConfig): Promise<T> {
        url = API_PREFIX +  url;
        return axios.put(url, data, processAxiosConfig(config));
    },

    // 1.4 DELETE方法
    delete<T = any>(url: string, config?: AxiosRequestConfig): Promise<T> {
        url = API_PREFIX +  url;
        return axios.delete(url, processAxiosConfig(config));
    }
}

// 2.axios响应拦截器:判断响应的状态码,这个方法中接受到响应执行then、catch方法之前自动执行
axios.interceptors.response.use((response: AxiosResponse) => {      //参数就是响应
        // 2.1 获取后端返回响应体中的  状态码code、消息message、响应体数据data
        const {code, message, data} = response.data;

        // 2.2 根据响应体中的code进行不同处理
        // 2.2.1 响应成功状态码
        if (code == 200) {
            // 返回响应体中的数据
            return data;
        }
        // 2.2.2 用户未登陆状态码
        else if (code == 401) {
            // 重定向到登陆页面
        }
        // 2.2.3 响应失败的其他状态码
        else {
            // antd弹窗提示响应失败的Message
            Message.error(message);
            return Promise.reject(message);
        }
    }, (error: AxiosError) => {
        // 2.2.3 请求出错
        Message.error(error.message + error.response?.status);
        return Promise.reject(error);
    }
)

// 3.axios请求config封装方法
// 3.1 请求的config类型接口
interface AdaptAxiosRequestConfig extends AxiosRequestConfig {
    headers: AxiosRequestHeaders;
}

//3.2 请求的config封装方法:将请求的axios请求config类型转换为自定义的AdaptAxiosRequestConfig类型
export function processAxiosConfig(config: AxiosRequestConfig | undefined): AxiosRequestConfig {        // 这里有使用继承特点,派生类对象可以用父类对象接收
    // 3.2.1 声明待返回的config
    let newConfig: AdaptAxiosRequestConfig = {} as AdaptAxiosRequestConfig;

    // 3.2.2 只对config非空且无header的情况进行处理
    if (config) {
        //3.2.3 将config原来的内容复制到newConfig对象中
        newConfig = {
            ...config
        } as AdaptAxiosRequestConfig;

        // 3.2.4 对于config中headers属性非空,将其复制到newConfig中
        newConfig.headers = {
            ...config.headers
        } as AxiosRequestHeaders;

        // 3.2.5 对于config中无headers属性,设置headers属性为空
        newConfig.headers = {} as AxiosRequestHeaders;
    }

    // 3.2.6 返回newConfig
    return newConfig;
}
