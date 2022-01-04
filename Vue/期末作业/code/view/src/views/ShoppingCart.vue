<template>
<div id="ShoppingCart">
  <van-nav-bar
      title="购物车"
      :right-text="manage"
      @click-right="onClickRight"
  />
  <van-swipe-cell v-for="(value,index) in BookList">
    <van-card
        :title="value.book.name"
        :price="value.book.price"
        :desc="value.book.author"
        :thumb="value.book.img"
        style="padding-left: 30px"
    >

      <template #price-top>
        <van-checkbox :key="value.book.id" v-model="value.check" class="check" @click="iClick" />
      </template>
      <template #footer>
        <van-stepper v-model="value.num" theme="round" button-size="22" disable-input/>
      </template>

    </van-card>
    <template #right>
      <van-button square text="删除" type="danger" class="delete-button" @click="toDelete(value.book.id)" />
    </template>
  </van-swipe-cell>
  <div style="height: 100px"></div>
  <van-submit-bar :price="total" button-text="提交订单" style="margin-bottom: 50px" v-if="!ifManage" @submit="onSubmit">
    <van-checkbox v-model="checked"  @click="checkAll">全选</van-checkbox>
  </van-submit-bar>
  <van-submit-bar   button-text="提交订单" style="margin-bottom: 50px" v-if="ifManage">
    <template #default>
      <van-checkbox v-model="checked"  @click="checkAll" style="margin-right: 190px">全选</van-checkbox>
    </template>
    <template #button>
      <van-tag plain  round  type="warning" size="large" style="margin-right: 10px" @click="addCollectionAll">加入收藏夹</van-tag>
      <van-tag plain  round  type="danger" size="large" @click="deleteAll">删除</van-tag>
    </template>
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
      checked: false,
      manage:'管理',
      ifManage: false,
    }
  },
  computed: {
    ...mapState({BookList:'goodsList'}),
    total() {
      let sum = 0;
      for (let i = 0; i < this.BookList.length;i++) {
        if (this.BookList[i].check)
          sum += this.BookList[i].num*this.BookList[i].book.price*100;
      }
      return sum;
    },
  },
  methods: {
    onSubmit() {
      let buys = [];
      for (let i = 0;i < this.BookList.length;i++) {
        if (this.BookList[i].check) {
          let book = {
            num: this.BookList[i].num,
            id: this.BookList[i].bookId
          }
          buys.push(book);
        }
      }
      if(buys.length == 0) {
        this.$toast.fail("请选择商品")
      } else {
        this.$router.push({name:"OrderForm",query:{buys}});
      }
    },
    addCollectionAll() {
      let collection = [];
      for (let i = 0;i < this.BookList.length;i++) {
        if (this.BookList[i].check) {
          collection.push(this.BookList[i].bookId);
        }
      }
      console.log(collection)
      this.$toast.success("收藏成功")
    },
    deleteAll() {
      let deletes = [];
      for (let i = 0;i < this.BookList.length;i++) {
        if (this.BookList[i].check) {
          deletes.push(this.BookList[i].bookId);
        }
      }
      console.log(deletes)
      this.$toast.success("删除成功")
    },
    onClickRight() {
      if (this.ifManage) {
        this.manage = '管理'
      } else this.manage = '完成'
      this.ifManage = !this.ifManage
    },
    checkedIf() {
      for (let i = 0; i < this.BookList.length;i++) {
        if (this.BookList[i].num != 0 && !this.BookList[i].check)
          return false;
      }
      return true;
    },
    iClick() {
      this.checked = this.checkedIf();
    },
    toDelete(id) {
      this.$store.dispatch('asyncDeleteGoodItem',id);
    },
    checkAll() {
      this.$store.commit('changeAllItemFlag',this.checked)
    },
    reload() {
    },
    load() {
      this.$store.dispatch('asyncInitShoppingCart')
    }
  },
  created() {
    this.load();
    this.reload();
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
.goods-card {
  margin: 0;
  background-color: white;
}

.delete-button {
  height: 100%;
}
</style>