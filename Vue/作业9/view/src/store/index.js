import Vue from "vue";
import Vuex from "vuex";
import network from "@/network/index"
import router from '../router';


Vue.use(Vuex)

const store = new Vuex.Store({
    state:{
        goodsList: [],
        total: 0,
        category:[],
        login: false
    },
    mutations: {
        deleteGoodItem(state,id) {

        },
        changeItemFlag(state,id) {

        },
        changeLogin(state,flag) {
            state.login = flag;
        },
        initCategory(state,list) {
            state.category = list;
        },
        initGoodList(state,list) {
            state.goodsList = list;

        }
    },
    actions: {
        asyncInitCategory(context) {
            //初始化导航
            network.get_category()
            .then(res=>{
                context.commit('initCategory',res.data)
            })

        },
        asyncInitShoppingCart(context) {
            //初始化购物车
            if (localStorage.getItem("username")) {
                context.commit('changeLogin',true);
                //初始化购物车
                network.get_shoppingCart()
                    .then(res=>{
                        if (res.code == 100) {
                            context.commit('initGoodList',res.data.items);
                        }
                    })
            } else {
                context.commit('initGoodList',"")
            }
        },
        asyncAddGoodItem(context,id){
            network.addGoodItem(id).then(res => {
                if (res.code == 100) {
                    router.push('/ShoppingCart');
                }
            })
        }
    },
    getters: {

    }
})

export default store