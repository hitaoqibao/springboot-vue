<template>
  <div class="app-container">
    <div class="real-container">
      <el-divider content-position="left">
        基于
        <a class="link-type" href="https://terryz.gitee.io/vue/#/region/demo"
          >v-region</a
        >
        行政区划选择器
      </el-divider>
      <v-region
        @values="regionChange"
        type="group"
        :town="true"
        blank="false"
      ></v-region>
      <div class="region">
        <span>{{ region }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Region',
  data() {
    return {
      region: '',
    };
  },
  methods: {
    regionChange(data) {
      // console.log(data);
      if (data.province != null) {
        this.region = data.province.value;
      } else {
        this.region = '';
      }
      if (data.city != null) {
        this.region = data.province.value + ',' + data.city.value;
      }
      if (data.area != null) {
        this.region =
          data.province.value + ',' + data.city.value + ',' + data.area.value;
      }
      if (data.town != null) {
        this.region =
          data.province.value +
          ',' +
          data.city.value +
          ',' +
          data.area.value +
          ',' +
          data.town.value;
      }
    },
  },
};
</script>

<style scoped>
.region {
  margin-top: 30px;
}
</style>
