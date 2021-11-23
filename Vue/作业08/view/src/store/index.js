import Vue from "vue";
import Vuex from "vuex";
import axios from "axios"
import login from "@/views/login";

Vue.use(Vuex)

const store = new Vuex.Store({
    state:{
        goodsList: [
            {
                "id": 1,
                "name": "东野圭吾：白夜行（2017版）",
                "author": "[日]东野圭吾",
                "price": 59.6,
                "img": "/image/1.jpg",
                "category": "侦探/悬疑/推理",
                "flag":false,
                "num":0
            },
            {
                "id": 13,
                "name": "红岩",
                "author": "罗广斌，杨益言",
                "price": 25.0,
                "img": "/image/13.jpg",
                "category": "军事",
                "flag":false,
                "num":0
            },
            {
                "id": 25,
                "name": "无尽世界（套装全3册）(通宵小说大师肯·福莱特中世纪三部曲）",
                "author": "[英]肯·福莱特",
                "price": 188.0,
                "img": "/image/25.jpg",
                "category": "历史",
                "flag":false,
                "num":0
            },
            {
                "id": 37,
                "name": "中国科幻基石丛书：三体（套装1-3册）",
                "author": "刘慈欣",
                "price": 93.0,
                "img": "/image/37.jpg",
                "category": "科幻",
                "flag":false,
                "num":0
            },
            {
                "id": 49,
                "name": "北欧众神",
                "author": "[英]尼尔·盖曼",
                "price": 42.0,
                "img": "/image/49.jpg",
                "category": "魔幻/奇幻/玄幻",
                "flag":false,
                "num":0
            }]
        ,
        name: "hello",
        login: false,
        total: 0
    },
    mutations: {
        addGoodItem(state,id) {
            for (let i = 0;i < state.goodsList.length; i++) {
                if (state.goodsList[i].id === id) {
                    state.goodsList[i].num ++;
                }
            }
        },
        deleteGoodItem(state,id) {
            for (let i = 0;i < state.goodsList.length; i++) {
                if (state.goodsList[i].id === id) {
                    state.goodsList[i].num = 0;
                }
            }
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
        countTotal(state) {
            for (let i = 0;i < state.goodsList.length; i++) {
                if (state.goodsList[i].flag) {
                    state.total += (state.goodsList[i].num * state.goodsList[i].price);
                }
            }
        }
    },
    actions: {

    }
})

export default store