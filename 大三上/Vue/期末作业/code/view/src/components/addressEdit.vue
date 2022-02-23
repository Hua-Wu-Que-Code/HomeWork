<template>
<div class="addressEdit">
  <van-nav-bar
      title="编辑收货地址"
      left-arrow
      @click-left="prev"
  >
    <template #right>
      <van-icon name="ellipsis" size="18" />
    </template>
  </van-nav-bar>
  <van-address-edit
      :area-list="areaList"
      show-postal
      show-delete
      show-set-default
      show-search-result
      :search-result="searchResult"
      :area-columns-placeholder="['请选择', '请选择', '请选择']"
      @save="onSave"
      @delete="onDelete"
      @change-detail="onChangeDetail"
  />
</div>
</template>

<script>

import { Toast } from 'vant';
import { areaList } from '@vant/area-data';

export default {
  name: "addressEdit",
  data() {
    return {
      areaList,
      searchResult: [],
    };
  },
  methods: {
    prev() {
      this.$router.back();
    },
    onSave() {
      Toast('save');
    },
    onDelete() {
      Toast('delete');
    },
    onChangeDetail(val) {
      if (val) {
        this.searchResult = [
          {
            name: '黄龙万科中心',
            address: '杭州市西湖区',
          },
        ];
      } else {
        this.searchResult = [];
      }
    },
  }
}
</script>

<style scoped>
.addressEdit {
  background-color: #f7f8fa;
  height: 710px;
}
</style>