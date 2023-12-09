import { createStore } from 'vuex'
export default createStore({
  state: {
    //相当于Java的实体类
    member: {}
  },
  getters: {
  },
  mutations: {
    setMember (state, member){
      state.member = member
    }
  },
  actions: {
  },
  modules: {
  }
})
