import request from '@/utils/request'

// 查询账户信息列表
export function listUser(query) {
  return request({
    url: '/client/user/list',
    method: 'get',
    params: query
  })
}

// 查询账户信息详细
export function getUser(userId) {
  return request({
    url: '/client/user/' + userId,
    method: 'get'
  })
}

// 新增账户信息
export function addUser(data) {
  return request({
    url: '/client/user',
    method: 'post',
    data: data
  })
}

// 修改账户信息
export function updateUser(data) {
  return request({
    url: '/client/user',
    method: 'put',
    data: data
  })
}

// 删除账户信息
export function delUser(userId) {
  return request({
    url: '/client/user/' + userId,
    method: 'delete'
  })
}
