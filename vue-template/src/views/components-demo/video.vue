<template>
  <div class="app-container">
    <aside>
      基于
      <a class="link-type" href="https://www.w3school.com.cn/tags/tag_video.asp">HTML5</a>
      视频播放
    </aside>
    <div class="video">
      <video style="{width:300px;height:300px;object-fit: fill}" controls autoplay :src="getUrl()" />
    </div>

    <aside>
      基于
      <a
        class="link-type"
        href="https://github.com/surmon-china/vue-video-player"
      >vue-video-player</a>
      视频播放
    </aside>
    <div class="demo">
      <video-player
        class="video-player vjs-custom-skin"
        ref="videoPlayer"
        :playsinline="true"
        :options="playerOptions"
      ></video-player>
    </div>
    <el-button type="primary" @click="getName">切换视频：{{filename}}</el-button>
  </div>
</template>

<script>
import { videoPlayer } from "vue-video-player"; //https://github.com/surmon-china/vue-video-player
import "vue-video-player/src/custom-theme.css";
import "video.js/dist/video-js.css";
import { allowedNodeEnvironmentFlags } from "process";
import { getToken } from "@/utils/auth";

//视频播放
export default {
  name: "Video",
  components: { videoPlayer },
  data() {
    return {
      //传入后台文件名
      filename: "登录",
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
        preload: "auto",
        language: "zh-CN",
        // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        aspectRatio: "16:9",
        // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        fluid: true,
        sources: [
          {
            //类型
            type: "video/mp4",
            //url地址初始化为空，进行动态赋值
            src: "",
          },
        ],
        //你的封面地址
        poster: "",
        //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        notSupportedMessage: "此视频暂无法播放，请稍后再试",
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          //全屏按钮
          fullscreenToggle: true,
        },
      },
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
        "/file/video?filename=" +
        this.filename +
        "&token=" +
        getToken()
      );
    },
    //vue-video-player路径
    getSrc() {
      this.playerOptions["sources"][0]["src"] =
        process.env.VUE_APP_BASE_API +
        "/file/video?filename=" +
        this.filename +
        "&token=" +
        getToken();
    },
    getName() {
      if (this.filename === "登录") {
        this.filename = "注册";
      } else {
        this.filename = "登录";
      }

      this.getSrc();
    },
  },
};
</script>

<style scoped>
.demo {
  width: 500px;
  height: 338px;
}
</style>
