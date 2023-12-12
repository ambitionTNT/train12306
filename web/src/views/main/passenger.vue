<template>

    <div>
      <p>
        <a-button type="primary" @click="showModal">新增乘车人</a-button>

      </p>
      <a-table :dataSource="dataSource" :columns="columns" />

      <a-modal v-model:visible="visible" title="乘车人" @ok="handleOk" okText ="确认" cancelText="取消">
        <a-form
            :model="passenger"
            name="basic"
            :label-col="{ span: 8 }"
            :wrapper-col="{ span: 16 }"
        >
          <a-form-item
              label="姓名"
              name="name"
          >
            <a-input v-model:value="passenger.name"   placeholder="姓名"/>
          </a-form-item>

          <a-form-item
              label="身份证"
              name="idCard"
          >
            <a-input v-model:value="passenger.idCard"   placeholder="身份证"/>
          </a-form-item>

          <a-form-item
              label="类型"
              name="type"
          >
            <a-select
                v-model:value="passenger.type"
            >
              <a-select-option value="1">成人</a-select-option>
              <a-select-option value="2">儿童</a-select-option>
              <a-select-option value="3" >学生</a-select-option>
            </a-select>
          </a-form-item>
        </a-form>
      </a-modal>
    </div>

</template>
<script>
import {defineComponent,  reactive, ref} from "vue";
import axios from "axios";
import {notification} from "ant-design-vue";


export default defineComponent({
  setup(){
    const visible = ref(false);
    //表格数据
    const  dataSource = ref([
      {
        key: '1',
        name: '胡彦斌',
        age: 32,
        address: '西湖区湖底公园1号',
      },
      {
        key: '2',
        name: '胡彦祖',
        age: 42,
        address: '西湖区湖底公园1号',
      },
    ]);

    //表格的各列
    const columns = ref([
      {
        title: '姓名',
        dataIndex: 'name',
        key: 'name',
      },
      {
        title: '年龄',
        dataIndex: 'age',
        key: 'age',
      },
      {
        title: '住址',
        dataIndex: 'address',
        key: 'address',
      },
    ])
    const passenger = reactive({
      id: undefined,
      memberId: undefined,
      name: undefined,
      idCard: undefined,
      type: undefined,
      createTime: undefined,
      UpdateTime: undefined,

    });

    const showModal = () => {
      visible.value = true;
    };
    const handleOk = () => {
      console.log(passenger)
      axios.post("/member/passenger/save", passenger).then((response)=>{
        console.log(response);
        const data  = response.data;
        if (data.success){
          notification.success({
            description: "保存成功"}
          )
          visible.value = false;

        }else {
          notification.error({
            description:data.message
          })
        }
      })
    };
    return{
      visible,
      dataSource,
      columns,
      passenger,
      showModal,
      handleOk,
    };
  }
})
</script>
<style>

</style>