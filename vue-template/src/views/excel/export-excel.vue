<template>
  <div class="app-container">
    <div class="real-container">
      <div>
        <FilenameOption v-model="filename" />
        <AutoWidthOption v-model="autoWidth" />
        <BookTypeOption v-model="bookType" />
        <el-button
          :loading="downloadLoading"
          style="margin:0 0 20px 20px;"
          type="primary"
          icon="el-icon-download"
          @click="handleDownload"
          size="medium"
          >导出 Excel</el-button
        >
        <a
          href="https://panjiachen.gitee.io/vue-element-admin-site/zh/feature/component/excel.html#excel-%E5%AF%BC%E5%87%BA"
          target="_blank"
          style="margin-left:15px;"
        >
          <el-tag type="info">文档</el-tag>
        </a>
      </div>

      <el-table :data="list" border fit highlight-current-row>
        <el-table-column align="center" label="序号" width="95">
          <template slot-scope="scope">{{ scope.row.id }}</template>
        </el-table-column>
        <el-table-column label="作品">
          <template slot-scope="scope">{{ scope.row.title }}</template>
        </el-table-column>
        <el-table-column label="作者" width="110" align="center">
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
  </div>
</template>

<script>
import { parseTime } from '@/utils';
import FilenameOption from './components/FilenameOption';
import AutoWidthOption from './components/AutoWidthOption';
import BookTypeOption from './components/BookTypeOption';
import { allinfo } from '@/api/info';
//导出excel
export default {
  name: 'ExportExcel',
  components: { FilenameOption, AutoWidthOption, BookTypeOption },
  data() {
    return {
      list: null,
      downloadLoading: false,
      filename: '', //文件名
      autoWidth: true, //单元格自动宽度
      bookType: 'xlsx', //文件类型
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
    //导出Excel
    handleDownload() {
      this.downloadLoading = true;
      import('@/vendor/Export2Excel').then((excel) => {
        const tHeader = ['序号', '作品', '作者', '阅读数', '时间'];
        const filterVal = ['id', 'title', 'author', 'readings', 'date'];
        const list = this.list;
        const data = this.formatJson(filterVal, list);
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: this.filename,
          autoWidth: this.autoWidth,
          bookType: this.bookType,
        });
        this.downloadLoading = false;
      });
    },
    //时间格式化
    formatJson(filterVal, jsonData) {
      return jsonData.map((v) =>
        filterVal.map((j) => {
          if (j === 'date') {
            return parseTime(v[j]);
          } else {
            return v[j];
          }
        })
      );
    },
  },
};
</script>

<style>
.radio-label {
  font-size: 14px;
  color: #606266;
  line-height: 40px;
  padding: 0 12px 0 30px;
}
</style>
