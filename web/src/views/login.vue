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
<!--          <a-input-search
              v-model:value="value"
              placeholder="input search text"
              size="large"
              @click="sendcode"
          >
            <template #enterButton>
              <a-button>获取验证码</a-button>
            </template>
          </a-input-search>-->
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
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>




</template>
<script>
import { defineComponent, reactive } from 'vue';
export default defineComponent({
  setup() {
    const loginForm = reactive({
      mobile: '18755817542',
      code: '',
      remember: true,
    });
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