<template>
  <div id="info">
    <van-nav-bar
        title="个人中心"
        @click-right="onClickRight"
    >
      <template #right>
        <van-icon name="setting-o" size="18"/>
      </template>
    </van-nav-bar>
    <div class="cell">
      <van-image
          round
          width="80px"
          height="80px"
          src="/icons/avatar_default.png"
          class="t"
      />
      <div class="name">{{msg}}</div>
<!--      <div class="signature">{{signature}}</div>-->
    </div>
    <div class="base">
      <van-grid :border="false">
        <van-grid-item icon="star-o" text="收藏" to="/collection"/>
        <van-grid-item icon="shop-o" text="订阅" />
        <van-grid-item icon="underway-o" text="足迹" to="/track"/>
        <van-grid-item icon="balance-pay" text="零钱" />
      </van-grid>
    </div>
    <div class="OrderForm">
      <div style="margin-left: 10px; margin-top:10px">我的订单</div>
      <van-grid :column-num="5" :border="false">
        <van-grid-item icon="gold-coin-o" text="待付款" />
        <van-grid-item icon="bag-o" text="待发货" />
        <van-grid-item icon="logistics" text="待收货" />
        <van-grid-item icon="chat-o" text="待评价" />
        <van-grid-item icon="cash-back-record" text="退款/售后" />
      </van-grid>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters, mapState} from 'vuex'
export default {
  name: "Info",
  data() {
    return {
      msg: "",
      bMsg: "退出",
      signature: '点击这里可以添加个性签名',
    }
  },
  methods: {
    init() {
      this.msg = localStorage.getItem("username");
    },
    onClickRight() {
      this.$router.push({name:'person'})
    }
  },
  created() {
    this.init();
    if (localStorage.getItem("username") == null) {
      this.$router.push({name:'login'})
    }
  }
}
</script>

<style scoped>
#info {
  background-color: #f7f8fa;
  height: 640px;
}
.t {
  margin: 20px;
  display: inline-block;
}
.login {
  width: 220px;
  background-color: #3a8ee6;
}
.custom-title {
  margin-right: 4px;
  vertical-align: middle;
}

.search-icon {
  font-size: 16px;
  line-height: inherit;
}
.name {
  display: inline-block;
  position: fixed;
  top: 90px;
  left: 120px;
}
.signature {
  display: inline-block;

}
.van-cell__label {
  width: 400px ;
}
.OrderForm {
  background: white;
  border-radius: 10px;
  margin: 10px;
}
.base {
  margin: 10px;
}
</style>