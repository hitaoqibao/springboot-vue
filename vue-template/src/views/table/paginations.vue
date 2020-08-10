<template>
  <div class="backcolor">
    <div class="all">
      <div class="operation">
        <div class="select">
          <el-input
            :style="{ width: '150px' }"
            v-model="value"
            @input="getList"
            suffix-icon="el-icon-search"
            placeholder="模糊搜索"
            size="small"
          ></el-input>
        </div>
        <div class="button">
          <el-button type="primary" icon="el-icon-plus" size="small" v-waves @click="add">添加</el-button>
          <el-button type="primary" icon="el-icon-edit" size="small" v-waves>查看</el-button>
          <el-button type="primary" icon="el-icon-edit" size="small" v-waves>编辑</el-button>
          <el-button type="danger" icon="el-icon-delete" size="small" v-waves>删除</el-button>
          <el-button type="success" icon="el-icon-download" size="small" v-waves>导出</el-button>
        </div>
      </div>
      <div class="table">
        <el-table :data="list" v-loading="listLoading" element-loading-text="数据加载中...">
          <el-table-column label="序号" prop="id" align="center"></el-table-column>
          <el-table-column label="账号" prop="username" align="center"></el-table-column>
          <el-table-column label="密码" prop="password" align="center"></el-table-column>
        </el-table>
      </div>
      <div class="pagination">
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="listQuery.page"
          :limit.sync="listQuery.limit"
          @pagination="getList"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { getusertable } from "@/api/test";
import Pagination from "@/components/Pagination"; //分页组件
import waves from "@/directive/waves/index.js"; // 水波纹指令

export default {
  name: "paginations",
  components: { Pagination },
  directives: {
    waves,
  },
  data() {
    return {
      value: "",
      //表格数据
      list: null,
      //表格总数
      total: 0,
      //表格加载动画
      listLoading: false,
      listQuery: {
        // 当前页
        page: 1,
        //每页多少条
        limit: 10,
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //获取列表数据
    getList() {
      this.listLoading = true;
      getusertable(this.listQuery.page, this.listQuery.limit, this.value)
        .then((response) => {
          this.list = response.data.list;
          this.total = response.data.total;
          this.listLoading = false;
        })
        .catch((error) => {
          this.listLoading = false;
          console.log(error);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.backcolor {
  background-color: #f0f2f5;
  padding: 15px;
  min-height: calc(100vh - 84px);
}
.all {
  padding: 20px;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
.operation {
  width: 100%;
}
.select {
  float: right;
  margin-bottom: 5px;
}
.select ::v-deep .el-input__inner {
  border-radius: 20px;
}
.pagination {
  text-align: center;
}
</style>
