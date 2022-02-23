<template>
  <div class="list">
    <div id="nav">商品列表</div>
    <van-list
        v-model:loading="loading"
        :finished="finished"
        finished-text="没有更多了"
        @load="onLoad"
    >
      <van-cell v-for="(item ,index) in list" :key="item" :title="item" @click="hand(index)"/>
    </van-list>
  </div>
</template>

<script>
import { ref } from 'vue';
export default {
  data() {
    return {
      list: [
          '测试商品1',
          '测试商品2',
          '测试商品3',
          '测试商品4',
          '测试商品5',
          '测试商品6',
          '测试商品7',
          '测试商品8',
          '测试商品9',
          '测试商品10',
          '测试商品11',
          '测试商品12',
          '测试商品13',
          '测试商品14',
          '测试商品15',
      ],
      onLoad: '',
      loading: '',
      finished: '',
    }
  },
  methods:{
    TableRowClassName({row, rowIndex}) {
      if (rowIndex%2==0) {
        return 'statistics-warning-row';
      } else {
        return '';
      }
    },
    hand(index) {
      const id = index;
      this.$router.push({name:"Books",params:{id}});
    }
  },
  setup() {
    const list = ref([]);
    const loading = ref(false);
    const finished = ref(false);

    const onLoad = () => {
      // 异步更新数据
      // setTimeout 仅做示例，真实场景中一般为 ajax 请求
      setTimeout(() => {
        for (let i = 0; i < 10; i++) {
          list.value.push(list.value.length + 1);
        }

        // 加载状态结束
        loading.value = false;

        // 数据全部加载完成
        if (list.value.length >= 40) {
          finished.value = true;
        }
      }, 1000);
    };

    return {
      list,
      onLoad,
      loading,
      finished,
    };
  },

}
</script>
<style>
.el-table__row.statistics-warning-row {
  background:#D3DFED;
}

.list {
  text-align: center;
}
</style>
