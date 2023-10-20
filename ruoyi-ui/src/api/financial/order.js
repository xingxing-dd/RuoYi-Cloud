import request from '@/utils/request'

// 查询余额宝订单列表
export function listOrder(query) {
  return request({
    url: '/financial/order/list',
    method: 'get',
    params: query
  })
}

// 查询余额宝订单详细
export function getOrder(id) {
  return request({
    url: '/financial/order/' + id,
    method: 'get'
  })
}

// 新增余额宝订单
export function addOrder(data) {
  return request({
    url: '/financial/order',
    method: 'post',
    data: data
  })
}

// 修改余额宝订单
export function updateOrder(data) {
  return request({
    url: '/financial/order',
    method: 'put',
    data: data
  })
}

// 删除余额宝订单
export function delOrder(id) {
  return request({
    url: '/financial/order/' + id,
    method: 'delete'
  })
}
