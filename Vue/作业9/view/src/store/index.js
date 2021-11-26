import Vue from "vue";
import Vuex from "vuex";
import network from "@/network/index"
import login from "@/views/login";

Vue.use(Vuex)

const store = new Vuex.Store({
    state:{
        goodsList: [],
        login: false,
        total: 0,
        category:[]
    },
    mutations: {
        addGoodItem(state,id) {

        },
        deleteGoodItem(state,id) {

        },
        changeItemFlag(state,id) {
            for (let i = 0; i < state.goodsList.length;i++) {
                if (state.goodsList[i].id == id) {

                    state.goodsList[i].flag = !state.goodsList[i].flag;
                }
            }
        },
        changeLogin(state,flag) {
            state.login = flag;
        },
        initCategory(state,list) {
            state.category = list;
        }
    },
    actions: {
        asyncInitCategory(context) {
            network.get_category()
            .then(res=>{
                context.commit('initCategory',res.data)
            })
        }
    }
})

export default store