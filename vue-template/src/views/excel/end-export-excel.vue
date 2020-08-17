<template>
  <div class="app-container">
    <div class="real-container">
      <el-button
        :loading="downloadLoading"
        style="margin:0 0 20px 20px;"
        type="primary"
        icon="el-icon-download"
        @click="handleDownload"
        size="medium"
        >导出 Excel</el-button
      >
      <el-table :data="list" v-loading="listloading" border>
        <el-table-column
          label="序号"
          prop="id"
          align="center"
          width="50px"
        ></el-table-column>
        <el-table-column
          label="作品"
          prop="title"
          align="center"
        ></el-table-column>
        <el-table-column
          label="作者"
          prop="author"
          align="center"
        ></el-table-column>
        <el-table-column
          label="阅读数"
          prop="readings"
          align="center"
        ></el-table-column>
        <el-table-column
          label="时间"
          prop="date"
          align="center"
        ></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { allinfo, exporttable } from '@/api/info';
export default {
  name: 'EndExportExcel',
  data() {
    return {
      filename: '',
      listloading: false,
      downloadLoading: false,
      list: null,
    };
  },
  created() {
    this.getlist();
  },
  methods: {
    //获取表格数据
    getlist() {
      this.listloading = true;
      allinfo()
        .then((response) => {
          this.list = response.data.list;
          this.listloading = false;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    handleDownload() {
      this.downloadLoading = true;
      // exporttable().then((response) => {
      //   window.open("www.baidu.com");
      // });
    },
  },
};
</script>

<style></style>
