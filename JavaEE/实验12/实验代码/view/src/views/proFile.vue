<template>
<div class="profile">
  <el-card class="toTop">
    <div class="quote">
      {{message}}
    </div>
  </el-card>

  <el-card class="toTop">
    <div slot="header">
      <el-row>
        <el-col :span="4" style="font-size: 18px; margin-top: 10px">
          文章信息
        </el-col>
        <el-col :span="8" :offset="12">
          <el-input v-model="search" placeholder="请输入查找文章标题" style="width: 80%" clearable></el-input>
          <el-button type="primary" style="margin-left: 5px;"@click="load">查询</el-button>
        </el-col>
      </el-row>
    </div>
    <div>
      <div>
        <el-button type="primary" @click="add" size="small">新增</el-button>
        <el-button type="primary"size="small">导出</el-button>
      </div>
      <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%;margin-top: 20px">
        <el-table-column
            prop="title"
            label="文章标题">
        </el-table-column>
        <el-table-column
            prop="author"
            label="作者">
        </el-table-column>
        <el-table-column
            prop="date"
            label="创作时间">
        </el-table-column>
        <el-table-column
            prop="tag"
            label="标签">
        </el-table-column>
        <el-table-column
            prop="category"
            label="分类">
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="mini">编辑</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
              <template #reference>
                <el-button size="mini" type="danger">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top: 20px">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </div>
  </el-card>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "proFile",
  components:{

  },
  data() {
    return {
      currentPage: 1,
      total:0,
      pageSize: 10,
      message:'今天月色真美。',
      tableData: [],
      user:{
        accountName:'',
        authority:'',
        id:'',
        nickName:'',
        passwd:''
      },
    }
  },
  created() {
    this.load();
    let _this = this;//定义一个外部变量，将this的值付给它
    axios({
      method: 'get',
      url: 'https://v2.alapi.cn/api/qinghua?token=EH0lMfwmTQJQNcBZ&format=json'
    }).then(function (response) {
      console.log(response)
      _this.message = response.data.data.content;//调用该变量

    }).catch(resp => {
      console.log("请求失败：" + resp);
    })
  },
  methods: {
    load() {
      this.uid = JSON.parse(sessionStorage.getItem("user")).id;
      this.$ajax.post("/article", {
        id: this.uid,
        currentPage: this.currentPage,
        pageSize: this.pageSize,
      }).then(res => {
        this.tableData = res.limit
        this.total = res.total
      })
    },
    handleSizeChange(pageSize) {   // 改变当前每页的个数触发
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {  // 改变当前页码触发
      this.currentPage = pageNum
      this.load();
    }
  },
}
</script>

<style scoped>

.toTop {
  margin-top: 15px;
}
.describe {
  background: #F1F1F1;
  font-size: 18px;
}
.describe :hover {
  background: #CFCFCF;
}
.describe div {
  color: #379688;
  font-size: 25px;
  margin-top: 10px;
}
.quote {
  margin-bottom: 10px;
  padding: 15px;
  line-height: 22px;
  border-left: 5px solid #009688;
  border-radius: 0 2px 2px 0;
  background-color: #f2f2f2;
}
</style>