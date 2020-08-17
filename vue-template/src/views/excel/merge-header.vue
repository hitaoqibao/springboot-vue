<template>
  <div class="app-container">
    <el-card>
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
        <el-table-column label="主要信息" align="center">
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
        </el-table-column>
        <el-table-column align="center" label="时间" width="220">
          <template slot-scope="scope">
            <i class="el-icon-time" />
            <span>{{ scope.row.date }}</span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import { parseTime } from '@/utils';
import FilenameOption from './components/FilenameOption';
import AutoWidthOption from './components/AutoWidthOption';
import BookTypeOption from './components/BookTypeOption';
import { allinfo } from '@/api/info';
//导出多级表头
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
        const multiHeader = [['序号', '主要信息', '', '', '时间']];
        const header = ['', '作品', '作者', '阅读数', ''];
        const filterVal = ['id', 'title', 'author', 'readings', 'date'];
        const merges = ['A1:A2', 'B1:D1', 'E1:E2'];
        const list = this.list;
        const data = this.formatJson(filterVal, list);
        excel.export_json_to_excel({
          multiHeader,
          header,
          merges,
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

<style></style>
