import request from '@/utils/request'

// 查询实名认证列表
export function listAuth(query) {
  return request({
    url: '/client/auth/list',
    method: 'get',
    params: query
  })
}

// 查询实名认证详细
export function getAuth(id) {
  return request({
    url: '/client/auth/' + id,
    method: 'get'
  })
}

// 新增实名认证
export function addAuth(data) {
  return request({
    url: '/client/auth',
    method: 'post',
    data: data
  })
}

// 修改实名认证
export function updateAuth(data) {
  return request({
    url: '/client/auth',
    method: 'put',
    data: data
  })
}

// 删除实名认证
export function delAuth(id) {
  return request({
    url: '/client/auth/' + id,
    method: 'delete'
  })
}
