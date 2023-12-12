<template>
  <a-layout-sider width="200" style="background: #fff">
    <a-menu

        mode="inline"
        :style="{ height: '100%', borderRight: 0 }"
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
  </a-layout-sider>
</template>


<script>
import {defineComponent, ref} from "vue";

export default defineComponent({

  name: 'the-sider',
  setup(){
    return{
      selectedKeys2: ref(['1']),
      openKeys: ref(['sub1'])
    };
  }
})
</script>
<style>

</style>