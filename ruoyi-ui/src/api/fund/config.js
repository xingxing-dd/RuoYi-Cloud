import request from '@/utils/request'

// 查询费用配置列表
export function listConfig(query) {
  return request({
    url: '/fund/config/list',
    method: 'get',
    params: query
  })
}

// 查询费用配置详细
export function getConfig(id) {
  return request({
    url: '/fund/config/' + id,
    method: 'get'
  })
}

// 新增费用配置
export function addConfig(data) {
  return request({
    url: '/fund/config',
    method: 'post',
    data: data
  })
}

// 修改费用配置
export function updateConfig(data) {
  return request({
    url: '/fund/config',
    method: 'put',
    data: data
  })
}

// 删除费用配置
export function delConfig(id) {
  return request({
    url: '/fund/config/' + id,
    method: 'delete'
  })
}
