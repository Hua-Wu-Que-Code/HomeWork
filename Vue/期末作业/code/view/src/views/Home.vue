<template>
  <div class="home">
    <div id="nav">
      <van-search v-model="value" placeholder="商品搜索" @click="onSearch" />
    </div>
    <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
      <van-swipe-item v-for="(image, index) in images" :key="index">
        <img class="sImage" v-lazy="image" />
      </van-swipe-item>
    </van-swipe>
    <van-grid :column-num="3" :gutter="10">
      <van-grid-item :class="{active: ifActive(index) }" v-for="(value,index) in category" :key="index" icon="photo-o" :text="value.name" @click="hand(value.id , index)"/>
    </van-grid>
    <router-view :key="$route.path" />
  </div>
</template>

<script>
// @ is an alias to /src
import { Toast } from 'vant';
import {mapActions, mapState} from "vuex";

export default {
  name: 'Home',
  data() {
    return{
      value:'',
      flag:true,
      tid:0,
      images: [
        require("/public/slider/1.jpg"),
        require("/public/slider/2.jpg"),
        require("/public/slider/3.jpg"),
        require("/public/slider/4.jpg"),
        require("/public/slider/5.jpg"),
      ],
    }
  },
  created: function () {
    },
  computed: {
    ...mapState({category:'category'})
  },
  methods: {
    onSearch() {
      this.$router.push({name:'search'})
    },
    onChange(index) {
      Toast('当前 Swipe 索引：' + index);
    },
    hand(value,index) {
      const key = index;
      this.tid = index;
      this.$router.push({name:"category",params:{key}});
    },
    ifActive(index) {
      if (index === this.tid) return true;
      return false;
    }
  }
}
</script>
<style>
#nav {
  height: 60px;
}

.home {
  text-align: center;
}

.my-swipe .van-swipe-item {
  color: #fff;
  font-size: 20px;
  height:162px;
}
.sImage {
  width: 100%;
  height: 100%;
}
.active {
  color: #3a8ee6;
}

</style>
