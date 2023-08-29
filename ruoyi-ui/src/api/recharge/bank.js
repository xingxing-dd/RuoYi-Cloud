import request from '@/utils/request'

// 查询充值银行信息列表
export function listBank(query) {
  return request({
    url: '/recharge/bank/list',
    method: 'get',
    params: query
  })
}

// 查询充值银行信息详细
export function getBank(id) {
  return request({
    url: '/recharge/bank/' + id,
    method: 'get'
  })
}

// 新增充值银行信息
export function addBank(data) {
  return request({
    url: '/recharge/bank',
    method: 'post',
    data: data
  })
}

// 修改充值银行信息
export function updateBank(data) {
  return request({
    url: '/recharge/bank',
    method: 'put',
    data: data
  })
}

// 删除充值银行信息
export function delBank(id) {
  return request({
    url: '/recharge/bank/' + id,
    method: 'delete'
  })
}
