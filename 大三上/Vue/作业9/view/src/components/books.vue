<template>
<div id="books">
  <van-card
      v-for="(value,index) in message"
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
  <div style="height: 5rem"></div>
</div>
</template>

<script>
import mapMutations from "vuex";

export default {
  name: "books",
  data() {
    return {
      key:'',
      message:[],
    }
  },
  created: function () {
    let self = this;
    this.key = this.$route.params.key;
    this.$ajax.get_category_books(this.key)
    /*this.axios({
      url:"http://localhost:9990/category/books",
      method:'get',
      params: { id: this.key }
    })*/
    .then(function(response){
      self.message = response.data;
      for (let i = 0; i < self.message.length; i++) {
        self.message[i].img = "/" + self.message[i].img;
      }
    })
  },
  methods:{
    hand(index) {
      const id = index;
      this.$router.push({name:"Books",params:{id}});
    },
    toBy(id) {
      this.$store.dispatch('asyncAddGoodItem',id)

    },

  }
}
</script>

<style scoped>
</style>