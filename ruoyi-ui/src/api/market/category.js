import request from '@/utils/request'

// 查询产品类别列表
export function listCategory(query) {
  return request({
    url: '/market/category/list',
    method: 'get',
    params: query
  })
}

// 查询产品类别详细
export function getCategory(id) {
  return request({
    url: '/market/category/' + id,
    method: 'get'
  })
}

// 新增产品类别
export function addCategory(data) {
  return request({
    url: '/market/category',
    method: 'post',
    data: data
  })
}

// 修改产品类别
export function updateCategory(data) {
  return request({
    url: '/market/category',
    method: 'put',
    data: data
  })
}

// 删除产品类别
export function delCategory(id) {
  return request({
    url: '/market/category/' + id,
    method: 'delete'
  })
}
