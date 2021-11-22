import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/components/Login";
import Home from '@/views/layout'
import proFile from "@/views/proFile";
import ToLogin from "@/components/ToLogin";
import writer from "@/views/BlogWrite"
import blogs from "@/views/blogs";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'blogs',
    component: blogs
  },
  {
    path:'/login',
    name:'login',
    component: Login
  },
  {
    path: '/Home',
    name: 'Home',
    component: Home,
    redirect:'/profile',
    children: [
      {
        path:'/profile',
        name:'profile',
        component:proFile
      },{
        path:'/person',
        name:'person',
        component: () => import('../views/Person')
      },
      {
        path: '/write',
        name: 'write',
        component: writer
      }
    ]
  },
  {
    path: '/ToLoGin',
    name: 'ToLoGin',
    component: ToLogin
  }
]

const originalPush = VueRouter.prototype.push

VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
