<template>
  <a-layout-header class="header" id="components-layout-demo-top-side-2">
    <div class="logo" />
    <div style="float: right;color:white;">
      您好：{{member.mobile}}
      <router-link to="/login" style="color: white;">
        退出登录
      </router-link>
    </div>
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <robot-outlined />&nbsp; 欢迎
        </router-link>
      </a-menu-item>
      <a-menu-item key="/passenger">
        <router-link to="/passenger">
          <user-outlined/>&nbsp; 乘车人管理
        </router-link>
      </a-menu-item>

    </a-menu>

  </a-layout-header>
</template>
<script>
import {defineComponent, ref} from "vue";
import store from "@/store";

export default defineComponent({
  name: 'the-header',
  setup(){

    let member = store.state.member

    return{
      selectedKeys1: ref(['2']),
      member
    };
  }
})
</script>
<style>

</style>