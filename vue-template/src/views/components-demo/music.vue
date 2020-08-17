<template>
  <div class="app-container">
    <div class="real-container">
      <el-divider content-position="left">
        基于
        <a class="link-type" href="https://www.npmjs.com/package/zw-player"
          >zw-player</a
        >
        音乐播放器
      </el-divider>
      <player> </player>
    </div>
  </div>
</template>

<script>
import Player from 'zw-player';
export default {
  data() {
    return {};
  },
  components: { Player },
};
</script>

<style></style>
