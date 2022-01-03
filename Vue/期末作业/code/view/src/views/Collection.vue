<template>
<div class="collection">
  <van-nav-bar
      title="收藏夹"
      left-arrow
      @click-left="prev"
      @click-right="toBuy"
  >
    <template #right>
      <i class="icon-tubiao_xiadan iconfont">合并下单</i>
    </template>
  </van-nav-bar>
  <van-swipe-cell v-for="value in collection">
    <van-card
        :price="value.book.price"
        :desc="value.book.author"
        :title="value.book.name"
        @click-thumb="hand(value.book.id)"
        :thumb="value.book.img"
        style="text-align: left; margin-bottom: 10px"
    >
      <template #tags>
        <van-tag type="danger">{{value.book.publisher}}</van-tag>
      </template>
      <template #footer>
        收藏后
        <i v-if="value.book.price>=value.original" class="icon-up iconfont" style="color: red">{{value.book.price - value.original}}</i>
        <i v-if="value.book.price<value.original" class="icon-downswing iconfont" style="color: green">{{ value.original - value.book.price}}</i>
      </template>
    </van-card>
    <template #right>
      <van-button square text="取消收藏" type="danger" class="delete-button" @click="toCancelCollect(value.book.id)" />
    </template>
  </van-swipe-cell>
</div>
</template>

<script>


export default {
  name: "Collection",
  data() {
    return {
      collection: []
    }
  },
  methods: {
    prev() {
      this.$router.back();
    },
    hand(index) {
      const id = index;
      this.$router.push({name:"Books",params:{id}});
    },
    toBuy() {
      this.$toast.success("购买成功")
    },
    toCancelCollect(id) {
      this.$ajax.toCancelCollect(id).then(res => {
        if (res.code === 100) {
          this.$toast.success("取消成功")
          this.init();
        }
      })
    },
    init() {
      this.$ajax.getCollection().then(res => {
        this.collection = res.data;
      })
    }
  },
  created() {
    this.init();
  }
}
</script>

<style scoped>
.delete-button {
  height: 100%;
}
</style>