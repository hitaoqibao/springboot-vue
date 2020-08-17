<template>
  <div class="app-container">
    <div class="real-container">
      <aside>
        全局返回顶部代码在：src\layout\index.vue(el-backtop标签)
      </aside>
      <div v-for="(item, index) in 100" :key="index" style="padding: 20px;">
        测试滚轮显示返回顶部-{{ index }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BackToTopDemo',
  data() {
    return {};
  },
};
</script>

<style scoped></style>
