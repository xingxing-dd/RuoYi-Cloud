import request from '@/utils/request'

// 查询充币地址列表
export function listNetwork(query) {
  return request({
    url: '/recharge/network/list',
    method: 'get',
    params: query
  })
}

// 查询充币地址详细
export function getNetwork(id) {
  return request({
    url: '/recharge/network/' + id,
    method: 'get'
  })
}

// 新增充币地址
export function addNetwork(data) {
  return request({
    url: '/recharge/network',
    method: 'post',
    data: data
  })
}

// 修改充币地址
export function updateNetwork(data) {
  return request({
    url: '/recharge/network',
    method: 'put',
    data: data
  })
}

// 删除充币地址
export function delNetwork(id) {
  return request({
    url: '/recharge/network/' + id,
    method: 'delete'
  })
}
