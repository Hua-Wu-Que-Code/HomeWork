<template>
  <el-row>
    <el-col :span="4">
      <Nav style="overflow-x: hidden"/>
    </el-col>
    <el-col :span="20">
      <el-header style="background: #EFEEF0;">
        <el-col :span="6">
          <div style="display: inline-flex">
            <el-input
                v-model="keyword"
                :placeholder="placeholder"
                prefix-icon="el-icon-search"
                style="width: 350px;margin-right: 10px;line-height: 60px"
                clearable
                @clear="search"
                @keydown.enter.native="search">
            </el-input>
          </div>
        </el-col>
        <el-col :span="1" style="margin-top: 10px;font-size: 24px" :offset=17>
          <el-dropdown :hide-on-click="false">
            <el-avatar :src="require('../assets/icon.jpg')"></el-avatar>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <router-link to="/profile" style="text-decoration: none;color:#606266;">个人中心</router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/personSet" style="text-decoration: none;color:#606266;">设置</router-link>
              </el-dropdown-item>
              <el-dropdown-item @click.native="layout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </el-header>
      <router-view style="margin-left: 20px; margin-top: 10px; height: calc(98vh - 92px);  overflow-y: scroll; overflow-x: hidden; " />
    </el-col>
  </el-row>
</template>

<script>
import Nav from "@/views/Nav";
export default {
  name: "layout",
  components: {
    Nav,
  },
  data() {
    return {
      keyword:''
      }
  },
  methods: {
    layout() {
      console.log('退出');
      sessionStorage.removeItem('user');
      this.$router.push('/Login');
    },
    search(){
      this.$emit("search",['search',this.keyword])
    }
  },
  props:{
    disabled:{
      type:Boolean,
      default:true
    },
    placeholder:{
      type:String,
      default: '请输入昵称进行搜索，可以直接回车搜索...'
    }
  },
}
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>