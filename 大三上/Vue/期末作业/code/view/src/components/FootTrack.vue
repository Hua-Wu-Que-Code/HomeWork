<template>
<div class="footerTrack">
  <van-nav-bar
      title="我的足迹"
      left-arrow
      @click-left="prev"
  >
    <template #right>
      <p class="mr">清空</p>
      <p class="mr">管理</p>
    </template>
  </van-nav-bar>
</div>
</template>

<script>
export default {
  name: "FootTrack",
  data() {
    return {
      tracks:[]
    }
  },
  methods: {
    prev() {
      this.$router.back();
    },
    init() {
      this.$ajax.getUserTrack().then(res => {
        if (res.code ===100) {
          this.tracks = res.data;
        }
      })
    }
  },
  created() {
    this.init();
  }
}
</script>

<style scoped>
.footerTrack {
  background-color: #f7f8fa;
  height: 640px;
}
.mr {
  margin-right: 10px;
}
</style>