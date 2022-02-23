import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import Vant,{Lazyload} from 'vant'
import 'vant/lib/index.css'
import network from "./network/index";
import store from "./store";

Vue.prototype.$ajax = network;
Vue.config.productionTip = false
Vue.config.devtool=true



Vue.use(Vant);
Vue.use(Lazyload);


new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
