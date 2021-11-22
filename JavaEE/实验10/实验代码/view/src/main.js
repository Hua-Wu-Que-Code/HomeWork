import Vue from 'vue'
import request from "@/network/request";
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import store from "@/store";
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI)


Vue.prototype.$ajax=request
Vue.config.productionTip = false
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
