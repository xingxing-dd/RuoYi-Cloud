import request from '@/utils/request'

// 查询用户资产列表
export function listWallet(query) {
  return request({
    url: '/client/wallet/list',
    method: 'get',
    params: query
  })
}

// 查询用户资产详细
export function getWallet(id) {
  return request({
    url: '/client/wallet/' + id,
    method: 'get'
  })
}

// 新增用户资产
export function addWallet(data) {
  return request({
    url: '/client/wallet',
    method: 'post',
    data: data
  })
}

// 修改用户资产
export function updateWallet(data) {
  return request({
    url: '/client/wallet',
    method: 'put',
    data: data
  })
}

// 删除用户资产
export function delWallet(id) {
  return request({
    url: '/client/wallet/' + id,
    method: 'delete'
  })
}
