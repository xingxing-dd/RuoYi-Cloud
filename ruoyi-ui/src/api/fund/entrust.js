import request from '@/utils/request'

// 查询委托订单列表
export function listEntrust(query) {
  return request({
    url: '/fund/entrust/list',
    method: 'get',
    params: query
  })
}

// 查询委托订单详细
export function getEntrust(id) {
  return request({
    url: '/fund/entrust/' + id,
    method: 'get'
  })
}

// 新增委托订单
export function addEntrust(data) {
  return request({
    url: '/fund/entrust',
    method: 'post',
    data: data
  })
}

// 修改委托订单
export function updateEntrust(data) {
  return request({
    url: '/fund/entrust',
    method: 'put',
    data: data
  })
}

// 删除委托订单
export function delEntrust(id) {
  return request({
    url: '/fund/entrust/' + id,
    method: 'delete'
  })
}
