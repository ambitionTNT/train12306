<template>

  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center" ><rocket-two-tone></rocket-two-tone>&nbsp;TNT12306售票系统</h1>
      <a-form
          :model="loginForm"
          name="basic"


          @finish="onFinish"
          @finishFailed="onFinishFailed"
      >
        <a-form-item
            label=""
            name="mobile"
            :rules="[{ required: false, message: '请输入手机号!' }]"
        >
          <a-input v-model:value="loginForm.mobile" placeholder="手机号" size="large" />
        </a-form-item>

        <a-form-item
            label=""
            name="code"
            :rules="[{ required: false, message: '请获取手机验证码' }]"
        >
          <a-input
              v-model:value="loginForm.code"
              size="large"
          >
            <template #addonAfter>
              <a @click="sendCode">获取验证码</a>
            </template>
          </a-input>
        </a-form-item>


        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
<!--          <a-button type="primary" html-type="submit">登录</a-button>-->
          <a-button type="primary" @click="login">登录</a-button>
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>




</template>
<script>
import { defineComponent, reactive, h } from 'vue';
import { SmileOutlined } from '@ant-design/icons-vue';
import { notification } from 'ant-design-vue';
import {useRouter} from "vue-router";
import axios from 'axios';
import store from "@/store";
export default defineComponent({
  setup() {
    const route = useRouter();
    const loginForm = reactive({
      mobile: '18755817542',
      code: ''
    });



    const sendCode = () => {
      axios.post("/member/member/sendCode",
          {mobile: loginForm.mobile}).then((response)=>{
        console.log(response);
          const data  = response.data;
          if (data.success){
            notification.open({
              message: '发送短信验证码成功',
              description: ' 恭喜您发送短信验证码成果，请注意查收手机',
              icon: () => h(SmileOutlined, {
                style: 'color: #108ee9',

              })})
            loginForm.code="8888"
          }else {
            console.log("cuowu ")
          }
      })
    };

    const login = () => {
      console.log(loginForm)
      axios.post("/member/member/login", loginForm).then((response)=>{
        console.log(response);
        const data  = response.data;
        if (data.success){
          notification.open({
            message: '登录成功',
            description: ' 恭喜您登录成功，请耐心等待页面加载。。。',
            icon: () => h(SmileOutlined, {
              style: 'color: #108ee9',

            })})
          console.log("登录成功:", data.content)
          //跳转主页
          route.push("/welcome")
          //把混悬信息存入全局变量member中
          store.commit("setMember", data.content);
        }else {
          notification.error({
            description:data.message
          })
        }
      })
    };
    const onFinish = values => {
      console.log('Success:', values);
    };
    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo);
    };
    return {
      loginForm,
      onFinish,
      onFinishFailed,
      sendCode,
      login
    };
  },
});
</script>
<style>
  .login-main h1{
    font-size: 25px;
    font-weight: bold;
  }
  .login-main{
    margin-top: 100px;
    padding: 30px 30px 30px;
    border: 2px solid gray;
    border-radius: 10px;
    background-color: #fcfcfc;
  }

</style>