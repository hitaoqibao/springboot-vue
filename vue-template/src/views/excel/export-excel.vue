<template>
  <div class="app-container">
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
        <template slot-scope="scope">{{ scope.$index }}</template>
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
</template>

<script>
import { parseTime } from '@/utils';
import FilenameOption from './components/FilenameOption';
import AutoWidthOption from './components/AutoWidthOption';
import BookTypeOption from './components/BookTypeOption';
//导出excel
export default {
  name: 'ExportExcel',
  components: { FilenameOption, AutoWidthOption, BookTypeOption },
  data() {
    return {
      list: [
        {
          id: 1,
          date: '2020-01-01 15:12:01',
          title: '《斗破苍穹》《大主宰》',
          author: '天蚕土豆',
          readings: '6546',
        },
        {
          id: 2,
          date: '1955-10-22 05:04:12',
          title: '《呐喊》《彷徨》《朝花夕拾》《野草》《华盖集》',
          author: '鲁迅',
          readings: '55444',
        },
        {
          id: 3,
          date: '2018-06-15 18:01:55',
          title: '《水浒传》《大宋宣和遗事》',
          author: '施耐庵',
          readings: '4472',
        },
        {
          id: 4,
          date: '2007-09-24 07:08:55',
          title: '《三国演义》《隋唐两朝志传》《残唐五代史演义》《三遂平妖传》',
          author: '罗贯中',
          readings: '51512',
        },
        {
          id: 5,
          date: '1996-11-19 07:08:55',
          title: '《西游记》《淮安府志》',
          author: '吴承恩',
          readings: '151223',
        },
        {
          id: 6,
          date: '1824-06-20 04:15:45',
          title: '《红楼梦》《石头记》《金陵十二钗》《风月宝鉴》',
          author: '曹雪芹',
          readings: '25145',
        },
      ],
      downloadLoading: false,
      filename: '', //文件名
      autoWidth: true, //单元格自动宽度
      bookType: 'xlsx', //文件类型
    };
  },
  methods: {
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
