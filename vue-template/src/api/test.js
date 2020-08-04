import request from '@/utils/request'
import {
  getToken
} from '@/utils/auth'

export function getusertable(page, limit, value) {
  return request({
    url: '/user/getusertable',
    method: 'post',
    params: {
      token: getToken(),
      page,
      limit,
      value
    }
  })
}