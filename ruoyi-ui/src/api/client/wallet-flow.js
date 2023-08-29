import request from '@/utils/request'

// 查询用户钱包流水列表
export function listWalletFlow(query) {
  return request({
    url: '/client/wallet-flow/list',
    method: 'get',
    params: query
  })
}

// 查询用户钱包流水详细
export function getWalletFlow(id) {
  return request({
    url: '/client/wallet-flow/' + id,
    method: 'get'
  })
}

// 新增用户钱包流水
export function addWalletFlow(data) {
  return request({
    url: '/client/wallet-flow',
    method: 'post',
    data: data
  })
}

// 修改用户钱包流水
export function updateWalletflow(data) {
  return request({
    url: '/client/wallet-flow',
    method: 'put',
    data: data
  })
}

// 删除用户钱包流水
export function delWalletFlow(id) {
  return request({
    url: '/client/wallet-flow/' + id,
    method: 'delete'
  })
}
