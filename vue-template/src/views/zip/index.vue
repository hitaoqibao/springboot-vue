<template>
  <div class="app-container">
    <!-- $t is vue-i18n global function to translate lang -->
    <el-input
      :style="{ marginRight: '15px' }"
      v-model="filename"
      placeholder="请输入文件名（默认为file）"
      style="width:300px;"
      prefix-icon="el-icon-folder"
    />
    <el-button
      :loading="downloadLoading"
      style="margin-bottom:20px;"
      type="primary"
      icon="el-icon-download"
      @click="handleDownload"
      >导出Zip</el-button
    >
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="拼命加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="序号" width="95">
        <template slot-scope="scope">{{ scope.row.id }}</template>
      </el-table-column>
      <el-table-column label="作品">
        <template slot-scope="scope">{{ scope.row.title }}</template>
      </el-table-column>
      <el-table-column label="作者" width="95" align="center">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.author }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="阅读数" width="115" align="center">
        <template slot-scope="scope">{{ scope.row.readings }}</template>
      </el-table-column>
      <el-table-column align="center" label="时间" width="220">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.date }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { allinfo } from '@/api/info';
export default {
  name: 'ExportZip',
  data() {
    return {
      list: null,
      listLoading: false,
      downloadLoading: false,
      filename: '',
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
      import('@/vendor/Export2Zip').then((zip) => {
        const tHeader = ['序号', '作品', '作者', '阅读数', '时间'];
        const filterVal = ['id', 'title', 'author', 'readings', 'date'];
        const list = this.list;
        const data = this.formatJson(filterVal, list);
        zip.export_txt_to_zip(tHeader, data, this.filename, this.filename);
        this.downloadLoading = false;
      });
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map((v) => filterVal.map((j) => v[j]));
    },
  },
};
</script>
