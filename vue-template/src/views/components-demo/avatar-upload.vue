<template>
  <div class="components-container">
    <div>
      <aside>
        基于
        <a
          class="link-type"
          href="//github.com/dai-siki/vue-image-crop-upload"
        >vue-image-crop-upload</a> 图片上传
      </aside>
      <my-upload
        field="img"
        @crop-success="cropSuccess"
        @crop-upload-success="cropUploadSuccess"
        @crop-upload-fail="cropUploadFail"
        v-model="show"
        :width="300"
        :height="300"
        url="https://httpbin.org/post"
        :params="params"
        :headers="headers"
        img-format="png"
      ></my-upload>
      <pan-thumb :image="imgDataUrl" />
      <el-button type="primary" icon="el-icon-upload" @click="toggleShow">上传头像</el-button>
    </div>
    <div style="margin-top:20px">
      <aside>
        基于
        <a class="link-type" href="https://element.faas.ele.me/#/zh-CN/component/upload">el-upload</a> 文件上传
      </aside>
      <el-upload
        class="upload-demo"
        :file-list="fileList"
        list-type="picture"
        drag
        action="https://jsonplaceholder.typicode.com/posts/"
        multiple
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
    </div>
  </div>
</template>

<script>
import PanThumb from "@/components/PanThumb";
//基于https://github.com/dai-siki/vue-image-crop-upload
import myUpload from "vue-image-crop-upload";

export default {
  name: "AvatarUploadDemo",
  components: { PanThumb, "my-upload": myUpload },
  data() {
    return {
      show: false,
      params: {
        token: "123456798",
        name: "avatar",
      },
      headers: {
        smail: "*_~",
      },
      imgDataUrl:
        "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif", // the datebase64 url of created imag
    };
  },
  methods: {
    toggleShow() {
      this.show = !this.show;
    },
    //图片截取完成事件（上传前)
    cropSuccess(imgDataUrl, field) {
      console.log("-------- crop success --------");
      this.imgDataUrl = imgDataUrl;
    },
    //上传成功回调
    cropUploadSuccess(jsonData, field) {
      console.log("-------- upload success --------");
      console.log(jsonData);
      console.log("field: " + field);
    },
    //上传失败回调
    cropUploadFail(status, field) {
      console.log("-------- upload fail --------");
      console.log(status);
      console.log("field: " + field);
    },
  },
};
</script>

<style scoped>
.avatar {
  width: 200px;
  height: 200px;
  border-radius: 50%;
}
</style>
