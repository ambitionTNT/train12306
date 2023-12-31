import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import store from "@/store";
import {notification} from "ant-design-vue";

const routes = [
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/',
    name: 'main',
    component: () => import('../views/main.vue')
    ,meta:{
      loginRequire: true
    },
    children:[{
      path: 'welcome',
      component:()=>import('../views/main/welcome.vue')
    },
      {
        path: 'passenger',
        component:()=>import('../views/main/passenger.vue')
      },
    ]
  },
  {
    path: '',
    redirect: '/welcome'
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    ,meta:{
      loginRequire: true
    }
  }
  ,{
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/login.vue')

  }
  ,{
    path: '/login2',
    name: 'login2',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/login2.vue')
    ,meta:{
      loginRequire: true
    }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
// 路由登录拦截
router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if (to.matched.some(function (item) {
    console.log(item, "是否需要登录校验：", item.meta.loginRequire);
    return item.meta.loginRequire
  })) {
    const loginUser = store.state.member;
    if (!loginUser.token){
      console.log("用户为登录或登录超时！");
      notification.error({description:"未登录或登录超时"});

      next('/login')
    }else {
      next()
    }
  } else {
    next();
  }
});
export default router
