import request from '@/utils/request'

// 查询提现订单列表
export function listWithdraw(query) {
  return request({
    url: '/fund/withdraw/list',
    method: 'get',
    params: query
  })
}

// 查询提现订单详细
export function getWithdraw(id) {
  return request({
    url: '/fund/withdraw/' + id,
    method: 'get'
  })
}

// 新增提现订单
export function addWithdraw(data) {
  return request({
    url: '/fund/withdraw',
    method: 'post',
    data: data
  })
}

// 修改提现订单
export function updateWithdraw(data) {
  return request({
    url: '/fund/withdraw',
    method: 'put',
    data: data
  })
}

// 删除提现订单
export function delWithdraw(id) {
  return request({
    url: '/fund/withdraw/' + id,
    method: 'delete'
  })
}
