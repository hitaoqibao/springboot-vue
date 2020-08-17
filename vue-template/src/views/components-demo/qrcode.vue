<template>
  <div class="app-container">
    <div class="real-container">
      <el-divider content-position="left">
        基于
        <a class="link-type" href="https://www.npmjs.com/package/vue-qart"
          >vue-qart</a
        >
        二维码生成
      </el-divider>
      <el-row>
        <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-card shadow="hover">
            <div slot="header"><span>二维码示例</span></div>
            <a target="_blank" :href="url">
              <vab-qr-code :image-path="imagePath" :url="url"></vab-qr-code>
            </a>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import VabQrCode from '@/components/QrCode';

export default {
  name: 'QrCode',
  components: {
    VabQrCode,
  },
  data() {
    return {
      url: 'https://www.baidu.com',
      imagePath: require('@/assets/image/qr.png'),
    };
  },
  created() {},
  mounted() {},
  methods: {},
};
</script>

<style lang="scss" scoped>
.app-container {
  ::v-deep {
    .el-card__body {
      display: flex;
      align-content: center;
      justify-content: center;
    }
  }
}
</style>
