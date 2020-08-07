import request from '@/utils/request'
import {
  getToken
} from '@/utils/auth'

export function upload(form) {
  return request({
    url: '/file/httpupload',
    method: 'post',
    params: {
      token: getToken(),
      form
    }
  })
}