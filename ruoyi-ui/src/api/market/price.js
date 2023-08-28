import request from '@/utils/request'

// 查询大盘价格数据源列表
export function listPrice(query) {
  return request({
    url: '/market/price/list',
    method: 'get',
    params: query
  })
}

// 查询大盘价格数据源详细
export function getPrice(id) {
  return request({
    url: '/market/price/' + id,
    method: 'get'
  })
}

// 新增大盘价格数据源
export function addPrice(data) {
  return request({
    url: '/market/price',
    method: 'post',
    data: data
  })
}

// 修改大盘价格数据源
export function updatePrice(data) {
  return request({
    url: '/market/price',
    method: 'put',
    data: data
  })
}

// 删除大盘价格数据源
export function delPrice(id) {
  return request({
    url: '/market/price/' + id,
    method: 'delete'
  })
}
