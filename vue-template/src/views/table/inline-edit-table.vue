<template>
  <div class="app-container">
    <div class="real-container">
      <el-table
        :data="list"
        border
        fit
        highlight-current-row
        style="width: 100%"
      >
        <el-table-column align="center" label="序号" width="80">
          <template slot-scope="{ row }">
            <span>{{ row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column width="180px" align="center" label="时间">
          <template slot-scope="{ row }">
            <span>{{ row.date }}</span>
          </template>
        </el-table-column>
        <el-table-column width="120px" align="center" label="作者">
          <template slot-scope="{ row }">
            <span>{{ row.author }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="300px" label="作品">
          <template slot-scope="{ row }">
            <template v-if="row.edit">
              <el-input v-model="row.title" class="edit-input" size="small" />
              <el-button
                class="cancel-btn"
                size="small"
                icon="el-icon-refresh"
                type="warning"
                @click="cancelEdit(row)"
                >取消</el-button
              >
            </template>
            <span v-else>{{ row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="Actions" width="120">
          <template slot-scope="{ row }">
            <el-button
              v-if="row.edit"
              type="success"
              size="small"
              icon="el-icon-circle-check-outline"
              @click="confirmEdit(row)"
              >确定</el-button
            >
            <el-button
              v-else
              type="primary"
              size="small"
              icon="el-icon-edit"
              @click="row.edit = !row.edit"
              >编辑</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
//表格内编辑
export default {
  name: 'InlineEditTable',
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
          date: '1955-10-44 05:04:12',
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
    };
  },
  created() {
    this.getList();
  },
  methods: {
    async getList() {
      this.list = this.list.map((v) => {
        this.$set(v, 'edit', false); // https://vuejs.org/v2/guide/reactivity.html
        v.originalTitle = v.title; //  点击取消时实现
        return v;
      });
    },
    cancelEdit(row) {
      row.title = row.originalTitle;
      row.edit = false;
      this.$message({
        message: '取消修改',
        type: 'warning',
      });
    },
    confirmEdit(row) {
      row.edit = false;
      row.originalTitle = row.title;
      this.$message({
        message: '修改成功',
        type: 'success',
      });
    },
  },
};
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
