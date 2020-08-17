<template>
  <div class="app-container">
    <div class="real-container">
      <el-divider content-position="left">
        基于
        <a class="link-type" href="https://github.com/SortableJS/Vue.Draggable"
          >Vue.Draggable
        </a>
      </el-divider>
      <div class="editor-container">
        <dnd-list
          :list1="list1"
          :list2="list2"
          list1-title="列表"
          list2-title="文章池"
        />
      </div>
    </div>
  </div>
</template>

<script>
import DndList from '@/components/DndList';

export default {
  name: 'DndListDemo',
  components: { DndList },
  data() {
    return {
      list1: [
        { id: 1, author: '文章1' },
        { id: 2, author: '文章2' },
        { id: 3, author: '文章3' },
        { id: 4, author: '文章4' },
        { id: 5, author: '文章5' },
      ],
      list2: [
        { id: 6, author: '文章6' },
        { id: 7, author: '文章7' },
        { id: 8, author: '文章8' },
        { id: 9, author: '文章9' },
        { id: 10, author: '文章10' },
        { id: 11, author: '文章11' },
        { id: 12, author: '文章12' },
        { id: 13, author: '文章13' },
        { id: 14, author: '文章14' },
        { id: 15, author: '文章15' },
      ],
    };
  },
  created() {},
  methods: {},
};
</script>
