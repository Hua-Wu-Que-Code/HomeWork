import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import Vant,{Lazyload} from 'vant'
import 'vant/lib/index.css'
import request from "./network/request";
import store from "./store";
import axios from "@/plugins/axios";
import mock from './mock'

Vue.prototype.$ajax = request;
Vue.config.productionTip = false
Vue.config.devtool=true



Vue.use(Vant);
Vue.use(Lazyload);


new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
