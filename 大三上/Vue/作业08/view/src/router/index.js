import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import commodity from "@/components/commodity";
import books from "@/components/books";
import test from "@/components/test";
import ShoppingCart from "../views/ShoppingCart";
Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    redirect: {path:'category/0',}
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
    path: '/test',
    name: 'test',
    component: test,
    meta: {
      istrun: true
    }
  },
  {
    path: '/shoppingCart',
    name: 'ShoppingCart',
    component: ShoppingCart
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
