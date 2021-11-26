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
            network.addGoodItem(id);
        },
        deleteGoodItem(state,id) {

        },
        changeItemFlag(state,id) {

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
        },
        asyncAddGoodItem(context){

        }
    }
})

export default store