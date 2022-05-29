import request from '@/utils/request'

// 查询快件信息列表
export function listItems(query) {
  return request({
    url: '/logistics/items/list',
    method: 'get',
    params: query
  })
}

// 查询快件信息详细
export function getItems(id) {
  return request({
    url: '/logistics/items/' + id,
    method: 'get'
  })
}

// 新增快件信息
export function addItems(data) {
  return request({
    url: '/logistics/items',
    method: 'post',
    data: data
  })
}

// 修改快件信息
export function updateItems(data) {
  return request({
    url: '/logistics/items',
    method: 'put',
    data: data
  })
}

// 删除快件信息
export function delItems(id) {
  return request({
    url: '/logistics/items/' + id,
    method: 'delete'
  })
}
