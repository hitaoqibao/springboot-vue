<template>
  <div class="app-container">
    <div class="real-container">
      <div :style="{ marginBottom: '20px' }">
        <el-button type="primary" @click="getName"
          >切换视频：{{ filename }}</el-button
        >
      </div>
      <el-row :gutter="20">
        <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
          <el-card shadow="hover">
            <div slot="header">
              基于
              <a
                class="link-type"
                href="https://www.w3school.com.cn/tags/tag_video.asp"
                >HTML5</a
              >
              视频播放
            </div>
            <div class="video">
              <video
                style="{width:300px;height:300px;object-fit: fill}"
                controls
                autoplay
                :src="getUrl()"
              />
            </div>
          </el-card>
        </el-col>
        <el-rol :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
          <el-card shadow="hover">
            <div slot="header">
              基于
              <a
                class="link-type"
                href="https://github.com/surmon-china/vue-video-player"
                >vue-video-player</a
              >
              视频播放
            </div>
            <div class="demo">
              <video-player
                class="video-player vjs-custom-skin"
                ref="videoPlayer"
                :playsinline="true"
                :options="playerOptions"
              ></video-player>
            </div>
          </el-card>
        </el-rol>
      </el-row>
      <aside content-position="left" :style="{ marginTop: '20px' }">
        基于
        <a class="link-type" href="https://www.npmjs.com/package/zx-player"
          >zx-player</a
        >
        视频播放
      </aside>
      <el-row :gutter="20">
        <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
          <el-card shadow="hover">
            <div slot="header">播放传统MP4</div>
            <player-mp4 :config="config1" @player="Player1 = $event" />
          </el-card>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
          <el-card shadow="hover">
            <div slot="header">播放m3u8，且不暴露视频地址</div>
            <player-hls
              :config="config2"
              @player="Player2 = $event"
            ></player-hls>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { videoPlayer } from 'vue-video-player'; //https://github.com/surmon-china/vue-video-player
import 'vue-video-player/src/custom-theme.css';
import 'video.js/dist/video-js.css';
import { allowedNodeEnvironmentFlags } from 'process';
import { getToken } from '@/utils/auth';
import { PlayerMp4, PlayerHls } from '@/plugins/Player.js';

//视频播放
export default {
  name: 'Video',
  components: { videoPlayer, PlayerMp4, PlayerHls },
  data() {
    return {
      //传入后台文件名
      filename: '登录',
      playerOptions: {
        //播放速度
        playbackRates: [0.5, 1.0, 1.5, 2.0],
        //如果true,浏览器准备好时开始回放。
        autoplay: true,
        // 默认情况下将会消除任何音频。
        muted: false,
        // 导致视频一结束就重新开始。
        loop: false,
        // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        preload: 'auto',
        language: 'zh-CN',
        // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        aspectRatio: '15:9',
        // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        fluid: true,
        sources: [
          {
            //类型
            type: 'video/mp4',
            //url地址初始化为空，进行动态赋值
            src: '',
          },
        ],
        //你的封面地址
        poster: '',
        //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        notSupportedMessage: '此视频暂无法播放，请稍后再试',
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          //全屏按钮
          fullscreenToggle: true,
        },
      },
      config1: {
        id: 'mse1',
        url: '',
        volume: 1,
        autoplay: false,
      },
      Player1: null,
      config2: {
        id: 'mse2',
        url: 'https://cdn.jsdelivr.net/gh/chuzhixin/videos@master/video.m3u8',
        volume: 1,
        autoplay: false,
      },
      Player2: null,
    };
  },
  created() {
    this.getSrc();
  },
  methods: {
    //html5<video>标签路径
    getUrl() {
      return (
        process.env.VUE_APP_BASE_API +
        '/file/video?filename=' +
        this.filename +
        '&token=' +
        getToken()
      );
    },
    //vue-video-player路径
    getSrc() {
      this.playerOptions['sources'][0]['src'] =
        process.env.VUE_APP_BASE_API +
        '/file/video?filename=' +
        this.filename +
        '&token=' +
        getToken();
      this.config1.url =
        process.env.VUE_APP_BASE_API +
        '/file/video?filename=' +
        this.filename +
        '&token=' +
        getToken();
    },
    getName() {
      if (this.filename === '登录') {
        this.filename = '注册';
      } else {
        this.filename = '登录';
      }

      this.getSrc();
    },
  },
};
</script>

<style scoped>
.demo {
  width: 500px;
  height: 300px;
}
</style>
