<template>
  <div id="info">
    <van-image
        round
        width="10rem"
        height="10rem"
        src="https://img.yzcdn.cn/vant/cat.jpeg"
        style="margin-top: 40px"
    /> <br />
    <div class="t1">{{msg}}</div>
    <van-button round type="info" style="width:250px;" class="t1" @click="login">{{bMsg}}</van-button>
  </div>
</template>

<script>
export default {
  name: "Info",
  data() {
    return {
      msg: "你还没有登陆，请先登陆",
      bMsg: "去登陆"
    }
  },
  methods: {
    login() {

    }
  }
}
</script>

<style scoped>
.t1 {
  margin-top: 20px;
}
</style>