<template>
  <div id="info">
    <van-image
        round
        width="10rem"
        height="10rem"
        src="/icons/avatar_default.png"
        class="t"
    />
    <div class="t">{{msg}}</div>
    <van-button round type="primary" class="t login" @click="changeType">{{bMsg}}</van-button>

  </div>
</template>

<script>
import {mapActions, mapState} from 'vuex'
export default {
  name: "Info",
  data() {
    return {
      msg: "",
      bMsg: ""
    }
  },
  methods: {
    changeType() {
      if (this.ifLogin) {
        localStorage.removeItem("username");
        localStorage.removeItem("token");
        this.$store.commit('changeLogin',false);
        this.init();
      } else {
        this.$router.push({name:'login'})
      }
    },
    init() {
      if (this.ifLogin) {
        this.msg = "欢迎回来,"+localStorage.getItem("username");
        this.bMsg = "退出登录"
      }
      else {
        this.msg = "尚未登录";
        this.bMsg = "去登录"
      }
    }
  },
  computed: {
    ...mapState({ifLogin:'login'})
  },
  created() {
    this.init()
  }
}
</script>

<style scoped>
#info {
  text-align: center;
}
.t {
  margin-top: 40px;
}
.login {
  width: 220px;
  background-color: #3a8ee6;
}
</style>