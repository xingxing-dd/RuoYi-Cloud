import request from '@/utils/request'

// 查询交易订单列表
export function listTrade(query) {
  return request({
    url: '/fund/trade/list',
    method: 'get',
    params: query
  })
}

// 查询交易订单详细
export function getTrade(id) {
  return request({
    url: '/fund/trade/' + id,
    method: 'get'
  })
}

// 新增交易订单
export function addTrade(data) {
  return request({
    url: '/fund/trade',
    method: 'post',
    data: data
  })
}

// 修改交易订单
export function updateTrade(data) {
  return request({
    url: '/fund/trade',
    method: 'put',
    data: data
  })
}

// 删除交易订单
export function delTrade(id) {
  return request({
    url: '/fund/trade/' + id,
    method: 'delete'
  })
}
