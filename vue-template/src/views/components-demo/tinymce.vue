<template>
  <div class="app-container">
    <div class="real-container">
      <aside>
        基于
        <a
          class="link-type"
          href="https://cdn.jsdelivr.net/npm/tinymce-all-in-one@latest/"
          >tinymce</a
        >
        富文本编辑器，采用CDN引入方式
      </aside>
      <div>
        <tinymce v-model="content" :height="300" />
      </div>
      <div class="editor-content" v-html="content" />
    </div>
  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce';

export default {
  name: 'TinymceDemo',
  components: { Tinymce },
  data() {
    return {
      content: '',
    };
  },
};
</script>

<style scoped>
.editor-content {
  margin-top: 20px;
}
</style>
