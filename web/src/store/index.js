import { createStore } from 'vuex'

//定义一个常量作为往session放东西的key
const MEMBER = "MEMBER";
export default createStore({
  state: {
    //相当于Java的实体类,意思是说 到缓存中读取，没有就赋值一个空对象，以免造成空指针异常
    member: window.SessionStorage.get(MEMBER)||{}
  },
  getters: {
  },
  mutations: {
    setMember (state, member){
      state.member = member
      window.SessionStorage.set(MEMBER, member)
    }
  },
  actions: {
  },
  modules: {
  }
})
