<template>
  <div class="app-container">
    <div class="real-container">
      <div>
        <aside>
          基于
          <a
            class="link-type"
            href="//github.com/dai-siki/vue-image-crop-upload"
            >vue-image-crop-upload
          </a>
          图片上传
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
        <el-button type="primary" icon="el-icon-upload" @click="toggleShow"
          >上传头像</el-button
        >
      </div>

      <div style="margin-top:20px">
        <aside>
          基于
          <a
            class="link-type"
            href="https://element.faas.ele.me/#/zh-CN/component/upload"
            >el-upload</a
          >
          的文件上传
        </aside>
        <el-button
          type="primary"
          icon="el-icon-upload"
          @click="actiondialogVisible = true"
          >上传文件(action方式)</el-button
        >
        <el-dialog
          v-el-drag-dialog
          title="action方式上传文件"
          :visible.sync="actiondialogVisible"
          width="28%"
          :close-on-click-modal="false"
          @close="actioncleanupload"
          center
        >
          <el-upload
            ref="actionuploadlist"
            class="upload-demo"
            list-type="text"
            :auto-upload="false"
            :on-success="actionsuccess"
            :on-error="actionerror"
            :before-upload="actionbefore"
            :action="actionurl()"
            drag
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">
              文件大小不超过300MB
            </div>
          </el-upload>
          <span slot="footer" class="dialog-footer">
            <el-button @click="actiondialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="actionupload">确 定</el-button>
          </span>
        </el-dialog>
      </div>

      <div style="margin-top:20px">
        <el-button
          type="primary"
          icon="el-icon-upload"
          @click="httpdialogVisible = true"
          >上传文件(http-request方式)</el-button
        >
        <el-dialog
          v-el-drag-dialog
          title="http-request方式上传文件"
          :visible.sync="httpdialogVisible"
          width="28%"
          :close-on-click-modal="false"
          center
        >
          <el-upload
            class="upload-demo"
            list-type="text"
            action=""
            :http-request="httpupload"
            drag
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
          </el-upload>
          <span slot="footer" class="dialog-footer">
            <el-button @click="httpdialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="httpdialogVisible = false"
              >确 定</el-button
            >
          </span>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import PanThumb from '@/components/PanThumb';
import myUpload from 'vue-image-crop-upload'; //基于https://github.com/dai-siki/vue-image-crop-upload
import elDragDialog from '@/directive/el-drag-dialog'; // 基于el-dialog的拖拽dialog
import { upload } from '@/api/file';

export default {
  name: 'AvatarUploadDemo',
  components: { PanThumb, 'my-upload': myUpload },
  directives: { elDragDialog },
  data() {
    return {
      show: false,
      params: {
        token: '123456798',
        name: 'avatar',
      },
      headers: {
        smail: '*_~',
      },
      imgDataUrl:
        'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', // the datebase64 url of created imag
      actiondialogVisible: false,
      httpdialogVisible: false,
    };
  },
  methods: {
    toggleShow() {
      this.show = !this.show;
    },
    //图片截取完成事件（上传前)
    cropSuccess(imgDataUrl, field) {
      console.log('-------- crop success --------');
      this.imgDataUrl = imgDataUrl;
    },
    //上传成功回调
    cropUploadSuccess(jsonData, field) {
      console.log('-------- upload success --------');
      console.log(jsonData);
      console.log('field: ' + field);
    },
    //上传失败回调
    cropUploadFail(status, field) {
      console.log('-------- upload fail --------');
      console.log(status);
      console.log('field: ' + field);
    },
    //返回action方式上传文件接口
    actionurl() {
      return process.env.VUE_APP_BASE_API + '/file/actionupload';
    },
    //action方式上传文件
    actionupload() {
      this.$refs.actionuploadlist.submit();
    },
    //action方式上传前
    actionbefore(file) {
      const filesize = file.size / 1024 / 1024 < 300;
      if (!filesize) {
        this.$notify({
          message: '文件大小超过300MB',
          type: 'warning',
        });
      }
      return filesize;
    },
    //action方式上传成功回调
    actionsuccess(res, file, fileList) {
      this.$notify({
        message: '上传成功',
        type: 'success',
      });
    },
    //action方式上传失败回调
    actionerror(res, file, filelist) {
      this.$notify.error({
        message: '上传失败',
      });
    },
    //清除action方式上传列表
    actioncleanupload() {
      this.$refs.actionuploadlist.clearFiles();
    },
    //http方式上传文件
    httpupload(param) {
      alert('开发中.....');
      // FormData 对象
      var form = new FormData();
      form.append('file', param.file);
      upload(form)
        .then((response) => {
          alert(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
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
.components-container /deep/ .el-dialog {
  border-radius: 15px !important;
  -moz-box-shadow: 0px 0px 50px black;
  -webkit-box-shadow: 0px 0px 50px black;
  box-shadow: 0px 0px 50px black;
}
</style>
