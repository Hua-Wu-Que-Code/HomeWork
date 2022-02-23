<template>
  <div class="nav">
          <el-menu
          background-color="#1A1B20"
          default-active="/profile"
          class="el-menu-vertical-demo"
          text-color="#fff"
          router="router"
          unique-opened="unique-opened"
          style="min-height: 98vh;text-align: left"
          >
          <el-menu-item index="/">
            <h2>Blog</h2>
          </el-menu-item>
          <el-menu-item index="/profile">
            <template>
              <i class="el-icon-menu"></i>
              <span>首页</span>
            </template>
          </el-menu-item>
          </el-menu>
  </div>
</template>
<script>
export default {
  name: "Nav",
  data() {
    return {
      menuList:[
        {
          title:'个人管理',
          path:'1',
          ico:'el-icon-menu',
          child:[
            {title:'个人中心', path:'/person', ico:''},
            {title:'个人设置', path:'/personSet', ico:''},
          ]
        }
      ]
    }
  },
  methods: {

  },

}
</script>

<style scoped>

</style>