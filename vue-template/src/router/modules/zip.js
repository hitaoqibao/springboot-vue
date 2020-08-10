/** zip模块 **/

import Layout from '@/layout'

const zipRouter = {
  path: '/zip',
  component: Layout,
  redirect: '/zip/download',
  alwaysShow: true,
  name: 'Zip',
  meta: {
    title: 'zip',
    icon: 'zip'
  },
  children: [{
    path: 'download',
    component: () => import('@/views/zip/index'),
    name: 'ExportZip',
    meta: {
      title: '导出 zip'
    }
  }]
}
export default zipRouter