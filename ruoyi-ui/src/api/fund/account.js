import request from '@/utils/request'

// 查询账号管理列表
export function listAccount(query) {
  return request({
    url: '/fund/account/list',
    method: 'get',
    params: query
  })
}

// 查询账号管理详细
export function getAccount(id) {
  return request({
    url: '/fund/account/' + id,
    method: 'get'
  })
}

// 新增账号管理
export function addAccount(data) {
  return request({
    url: '/fund/account',
    method: 'post',
    data: data
  })
}

// 修改账号管理
export function updateAccount(data) {
  return request({
    url: '/fund/account',
    method: 'put',
    data: data
  })
}

// 删除账号管理
export function delAccount(id) {
  return request({
    url: '/fund/account/' + id,
    method: 'delete'
  })
}
