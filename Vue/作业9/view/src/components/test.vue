<template>
  <div id="test">
    <img alt="bookFace" :src="message.img">
    <van-divider />
    <van-card
        :key="message.id"
        :price="message.price"
        :desc="message.author"
        :title="message.name"
        style="text-align: left"
    >
      <template #tags>
        <van-tag plain type="primary">{{message.publisher}}</van-tag><br />
        <van-tag type="danger" style="margin-top: 5px">{{message.category}}</van-tag><br/>
      </template>
    </van-card>
    <div class="description" v-html="message.description"></div>
    <div style="height: 5rem"></div>
    <van-goods-action>
      <van-goods-action-icon icon="chat-o" text="客服" @click="onClickIcon" />
      <van-goods-action-icon icon="cart-o" text="购物车" @click="onClickIcon" />
      <van-goods-action-icon icon="shop-o" text="店铺" @click="onClickIcon" />
      <van-goods-action-button
          type="danger"
          text="立即购买"
          @click="onClickButton"
      />
    </van-goods-action>
  </div>
</template>

<script>
import {Toast} from "vant";

export default {
  name: "test",
  data() {
    return{
      message:''
    }
  },
  created: function () {
    let self = this;
    this.id = 1;
    axios({
      url:"/json/details/"+this.id+".json",
      method:'get',
    })
        .then(function(response){
          self.message = response.data;
          self.message.img = "/" + self.message.img;
          self.message.description = self.message.description.replace(/\n/g, '<br>')
          console.log(self.message)
          console.log(self.message.description)
        })
  },
  methods:{
    onClickIcon() {
      Toast('点击图标');
    },
    onClickButton() {
      Toast('点击按钮');
    },
  },
}
</script>

<style scoped>
.description {
  text-align: left;
  padding: 10px 20px;
}
</style>