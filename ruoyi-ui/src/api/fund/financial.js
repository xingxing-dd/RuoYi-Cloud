import request from '@/utils/request'

// 查询余额宝订单列表
export function listFinancial(query) {
  return request({
    url: '/fund/financial/list',
    method: 'get',
    params: query
  })
}

// 查询余额宝订单详细
export function getFinancial(id) {
  return request({
    url: '/fund/financial/' + id,
    method: 'get'
  })
}

// 新增余额宝订单
export function addFinancial(data) {
  return request({
    url: '/fund/financial',
    method: 'post',
    data: data
  })
}

// 修改余额宝订单
export function updateFinancial(data) {
  return request({
    url: '/fund/financial',
    method: 'put',
    data: data
  })
}

// 删除余额宝订单
export function delFinancial(id) {
  return request({
    url: '/fund/financial/' + id,
    method: 'delete'
  })
}
