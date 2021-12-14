<template>
<div class="list">
  <div class="nav">
    <van-search  shape="round" v-model="value" placeholder="请输入搜索关键词">

      <template #left>
        <van-icon @click="back" style="margin-right: 5px;" size="22px" name="arrow-left" />
      </template>

      <template #action>
        <van-button  size="small" type="warning" style="border-radius: 5px; font-size: 14px;">搜索</van-button>
      </template>

    </van-search>

    <van-card
        v-for="(value,index) in searchList "
        :key="value.id"
        :price="value.price"
        :desc="value.author"
        :title="value.name"
        @click-thumb="hand(value.id)"
        :thumb="value.img"
        style="text-align: left"
    >
      <template #tags>
        <van-tag type="danger">{{value.publisher}}</van-tag>
      </template>
      <template #footer>
        <van-icon @click="toBy(value.id)" name="shopping-cart" size="30" color="red" />
      </template>
    </van-card>

  </div>
</div>
</template>

<script>
import {Dialog} from "vant";

export default {
  name: "showList",
  data() {
    return {
      value: '',
      searchList: [],
    }
  },
  methods: {
    onSearch() {
      this.$router.push({name:'search'})
    },
    back() {
      this.$router.back();
    },
    hand(index) {
      const id = index;
      this.$router.push({name:"Books",params:{id}});
    },
    toBy(id) {
      this.$store.dispatch('asyncAddGoodItem',id)

    },
  },
  created() {
    let name = this.$route.query.kw;
    this.value = name;
    this.$ajax.getBookByName(name).then(res => {
      if (res.data.length == 0) {
        Dialog.alert({message:"没有找到相关宝贝~"})
      } else this.searchList = res.data;
    })
  }
}
</script>

<style scoped>

</style>