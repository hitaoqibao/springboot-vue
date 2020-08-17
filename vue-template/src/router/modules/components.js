/** 组件模块 **/

import Layout from '@/layout';

const componentsRouter = {
  path: '/components',
  component: Layout,
  redirect: 'noRedirect',
  name: 'ComponentDemo',
  meta: {
    title: '组件',
    icon: 'component',
  },
  children: [{
      path: 'tinymce',
      component: () => import('@/views/components-demo/tinymce'),
      name: 'TinymceDemo',
      meta: {
        title: '富文本编辑器'
      }
    }, {
      path: 'markdown',
      component: () => import('@/views/components-demo/markdown'),
      name: 'MarkdownDemo',
      meta: {
        title: 'markdown 编辑器'
      }
    }, {
      path: 'mixin',
      component: () => import('@/views/components-demo/mixin'),
      name: 'ComponentMixinDemo',
      meta: {
        title: '小组件'
      }
    },
    {
      path: 'qrcode',
      component: () => import('@/views/components-demo/qrcode'),
      name: 'qrcode',
      meta: {
        title: '二维码'
      }
    },
    {
      path: 'avatar-upload',
      component: () => import('@/views/components-demo/avatar-upload'),
      name: 'AvatarUploadDemo',
      meta: {
        title: '文件上传'
      }
    },
    {
      path: 'back-to-top',
      component: () => import('@/views/components-demo/back-to-top'),
      name: 'BackToTopDemo',
      meta: {
        title: '返回顶部'
      }
    },
    {
      path: 'drag-dialog',
      component: () => import('@/views/components-demo/drag-dialog'),
      name: 'DragDialogDemo',
      meta: {
        title: '拖拽 Dialog',
      },
    },
    {
      path: 'drag-select',
      component: () => import('@/views/components-demo/drag-select'),
      name: 'DragSelectDemo',
      meta: {
        title: '拖拽 Select',
      },
    },
    {
      path: 'dnd-list',
      component: () => import('@/views/components-demo/dnd-list'),
      name: 'DndListDemo',
      meta: {
        title: '列表拖拽'
      }
    },
    {
      path: 'drag-kanban',
      component: () => import('@/views/components-demo/drag-kanban'),
      name: 'DragKanbanDemo',
      meta: {
        title: '可拖拽看板'
      }
    },
    {
      path: 'video',
      component: () => import('@/views/components-demo/video'),
      name: 'Video',
      meta: {
        title: '视频播放'
      }
    },
    {
      path: 'music',
      component: () => import('@/views/components-demo/music'),
      name: 'Music',
      meta: {
        title: '音乐播放'
      }
    },
    {
      path: 'region',
      component: () => import('@/views/components-demo/region'),
      name: 'Region',
      meta: {
        title: '行政区划选择器'
      }
    }
  ],
};

export default componentsRouter;