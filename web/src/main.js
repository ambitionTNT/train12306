import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd, {notification} from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import * as Icons from '@ant-design/icons-vue';
import axios from "axios";
import './assets/js/enums';





const app = createApp(App)
app.use(store).use(router).use(Antd).mount('#app')
const icons = Icons;
for (const i in icons) {
    app.component(i, icons[i]);
}

axios.interceptors.request.use(function (config) {
    console.log('请求参数:',config);
    //拿到session的登录的会员的token，如果有值就往请求的token加入token即可。

    const token = store.state.member.token;
    if (token){
        config.headers.token = token;
        console.log("请求headers增加token:", token);
    }
    return config
}, error => {
    return Promise.reject(error)
});

axios.interceptors.response.use(function (response){
    console.log('返回结果:',response);
    return response
},error => {
    console.log('返回的错误:',error);
    const response = error.response;
    const  status = response.status;
    if (status===401){
        //判断状态吗是401 跳转到登录页面
        console.log("未登录或者登录超时，跳转到登录页面");
        store.commit("setMember", {});
        notification.error({description:"未登录或登录超时"});
        router.push('/login');
    }
    return Promise.reject(error)
})


//测试 环境配置
console.log('环境:' , process.env.NODE_ENV)
console.log('服务端:' , process.env.VUE_APP_SERVE)


axios.defaults.baseURL = process.env.VUE_APP_SERVE