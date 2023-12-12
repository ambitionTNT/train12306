<template>
  <a-layout>
      <the-header></the-header>
    <a-layout>
      <the-sider></the-sider>
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          <router-view></router-view>
        </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
import {defineComponent, ref} from 'vue';
import TheHeader from "@/components/the-header.vue";
import TheSider from "@/components/the-sider.vue";
import axios from "axios";
import {notification} from "ant-design-vue";

export default defineComponent({
  components: {
    TheSider,
    TheHeader,
  },
  setup() {
    const count = ref();

    axios.post("/member/member/count").then((response)=>{
      const data  = response.data;
      if (data.success){
        count.value = data.content;
      }else {
        console.log("cuowu ")
        notification.error({description: data.message})
      }
    })
    return {
      count,
    };
  },
});
</script>
<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

</style>