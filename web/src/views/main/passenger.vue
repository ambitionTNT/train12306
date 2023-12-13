<template>

    <div>
      <p>
        <a-space>
          <a-button type="primary" @click="onAdd">新增乘车人</a-button>
          <a-button type="primary" @click="handleQuery()">刷新</a-button>
        </a-space>


      </p>
      <a-table :dataSource="passengerList"
               :columns="columns"
               :pagination="pagination"
               @change="handleTableChange"
               :loading="loading"
      >
        <template #bodyCell="{ column, record }">
          <template v-if="column.dataIndex === 'operation'">
            <a-space>

              <a-button type="primary" @click="onEdit(record)" >
                编辑
              </a-button>
              <a-popconfirm
                  title="删除后不可回复，您确认要删除?"
                  ok-text="Yes"
                  cancel-text="No"
                  @confirm="onDelete(record.id)"
              >
                <a-button type="danger" >
                  删除
                </a-button>
              </a-popconfirm>
            </a-space>

          </template>
          <template v-else-if="column.dataIndex === 'type'">
        <span v-for="item in PASSENGER_TYPE_ARRAY" :key="item.key">
          <span v-if="item.key === record.type">
            {{item.value}}
          </span>
        </span>
          </template>
        </template>
      </a-table>

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
              <a-select-option v-for="item in PASSENGER_TYPE_ARRAY" :key="item.key" :value="item.key">
                {{item.value}}
              </a-select-option>

            </a-select>
          </a-form-item>
        </a-form>
      </a-modal>
    </div>

</template>
<script>
import {defineComponent, ref, onMounted} from "vue";
import axios from "axios";
import {notification} from "ant-design-vue";


export default defineComponent({
  setup(){
    const visible = ref(false);
    const loading = ref(false);
    //表格数据
    const  passengerList = ref([]);
    const pagination = ref({
      total: 0,
      current:1,
      pageSize: 2,
    })
    //表格的各列
    const columns = ref([
      {
        title: '姓名',
        dataIndex: 'name',
        key: 'name',
      },
      {
        title: '身份证',
        dataIndex: 'idCard',
        key: 'idCard',
      },
      {
        title: '类型',
        dataIndex: 'type',
        key: 'type',
      }, {
        title: '操作',
        dataIndex: 'operation',
        key: 'operation',
      }
    ]);
    const PASSENGER_TYPE_ARRAY = window.PASSENGER_TYPE_ARRAY;


    const passenger = ref({
      id: undefined,
      memberId: undefined,
      name: undefined,
      idCard: undefined,
      type: undefined,
      createTime: undefined,
      UpdateTime: undefined,

    });
    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination) => {
      console.log("看看自带的分页参数都有啥：" + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };
    const onAdd = () => {
      //在点击新增的时候清空表单
      passenger.value = {};
      visible.value = true;
    };
    const onEdit = (record) => {
      console.log("编辑的乘车人信息:"+record)
      passenger.value = window.Tool.copy(record);
      console.log("编辑的passenger信息:"+passenger.value)

      visible.value = true;
    };
    /**
     * 新增乘车人
     */
    const handleOk = () => {
      console.log(passenger)
      // passenger

      axios.post("/member/passenger/save", passenger.value).then((response)=>{
        console.log(response);
        const data  = response.data;
        if (data.success){
          notification.success({
            description: "保存成功"}
          )
          visible.value = false;
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          })
        }else {
          notification.error({
            description:data.message
          })
        }
      })
    };
    /**
     * 查询乘车人
     */
    const handleQuery = (params) => {
      if (!params){
        params = {
          page: 1,
          size:pagination.value.pageSize
        }
      }
      loading.value = true;
      axios.get("/member/passenger/list-query",{
            params:{
              page: params.page,
              size: params.size,
            }
      }
         ).then((response)=>{
          let data = response.data;
        loading.value = false;

        if (data.success){
            passengerList.value = data.content.list;
            //设置分页控件的值
            pagination.value.current = params.page
            pagination.value.total = data.content.total;

          }else {
            notification.error({description:data.message});
          }
      })
    }
    /**
     * 删除乘车人
     * @param id
     */
    const onDelete = ( id)=>{
      loading.value = true;
      axios.delete("/member/passenger/delete/" + id
      ).then((response) => {
        const data = response.data;
        if (data.success) {
          handleQuery({
            page:pagination.value.current,
            size: pagination.value.pageSize

          });
          notification.success({description:data.message})
        }else {
          notification.error({description: data.message})
        }
      });
    }
    onMounted(()=>{
      handleQuery({
        page: pagination.value.current,
        size: pagination.value.pageSize
      })
    });

    return{
      visible,
      loading,
      passengerList,
      columns,
      passenger,
      pagination,
      PASSENGER_TYPE_ARRAY,
      onEdit,
      onAdd,
      handleOk,
      handleTableChange,
      handleQuery,
      onDelete
    };
  }
})
</script>
<style>

</style>