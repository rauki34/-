import upload from '@/utils/upload'
import request from '@/utils/request'

// 列表查询
export function getCS42Records(pageNum, pageSize) {
  const data = {
    pageNum,
    pageSize
  }
  return request({
    url: '/getCS42Records',
    method: 'get',
    params: data
  })
}

// 添加数据
export function addCS42Record(data) {
  return request({
    url: '/addCS42Record',
    method: 'post',
	data: data
  })
}

