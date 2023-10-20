import request from '@/utils/request'

// 查询理财产品列表
export function listFinancial(query) {
  return request({
    url: '/market/financial/list',
    method: 'get',
    params: query
  })
}

// 查询理财产品详细
export function getFinancial(id) {
  return request({
    url: '/market/financial/' + id,
    method: 'get'
  })
}

// 新增理财产品
export function addFinancial(data) {
  return request({
    url: '/market/financial',
    method: 'post',
    data: data
  })
}

// 修改理财产品
export function updateFinancial(data) {
  return request({
    url: '/market/financial',
    method: 'put',
    data: data
  })
}

// 删除理财产品
export function delFinancial(id) {
  return request({
    url: '/market/financial/' + id,
    method: 'delete'
  })
}
