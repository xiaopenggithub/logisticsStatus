import request from '@/utils/request'

// 查询物流信息列表
export function listLogistics(query) {
  return request({
    url: '/logistics/logistics/list',
    method: 'get',
    params: query
  })
}

// 查询物流信息详细
export function getLogistics(id) {
  return request({
    url: '/logistics/logistics/' + id,
    method: 'get'
  })
}

// 新增物流信息
export function addLogistics(data) {
  return request({
    url: '/logistics/logistics',
    method: 'post',
    data: data
  })
}

// 修改物流信息
export function updateLogistics(data) {
  return request({
    url: '/logistics/logistics',
    method: 'put',
    data: data
  })
}

// 删除物流信息
export function delLogistics(id) {
  return request({
    url: '/logistics/logistics/' + id,
    method: 'delete'
  })
}
