<template>
<div id="books">
  <van-pull-refresh v-model="isDownLoading" @refresh="onDownRefresh">
    <van-list
        v-model="isUpLoading"
        :finished="upFinished"
        :immediate-check="false"
        finished-text="没有更多了"
        @load="onLoad"
    >
      <van-cell v-for="(value,index) in dtWinNumberInfos" :key="value.id" >

        <van-card
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
      </van-cell>
    </van-list>
  </van-pull-refresh>
  <div style="height: 5rem"></div>
</div>
</template>

<script>
import mapMutations, {mapState} from "vuex";

export default {
  name: "books",
  data() {
    return {
      key:'',
      pageSize: 10, // 每页条数
      pageIndex: 1, // 页码
      dtWinNumberInfos: [], // 请求数据
      isDownLoading: false, //下拉刷新
      isUpLoading: false, //上拉加载
      upFinished: false,  //上拉加载完毕
    }
  },
  created: function () {
    this.key = this.$route.params.key;
    let books = this.$store.state.books[this.key];
    if (books.length) {
      this.dtWinNumberInfos= books;
      let page = books.length/this.pageSize;
      if (page%1===0) {
        this.pageIndex = page+1;
      } else {
        this.pageIndex = Math.ceil(page)+2;
      }
      console.log(this.pageIndex)
    }
    this.getLoadList();
  },
  computed: {
    ...mapState({books:'books'})
  },
  methods:{
    hand(index) {
      const id = index;
      this.$router.push({name:"Books",params:{id}});
    },
    toBy(id) {
      this.$store.dispatch('asyncAddGoodItem',id)

    },
    // 获取资源表单列表
    getLoadList() {
      const _self = this
      this.$ajax.get_category_books_WaterFall(this.key,this.pageIndex,this.pageSize).then(response => {
        console.log(response)
        if (response.code === 100) {
          const rows = response.data
          if (rows == null || rows.length === 0) {
            // 加载结束
            _self.upFinished = true
            return
          }
          if (rows.length < this.pageSize) {
            // 最后一页不足10条的情况
            _self.upFinished = true
          }
          // 处理数据
          for (let i = 0; i < rows.length; i++) {
            rows[i].img = "/" + rows[i].img;
          }
          if (_self.pageIndex === 1) {
            _self.dtWinNumberInfos = rows
          } else {
            _self.dtWinNumberInfos = _self.dtWinNumberInfos.concat(rows)
          }
          this.$store.dispatch('asyncInitCategoryBooksWater',{key: this.key,list:this.dtWinNumberInfos});
        }
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '获取资源列表异常{' + error + '}',
          type: 'error'
        })
      }).finally(() => {
        _self.isDownLoading = false
        _self.isUpLoading = false
      })
    },
    //上拉加载请求方法
    onLoad() {
      this.pageIndex++
      this.getLoadList()
    },
    onDownRefresh() {
      this.pageIndex = 1
      this.upFinished = false // 不写这句会导致你上拉到底过后在下拉刷新将不能触发下拉加载事件
      this.onLoad() // 加载数据
    },
  }
}
</script>

<style scoped>
</style>