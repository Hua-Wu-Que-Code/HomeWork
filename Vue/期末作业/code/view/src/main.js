import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import Vant,{Lazyload,Uploader} from 'vant'
import 'vant/lib/index.css'
import network from "./network/index";
import store from "./store";
import './assets/icon/iconfont.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.prototype.$ajax = network;
Vue.config.productionTip = false
Vue.config.devtool=true


Vue.use(ElementUI)
Vue.use(Vant);
Vue.use(Lazyload);
Vue.use(Uploader);


new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
