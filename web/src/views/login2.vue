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
          <a-button type="primary" html-type="submit">登录</a-button>
<!--          <a-button type="primary" @click="login">登录</a-button>-->
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>




</template>
<script>
import {defineComponent, h, reactive} from 'vue';
import axios from "axios";
import {notification} from "ant-design-vue";
import {SmileOutlined} from "@ant-design/icons-vue";

export default defineComponent({
  setup() {

    const loginForm = reactive({
      mobile: '18755817542',
      code: '8888'
    });


/*    const login = () => {
      console.log(loginForm)
      axios.post("http://localhost:8000/member/member/login", loginForm).then((response)=>{
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
        }else {
          notification.error({
            description:data.message
          })
        }
      })
    };*/
    const onFinish = (values) => {
      axios.post("http://localhost:8000/member/member/login", {mobile:values.mobile,code:values.code}).then((response)=>{
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
        }else {
          notification.error({
            description:data.message
          })
        }
      })
      console.log('Success2222:', values);

    };
    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo);
    };
    return {
      loginForm,
      onFinish,
      onFinishFailed,
/*      sendCode,
      login*/
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