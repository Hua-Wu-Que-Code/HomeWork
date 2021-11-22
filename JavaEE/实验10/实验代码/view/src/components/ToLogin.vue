<template>
<div id="toLoGin">
  <el-row>
    <el-col :span="8">
      <div class="grid-content bg-purple">
      <el-form
          label-position="left"
          label-width="auto"
          v-loading="loading"
          style="margin-left: 40px"
      >
        <img src="https://picsum.photos/id/222/1800/977" class="myIcon" alt="">
        <h3 class="pm">鸭博</h3>
        <p style="color: #98a6ad;" class="pm">欢迎您使用鸭博！！！</p>
        <el-form-item style="width: 80%">
          <label for="account">账号</label>
          <el-input id="account" type="text" v-model="loginForm.username"  ></el-input>
        </el-form-item>
        <el-form-item style="width: 80%">
          <label for="passwd">密码</label>
          <el-input id="passwd" type="password" v-model="loginForm.password"  ></el-input>
        </el-form-item>
        <el-checkbox class="login_remember" v-model="checked" style="font-weight: normal">记住密码</el-checkbox>
        <el-form-item >
          <el-button type="primary" @click="submitClick" class="pm" style="float: right; margin-right: 90px">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
    </el-col>
    <el-col :span="16"><img :src="url" alt="图片飞了" class="grid-content bg-purple-light"></img></el-col>
  </el-row>
</div>
</template>

<script>
export default {
  name: "ToLogin",
  data() {
    return {
      url: 'https://picsum.photos/id/47/4272/2848',
      checked: false,
      loginForm: {
        username: '',
        password: ''
      },
      res_data:'',
      loading: false,
    }
  },
  methods: {
    submitClick: function () {
      let self = this;
      this.$ajax.post("/login", {
        accountName: this.loginForm.username,
        passwd: this.loginForm.password
      }).then(res => {

        if (res.status == 200) {
          sessionStorage.setItem("user", JSON.stringify(res.user));
          this.$store.commit('layOut',false);
          this.$router.push('/',);
        }
      })
    }
  }
}
</script>

<style scoped>

.myIcon {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  margin-top: 30px;
}
.pm {
  margin-top: 0.5rem;
}
#toLoGin {

}
.el-col {
  border-radius: 4px;
}
.bg-purple-light {
  width:100%;
  height:100%;
}
.grid-content {
  border-radius: 4px;
  /*height:  calc(95vh + 5px);*/
  height: 95vh;
}
label {
  display: inline-block;
  margin-bottom: 2px;
  font-weight: 600;
  color: #34395e;
  font-size: 12px;
  letter-spacing: .5px;
}
</style>