<template>
<div id="ShoppingCart">
  <van-nav-bar
      title="购物车"
      right-text="···"
      @click-right="onClickRight"
  />
  <van-checkbox-group v-model="result">
  <van-card
      v-for="(value,index) in list"
      :title="value.book.name"
      :price="value.book.price"
      :desc="value.book.author"
      :thumb="value.book.img"
      style="padding-left: 30px"
  >
    <template #title>
      {{value.book.name}}
      <van-icon name="delete-o" size="20" color="red" class="delete" @click="toDelete(value.book.id)"/>
    </template>

    <template #price-top>
      <van-checkbox :name="value.book.id" v-model="value.check" class="check" @click="iClick(value.book.id)" />
    </template>
    <template #footer>
      <van-stepper v-model="value.num" theme="round" button-size="22" disable-input/>
    </template>

  </van-card>
  </van-checkbox-group>
  <div style="height: 100px"></div>
  <van-submit-bar :price="total" button-text="提交订单" style="margin-bottom: 50px">
    <van-checkbox v-model="checked"  @click="checkAll">全选</van-checkbox>
  </van-submit-bar>
</div>
</template>

<script>
import {mapActions, mapGetters, mapState} from "vuex";
import {Dialog} from "vant";

export default {
  name: "ShoppingCart",
  data() {
    return {
      result: [],
      checked: false
    }
  },
  computed: {
    ...mapState({list: "goodsList",total:'total'})
  },
  methods: {
    onClickRight() {

    },
    checkedIf() {
      for (let i = 0; i < this.list.length;i++) {
        if (this.list[i].num != 0 && !this.list[i].flag)
          return false;
      }
      return true;
    },
    iClick(id) {
      this.$store.commit("changeItemFlag",id);
      this.checked = this.checkedIf();
    },
    toDelete(id) {
      this.$store.dispatch('asyncDeleteGoodItem',id);

    },
    checkAll() {

    },
    load() {
      this.$store.dispatch('asyncInitShoppingCart')
    }
  },
  created() {
    this.load();
  }
}
</script>

<style scoped>
.check {
  margin-top: -55px;
  margin-left: -115px;
}
.delete {
  float: right;
}

</style>