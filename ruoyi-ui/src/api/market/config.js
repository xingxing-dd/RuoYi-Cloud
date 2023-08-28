import request from '@/utils/request'

// 查询产品配置列表
export function listConfig(query) {
  return request({
    url: '/market/config/list',
    method: 'get',
    params: query
  })
}

// 查询产品配置详细
export function getConfig(id) {
  return request({
    url: '/market/config/' + id,
    method: 'get'
  })
}

// 新增产品配置
export function addConfig(data) {
  return request({
    url: '/market/config',
    method: 'post',
    data: data
  })
}

// 修改产品配置
export function updateConfig(data) {
  return request({
    url: '/market/config',
    method: 'put',
    data: data
  })
}

// 删除产品配置
export function delConfig(id) {
  return request({
    url: '/market/config/' + id,
    method: 'delete'
  })
}
