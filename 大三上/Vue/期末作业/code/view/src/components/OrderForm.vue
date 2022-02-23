<template>
<div class="order">
  <van-nav-bar
      left-text="确认订单"
      left-arrow
      @click-left="prev"
  >
  </van-nav-bar>
  <van-cell is-link to="#" size="large" class="t">
    <!-- 使用 title 插槽来自定义标题 -->
    <template #title>
      <i class="icon-ditu iconfont" style="color: orange;"></i>
    </template>
  </van-cell>

  <div class="t" v-for="value in list">
    <van-card
        :price="value.price"
        :desc="value.author"
        :title="value.name"
        :thumb="value.img"
        style="text-align: left;background: white"
    >
    </van-card>
    <van-cell-group>
      <van-cell title="配送服务" is-link value="内容" @click="delivery" />
      <van-cell title="运费险" is-link value="内容" @click="freight"/>
      <van-cell title="店铺优惠" is-link value="内容" @click="discount"/>
      <van-cell title="订单备注" is-link value="内容" @click="mark"/>
    </van-cell-group>
  </div>
  <van-popup
      v-model="payShow"
      closeable
      close-icon="close"
      position="bottom"
      style="height: 50%;text-align: center"
  >
    <!-- 生成二维码图片 -->
    <vueQr :text="text" :size="300" v-if="!paySucc"></vueQr>
    <!-- 使用websocket监控是否扫描，扫描成功显示成功并退出界面 -->
    <span class="iconfont icon-success" style="position: relative;font-size: 100px;color:#42B983;margin-left: 50px;top:-10px;" v-else></span>
  </van-popup>
  <van-popup
      v-model="markShow"
      closeable
      close-icon="close"
      position="bottom"
      :style="{ height: '50%' }"
  />
  <van-popup
      v-model="discountShow"
      closeable
      close-icon="close"
      position="bottom"
      :style="{ height: '50%' }"
  />
  <van-popup
      v-model="freightShow"
      closeable
      close-icon="close"
      position="bottom"
      :style="{ height: '50%' }"
  />
  <van-popup
      v-model="deliveryShow"
      closeable
      close-icon="close"
      position="bottom"
      :style="{ height: '50%' }"
  />
  <van-submit-bar :price="3050" button-text="提交订单" @submit="onSubmit" />
</div>
</template>

<script>
import vueQr from 'vue-qr'
export default {
  name: "OrderForm",
  components: {
    vueQr
  },
  data() {
    return {
      payShow: false,
      deliveryShow: false,
      freightShow: false,
      discountShow: false,
      markShow: false,
      list: [],
      text: "",
      paySucc: false
    }
  },
  methods: {
    prev() {
      this.$router.back();
    },
    back(dataUrl, id) {
      console.log(dataUrl, id)
    },
    onSubmit() {
      this.payShow = true;
      let _this = this;
      _this.paySucc = false;
      _this.dialogVisible = true;
      this.axios.request("http://localhost:9990/createQR")
          .then((response) => {
            _this.text = response.data;
            _this.dialogVisible = true;
            //使用webSocket发送请求，下面会简单介绍websocket使用
            if ("WebSocket" in window) {
              // 打开一个 web socket
              var ws = new WebSocket("ws://localhost:9990/bindingRecord");

              ws.onopen = function() {
                // Web Socket 已连接上，使用 send() 方法发送数据
                // ws.send("data");
                // alert("数据发送中...");
              };

              ws.onmessage = function(evt) {
                var received_msg = evt.data;
                // alert("数据已接收..." + evt.data);
                if (Boolean(evt.data)) {
                  _this.paySucc = true;
                  setTimeout(() => {
                    _this.dialogVisible = false;
                  }, 3 * 1000);
                }
                ws.close();

              };

              ws.onclose = function() {
                // // 关闭 websocket
                console.log("连接已关闭...");
              };
            } else {
              // 浏览器不支持 WebSocket
              alert("您的浏览器不支持 WebSocket!");
            }
          }).catch((err) => {
        console.log(err)
      })
    },
    delivery() {
      this.deliveryShow = true;
    },
    freight() {
      this.freightShow = true;
    },
    discount() {
      this.discountShow = true;
    },
    mark() {
      this.markShow = true;
    }
  },
  created() {
    let books = this.$route.query.buys;
    let bookIds = '';
    for (let i =0;i<books.length;i++) {
      if (i ==0) bookIds += books[i].id;
      else bookIds += (','+books[i].id);
    }
    this.$ajax.getBuyBooks(bookIds).then(res => {
      if (res.code ==100) {
        this.list = res.data;
      }
    })
  }
}
</script>

<style scoped>
.order {
  background-color: #f7f8fa;
  height: 640px;
}
.t {
  margin-top: 10px
}
</style>