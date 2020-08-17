<template>
  <div class="app-container">
    <div class="real-container">
      <el-table
        ref="dragTable"
        :data="list"
        row-key="id"
        border
        fit
        highlight-current-row
        style="width: 100%"
      >
        <el-table-column align="center" label="序号" width="65">
          <template slot-scope="{ row }">
            <span>{{ row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column width="180px" align="center" label="时间">
          <template slot-scope="{ row }">
            <span>{{ row.date }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="300px" label="作品">
          <template slot-scope="{ row }">
            <span>{{ row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column width="110px" align="center" label="作者">
          <template slot-scope="{ row }">
            <span>{{ row.author }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="阅读数" width="95">
          <template slot-scope="{ row }">
            <span>{{ row.readings }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="图标" width="80">
          <template slot-scope="{}">
            <svg-icon class="drag-handler" icon-class="drag" />
          </template>
        </el-table-column>
      </el-table>
      <div class="show-d">
        <el-tag style="margin-right:12px;">默认排序 :</el-tag>
        {{ oldList }}
      </div>
      <div class="show-d"><el-tag>拖拽后排序 :</el-tag> {{ newList }}</div>
    </div>
  </div>
</template>

<script>
import Sortable from 'sortablejs';
//拖拽表格
export default {
  name: 'DragTable',
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
      sortable: null,
      oldList: [],
      newList: [],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    async getList() {
      this.oldList = this.list.map((v) => v.id);
      this.newList = this.oldList.slice();
      this.$nextTick(() => {
        this.setSort();
      });
    },
    setSort() {
      const el = this.$refs.dragTable.$el.querySelectorAll(
        '.el-table__body-wrapper > table > tbody'
      )[0];
      this.sortable = Sortable.create(el, {
        ghostClass: 'sortable-ghost', // Class name for the drop placeholder,
        setData: function(dataTransfer) {
          // to avoid Firefox bug
          // Detail see : https://github.com/RubaXa/Sortable/issues/1012
          dataTransfer.setData('Text', '');
        },
        onEnd: (evt) => {
          const targetRow = this.list.splice(evt.oldIndex, 1)[0];
          this.list.splice(evt.newIndex, 0, targetRow);

          // for show the changes, you can delete in you code
          const tempIndex = this.newList.splice(evt.oldIndex, 1)[0];
          this.newList.splice(evt.newIndex, 0, tempIndex);
        },
      });
    },
  },
};
</script>

<style>
.sortable-ghost {
  opacity: 0.8;
  color: #fff !important;
  background: #42b983 !important;
}
</style>

<style scoped>
.icon-star {
  margin-right: 2px;
}
.drag-handler {
  width: 20px;
  height: 20px;
  cursor: pointer;
}
.show-d {
  margin-top: 15px;
}
</style>
