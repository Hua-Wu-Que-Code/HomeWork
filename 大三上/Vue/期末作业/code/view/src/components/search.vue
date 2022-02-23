<template>
  <div id="nav">
    <!-- vant搜索 -->
    <van-search @input="onInput" @search="onSearch" autofocus show-action shape="round" v-model="kw" placeholder="请输入搜索关键词">

      <template #left>
        <van-icon @click="back" style="margin-right: 5px;" size="22px" name="arrow-left" />
      </template>

      <template #action>
        <van-button @click="onSearch" size="small" type="warning" style="border-radius: 5px; font-size: 14px;">搜索</van-button>
      </template>

    </van-search>

    <!-- 搜索历史 -->
    <div v-show="isShow">
      <div class="search-host">
        <span>搜索记录：</span>
        <van-icon name="delete" @click="del"/>
      </div>

      <div>
        <van-tag v-for="(item,index) in kwList" @click="searchTs(item)" style="margin: 5px;">{{item}}</van-tag>
      </div>
    </div>

    <!-- 搜索提示 -->
    <van-cell-group v-show="!isShow">
      <van-cell v-for="(item,index) in showList"
                :title="item.name"
                @click="searchTs(item.name)"/>
    </van-cell-group>
  </div>
</template>

<script>
import {Dialog} from "vant";

export default {
  name: "search",
  data() {
    return {
      value: '',
      kw:'',
      isShow:true,
      list:[], //所有数据
      showList:[], //搜索提示数据
      kwList:[] //搜索记录
    }
  },
  created() { //初始化保存
    let kwlist = localStorage.kwList
    if(kwlist){
      this.kwList = JSON.parse(kwlist)
    }
  },
  mounted() {

  },
  watch: {
    kw(newValue) {

    }
  },
  methods:{
    back(){ //返回
      this.$router.push({
        path:'/'
      })
    },
    onInput(){ //搜索提示
      this.showList = [];
      this.$ajax.getBookByName(this.kw).then(res => {
        console.log(res);
        this.showList = res.data;
      })
      if(this.kw.trim() == ''){ //显示或隐藏搜索提示
        this.isShow = true
      }else{
        this.isShow = false
      }
    },
    searchTs(title){ //点击搜索提示或搜索记录，展示对应商品
      this.$router.push({
        path:'/showlist',
        query:{
          kw:title
        }
      })

      if(!this.kwList.includes(title)){ //不会重复添加相同搜索记录
        this.kwList.push(title)
        localStorage.kwList = JSON.stringify(this.kwList)
      }

    },
    onSearch(){ //确定搜索或点击搜索时触发

      if(this.kw.trim() == ''){ //判断内容是否为空
        return
      }

      this.$router.push({
        path:'/showList',
        query:{
          kw:this.kw
        }
      })

      if(!this.kwList.includes(this.kw.trim())){ //不会重复添加相同搜索记录
        this.kwList.push(this.kw)
        localStorage.kwList = JSON.stringify(this.kwList)
      }

    },
    del(){ //删除搜索记录
      this.$dialog.confirm({
        message: '确定要清空搜索记录吗？',
      })
      .then(() => {
        this.kwList = []
        localStorage.kwList = JSON.stringify(this.kwList)
      })
      .catch(() => {
        // on cancel
      });
    }

  }
}
</script>

<style scoped>
.search-host{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 5px;
  box-sizing: border-box;
}
</style>