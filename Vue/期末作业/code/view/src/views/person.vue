<template>
<div class="person">
  <van-nav-bar
      title="设置"
      left-arrow
      @click-left="prev"
  >
    <template #right>
      <van-icon name="ellipsis" size="18" />
    </template>
  </van-nav-bar>
  <van-cell title="账户" is-link/>
  <van-cell title="我的收货地址" is-link to="/address"/>
  <van-cell-group class="t">
    <van-cell title="账户和安全" is-link />
    <van-cell title="支付" is-link/>
  </van-cell-group>
  <van-cell-group class="t">
    <van-cell title="隐私" is-link />
  </van-cell-group>

  <van-cell class="t" title="退出" @click="layout" style="text-align: center" />
</div>
</template>

<script>
export default {
  name: "person",
  methods: {
    prev() {
      this.$router.back();
    },
    layout() {
      localStorage.removeItem("username");
      localStorage.removeItem("token");
      this.$store.commit('changeLogin',false)
      this.$router.push({path:'/'})
    }

  }
}
</script>

<style scoped>
.person {
  background-color: #f7f8fa;
  height: 710px;
}
.t {
  margin-top: 10px;
}
</style>