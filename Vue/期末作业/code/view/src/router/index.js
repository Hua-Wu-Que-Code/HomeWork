import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import commodity from "@/components/commodity";
import books from "@/components/books";
import test from "@/components/test";
import ShoppingCart from "../views/ShoppingCart";
import search from "@/components/search";
import showList from "@/components/showList";
import person from "@/views/person";
import addressList from "@/components/addressList";
import addressEdit from "@/components/addressEdit";
Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    redirect: {path:'category/0',}
  },
  {
    path:'/test',
    component: test
  },
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path: 'category/:key',
        name: 'category',
        component: books
      },
    ]
  },
  {
    path: '/info',
    name: 'Info',
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/Info')
    },
    beforeEnter:(from,to,next) => {
      if (localStorage.getItem("username")) {
        next();
      } else {
        next('/login')
      }
    }
  },
  {
    path: '/login',
    name: 'login',
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/login')
    },
    meta: {
      istrun: true
    }
  },
  {
    path: '/Books//:id',
    name: 'Books',
    component: commodity,
    meta: {
      istrun: true
    }
  },
  {
    path: '/shoppingCart',
    name: 'ShoppingCart',
    component: ShoppingCart,
    beforeEnter:(from,to,next) => {
      if (localStorage.getItem("username")) {
        console.log("嘿嘿")
        next();
      } else {
        next('/login')
      }
    }
  },
  {
    path: '/search',
    name: 'search',
    component: search,
    meta: {
      istrun: true
    }
  },
  {
    path: '/showList',
    name: 'showList',
    component: showList,
    meta: {
      istrun: true
    }
  },
  {
    path: '/person',
    name: 'person',
    component: person,
    meta: {
      istrun: true
    }
  },
  {
    path: '/address',
    name:'address',
    component: addressList,
    meta: {
      istrun: true
    }
  },
  {
    path: '/addressEdit',
    name:'addressEdit',
    component: addressEdit,
    meta: {
      istrun: true
    }
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

router.beforeEach((to,from,next) => {
  //从from跳转到to

  //必须调用
  next();
})

export default router
