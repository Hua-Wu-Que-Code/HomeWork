<template>
  <div id="app">
    <router-view/>
    <div id="footer" v-if="!$route.meta.istrun">
      <van-tabbar v-model="active"  route v-if="!$route.meta.istrun">
        <van-tabbar-item replace to="/category/0" name="home" icon="home-o">首页</van-tabbar-item>
        <van-tabbar-item replace to="/ShoppingCart" name="shoppingCart" icon="shopping-cart-o">购物车</van-tabbar-item>
        <van-tabbar-item replace to="/info" name="Info" icon="contact">我的书城</van-tabbar-item>
      </van-tabbar>
    </div>
  </div>
</template>

<script>
import {mapActions,mapState} from 'vuex'
export default {
  data() {
    return{
      active:"home",
    }
  },
  created() {
    this.asyncInitCategory();
  },
  methods:{
    ...mapActions({asyncInitCategory:'asyncInitCategory'}),
  }
}
</script>
<style>

</style>
