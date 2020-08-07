/** 当你的路由表太长时，可以分成小的模块 **/

import Layout from '@/layout'

const tableRouter = {
  path: '/table',
  component: Layout,
  redirect: '/table/complex-table',
  name: 'Table',
  meta: {
    title: '表格',
    icon: 'table'
  },
  children: [{
      path: 'dynamic-table',
      component: () => import('@/views/table/dynamic-table/index'),
      name: 'DynamicTable',
      meta: {
        title: '动态表格'
      }
    },
    {
      path: 'drag-table',
      component: () => import('@/views/table/drag-table'),
      name: 'DragTable',
      meta: {
        title: '拖拽表格'
      }
    },
  ]
}
export default tableRouter