<template>
<div id="login">
  <van-nav-bar
      title="尚未登录"
      left-arrow
      right-text="···"
      @click-left="prev"
      @click-right="onClickRight"
  />
  <van-image
      round
      width="10rem"
      height="10rem"
      src="/icons/avatar_default.png"
      class="t"
  />
  <div class="t">尚未登录</div>
  <van-cell-group inset class="t">
    <!-- 输入任意文本 -->
    <van-field v-model="accountName" label="用户名"   :placeholder="accountNameMsg"/>
    <!-- 输入密码 -->
    <van-field v-model="passWord" type="password" label="密码"  :placeholder="passWordMsg"/>
  </van-cell-group>
  <van-button round type="primary" class="t login" @click="login">提交</van-button>
</div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      accountName:'' ,
      passWord: '',
      accountNameMsg:'',
      passWordMsg:''
    }
  },
  methods: {
    onClickRight() {

    },
    prev() {
      this.$router.back();
    },
    login() {
      if (this.accountName != '' && this.passWord != '') {
        let self = this;
        this.$ajax.post("/login",{
          name: this.accountName,
          passwd:this.passWord
        }).then(res => {

          console.log(res)
          if (res.code == 100) {
            localStorage.setItem("token",res.data.token);
            localStorage.setItem("username",res.data.name);
            this.$store.commit('changeLogin',true);
            this.$router.push({name:'Info'});
          }

        })
      } else {
        if (this.accountName == '') this.accountNameMsg="请输入账号"
        if (this.passWord == '') this.passWordMsg="请输入密码"
      }
    }
  }
}
</script>

<style scoped>
#login {
  text-align: center;
  background-color: #f7f8fa;
  height: 640px;
}
.t {
  margin-top: 40px;
}
.login {
  width: 220px;
  background-color: #3a8ee6;
}
</style>