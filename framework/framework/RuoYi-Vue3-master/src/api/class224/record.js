import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 查询压浆材料配合比数据列表
export function getCS42Records(query) {
  return request({
    url: '/getCS42Records',
    method: 'get',
    params: query
  })
}

// 查询压浆材料配合比数据详细
export function getCS42Record(id) {
  return request({
    url: '/getCS42Record/' + parseStrEmpty(id),
    method: 'get'
  })
}

// 新增压浆材料配合比数据
export function addCS42Record(data) {
  return request({
    url: '/addCS42Record',
    method: 'post',
    data: data
  })
}

// 修改压浆材料配合比数据
export function editCS42Record(data) {
  return request({
    url: '/editCS42Record',
    method: 'put',
    data: data
  })
}

// 删除压浆材料配合比数据
export function deleteCS42Record(id) {
  return request({
    url: '/deleteCS42Record/' + id,
    method: 'delete'
  })
}

// 压浆材料配合比数据头像上传
export function uploadAvatar(data) {
  return request({
    url: '//profile/avatar',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: data
  })
}