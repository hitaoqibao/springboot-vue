/** Excel模块 **/

import Layout from '@/layout'

const excelRouter = {
  path: '/excel',
  component: Layout,
  redirect: '/excel/export-excel',
  name: 'Excel',
  meta: {
    title: 'excel',
    icon: 'excel'
  },
  children: [{
      path: 'export-excel',
      component: () => import('@/views/excel/export-excel'),
      name: 'ExportExcel',
      meta: {
        title: '前端 导出Excel'
      }
    },
    {
      path: 'export-selected-excel',
      component: () => import('@/views/excel/select-excel'),
      name: 'SelectExcel',
      meta: {
        title: '前端 导出选择项'
      }
    },
    {
      path: 'export-merge-header',
      component: () => import('@/views/excel/merge-header'),
      name: 'MergeHeader',
      meta: {
        title: '前端 导出多级表头'
      }
    },
    {
      path: 'upload-excel',
      component: () => import('@/views/excel/upload-excel'),
      name: 'UploadExcel',
      meta: {
        title: '前端 上传Excel'
      }
    },
    {
      path: 'end-export-excel',
      component: () => import('@/views/excel/end-export-excel'),
      name: 'EndExportExcel',
      meta: {
        title: '后端 导出Excel'
      }
    }
  ]
}
export default excelRouter