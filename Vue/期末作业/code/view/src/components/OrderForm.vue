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

  <van-cell-group inset>
    <van-cell title="单元格" value="内容" />
    <van-cell title="单元格" value="内容" label="描述信息" />
  </van-cell-group>


  <van-popup
      v-model="show"
      closeable
      close-icon="close"
      position="bottom"
      :style="{ height: '50%' }"
  />
  <van-submit-bar :price="3050" button-text="提交订单" @submit="onSubmit" />
</div>
</template>

<script>
export default {
  name: "OrderForm",
  data() {
    return {
      show: false
    }
  },
  methods: {
    prev() {
      this.$router.back();
    },
    onSubmit() {
      this.show = true;
    }
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