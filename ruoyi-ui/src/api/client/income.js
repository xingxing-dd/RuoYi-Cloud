import request from '@/utils/request'

// 查询用户收益列表
export function listIncome(query) {
  return request({
    url: '/client/income/list',
    method: 'get',
    params: query
  })
}

// 查询用户收益详细
export function getIncome(id) {
  return request({
    url: '/client/income/' + id,
    method: 'get'
  })
}

// 新增用户收益
export function addIncome(data) {
  return request({
    url: '/client/income',
    method: 'post',
    data: data
  })
}

// 修改用户收益
export function updateIncome(data) {
  return request({
    url: '/client/income',
    method: 'put',
    data: data
  })
}

// 删除用户收益
export function delIncome(id) {
  return request({
    url: '/client/income/' + id,
    method: 'delete'
  })
}
