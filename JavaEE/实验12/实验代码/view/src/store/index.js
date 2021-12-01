import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        Tflag: true
    },
    mutations: {
        layOut(state,flag) {
            console.log(flag)
            state.Tflag = flag;
        }
    }
})

export default store
