<template>
  <div class="app-container">
    <div class="real-container">
      <aside>
        基于
        <a class="link-type" href="https://www.npmjs.com/package/zw-player"
          >zw-player</a
        >
        音乐播放器
      </aside>
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
