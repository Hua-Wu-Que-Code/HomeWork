<template>
  <div id="commodity">
    <van-nav-bar
        :title="message.name"
        left-arrow
        @click-left="prev"
    >
      <template #right>
        <van-icon name="ellipsis" size="18" />
      </template>
    </van-nav-bar>

    <div style="text-align: center">
      <img alt="bookFace" :src="message.img">
    </div>
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
import { Toast } from 'vant';

export default {
  name: "commodity",
  data() {
    return {
      id:'',
      message:{},
    }
  },
  created: function () {
    let self = this;
    this.id = this.$route.params.id;
    axios({
      url:"/json/details/"+this.id+".json",
      method:'get',
    })
        .then(function(response){
          self.message = response.data;
          self.message.img = "/" + self.message.img;
          self.message.description = self.message.description.replace(/\n/g, '<br>')
        })
  },
  mounted(){

  },
  methods:{
    prev() {
      this.$router.back();
    },
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
#commodity {

}
#nav {
  font-size: 20px;
  position: relative;
  color: black;
  text-align: center;
}
.back {
  position: absolute;
  left: 10px;
}
.more {
  position: absolute;
  right: 10px;
}
#nav div {
  display: inline-block;
}
.description {
  font: 500 18px '微软雅黑';
  text-align: left;
  padding: 10px 20px;
}
</style>