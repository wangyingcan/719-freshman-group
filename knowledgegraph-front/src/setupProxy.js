// 1.引用中间件用作代理转发
const {createProxyMiddleware} = require('http-proxy-middleware')

// 2.配置代理
module.exports =function(app){
    // 将URL前缀为/api的请求转发给目标服务器
    app.use(createProxyMiddleware("/api",{
        target:"http://localhost:8080",     //转发的目标服务器地址
        pathRewrite:{       //转发请求URL重写,将最前面的URL转换为空字符串
            "^/api":""
        },
        changeOrigin:true        //支持跨域CORS
    }));
}