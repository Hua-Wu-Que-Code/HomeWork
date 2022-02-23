<template>
<div id="blogs">
  <el-menu
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64">
    <el-menu-item  style="margin-left: 50px;">
      <template slot="title">
        <span style="font-size: 20px">Blog</span>
      </template>
    </el-menu-item>
    <el-menu-item index="1">
      <template slot="title">
        <i class="el-icon-location"></i>
        <span>首页</span>
      </template>
    </el-menu-item>
    <el-menu-item index="2">
      <template slot="title">
        <i class="el-icon-location"></i>
        <span>分类</span>
      </template>
    </el-menu-item>
    <el-menu-item index="3">
      <template slot="title">
        <i class="el-icon-location"></i>
        <span>标签</span>
      </template>
    </el-menu-item>
    <el-menu-item index="4">
      <template slot="title">
        <i class="el-icon-location"></i>
        <span>关于我</span>
      </template>
    </el-menu-item>

    <ul class="tUl" v-if="flag" key="login">
      <li style="margin-right: 50px">注册</li>
      <li>|</li>
      <li @click="login">登录</li>
    </ul>

    <el-dropdown v-if="!flag" :hide-on-click="false" style="float: right; margin-right: 50px;" key="drop">
      <el-avatar :src="require('../assets/icon.jpg')" style="margin-top: 10px"></el-avatar>
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

  </el-menu>


  <el-container>
    <el-container>
      <el-main>
        <div class="recent-post-item box">
          <div class="post_cover left_radius img_box">
            <a href="http://songyuxin.top/2021/10/13/hello-world/" title="Hello World">
          <img class="post_bg" src="https://picsum.photos/id/272/1920/1280" alt="Hello World" style="width: 377px;height: 270px; "></a>
          </div>
          <div class="recent-post-info">
            <h1 style="margin-top: 80px; font-size: 30px; font-weight: normal"> Hello World！</h1>
            <div style="height: 20px; font-size: 15px">
              <img src="../assets/日历.png" alt="">
              <p style="line-height: 20px;display: inline-block;color: #858585">发表于2021/2/9</p>
              |
              <p style="line-height: 20px;display: inline-block;color: #858585">作者：花无缺</p>
            </div>
          </div>
        </div>

      </el-main>
      <el-aside width="500px">
        <div class="box boxA">
          <img src="../assets/最新.png" alt="">

        </div>
      </el-aside>
    </el-container>
    <el-footer>
    </el-footer>
  </el-container>

</div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "blogs",
  data() {
    return {
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    login() {
      this.$router.push('/ToLoGin')
    },
    layout() {
      console.log('退出');
      sessionStorage.removeItem('user');
      this.$store.commit('layOut',true);
    }
  },
  computed: {
    ...mapState({flag:'Tflag'})
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

.tUl> li {
  float: right;
  display: inline-block;
  width: 40px;
  height: 61px;
  margin-right: 10px;
  line-height: 61px;
  list-style: none;
  color: white;
  text-align: center;
}
.el-footer {
  min-height: 50px;
  background-color: #545c64;
}
.el-main {

  min-height: 80vh;
}
.el-aside {

}
.bg-purple-dark {

}
.grid-content {
  min-height: 36px;
}
.box {
  width: 840px;
  height: 270px;
  background: white;
  border-radius: 10px;
  overflow: hidden;
  margin-left: 120px;
  box-shadow: 0 3px 8px 6px rgba(7,17,27,0.15);
  margin-bottom: 20px;
}
.boxA {
  width: 260px;
  height: 480px;
  margin-top: 20px;
  background: white;
  padding: 20px 20px;
  border-radius: 10px;
  overflow: hidden;
  margin-left: 20px;
  box-shadow: 0 3px 8px 6px rgba(7,17,27,0.15);
}
.img_box {
  display: inline-block;
}
.recent-post-info {
  display: inline-block;
  overflow: hidden;
  padding: 0 40px;
  width: 370px;
  height: 270px;
}
</style>