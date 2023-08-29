import request from '@/utils/request'

// 查询用户钱包流水列表
export function listWallet-flow(query) {
  return request({
    url: '/client/wallet-flow/list',
    method: 'get',
    params: query
  })
}

// 查询用户钱包流水详细
export function getWallet-flow(id) {
  return request({
    url: '/client/wallet-flow/' + id,
    method: 'get'
  })
}

// 新增用户钱包流水
export function addWallet-flow(data) {
  return request({
    url: '/client/wallet-flow',
    method: 'post',
    data: data
  })
}

// 修改用户钱包流水
export function updateWallet-flow(data) {
  return request({
    url: '/client/wallet-flow',
    method: 'put',
    data: data
  })
}

// 删除用户钱包流水
export function delWallet-flow(id) {
  return request({
    url: '/client/wallet-flow/' + id,
    method: 'delete'
  })
}
