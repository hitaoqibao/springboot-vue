import request from '@/utils/request'
import {
  getToken
} from '@/utils/auth'

export function allinfo() {
  return request({
    url: '/info/allinfo',
    params: {
      token: getToken(),
    }
  })
}

export function exporttable() {
  return request({
    url: '/info/export',
    params: {
      token: getToken(),
    }
  })
}