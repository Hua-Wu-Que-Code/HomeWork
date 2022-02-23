import Vue from "vue";
import Vuex from "vuex";
import network from "@/network/index"
import router from '../router';


Vue.use(Vuex)

const store = new Vuex.Store({
    state:{
        goodsList: [], //购物车
        books: [[],[],[],[],[],[]], //瀑布式加载数据
        category:[],
        login: false
    },
    mutations: {
        changeAllItemFlag(state,flag) {
            for (let i =0;i<state.goodsList.length;i++) {
                state.goodsList[i].check = flag;
            }
        },
        changeLogin(state,flag) {
            state.login = flag;
        },
        initCategory(state,list) {
            state.category = list;
        },
        initGoodList(state,list) {
            for (let i =0;i<list.length;i++) {
                list[i].check = false;
            }
            state.goodsList = list;
        },
        initBooks(state,playLoad) {
            state.books[playLoad.key] = playLoad.list;
        }
    },
    actions: {
        asyncInitCategory(context) {

            if (localStorage.getItem("username")) {
                context.commit('changeLogin',true);
            }
            //初始化导航
            network.get_category()
            .then(res=>{
                context.commit('initCategory',res.data)
            })

        },
        //瀑布式加载数据
        asyncInitCategoryBooksWater(context,playLoad) {
            context.commit('initBooks',playLoad);
        },
        asyncInitShoppingCart(context) {
            //初始化购物车
            network.get_shoppingCart()
                .then(res=>{
                    if (res.code == 100) {
                        if (res.data == null) {
                            context.commit('initGoodList',"")
                        } else {
                            context.commit('initGoodList',res.data.items);
                        }
                    }
                })
        },
        asyncAddGoodItem(context,id){
            network.addGoodItem(id).then(res => {
                if (res.code == 100) {
                    router.push('/ShoppingCart');
                }
            })
        },
        asyncDeleteGoodItem(context,id) {
            network.deleteGoodItem(id).then(res => {
                if (res.code == 100) {
                    context.dispatch('asyncInitShoppingCart')
                }
            })
        }
    },
    getters: {

    }
})

export default store