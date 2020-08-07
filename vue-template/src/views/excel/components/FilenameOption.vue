<template>
  <div style="display:inline-block;">
    <label class="radio-label" style="padding-left:0;">文件名:</label>
    <el-input
      v-model="filename"
      placeholder="请输入文件名（默认为excel-list）"
      style="width:350px;"
      prefix-icon="el-icon-folder"
      size="medium"
    />
  </div>
</template>

<script>
export default {
  props: {
    value: {
      type: String,
      default: '',
    },
  },
  computed: {
    filename: {
      get() {
        return this.value;
      },
      set(val) {
        this.$emit('input', val);
      },
    },
  },
};
</script>
