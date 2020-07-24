/**
 * 通用请求拦截器
 * */
import axios from 'axios'
import { Notification } from 'element-ui'

function create (options) {
  options.validateStatus = function (status) {return true} // do not reject any response
  let instance = axios.create(options);
  instance.interceptors.response.use(function (response) {
    if (!response || !response.status) {
      return Notification.error({title: '提示', message: '网络请求失败，请稍后再试!'})
    }
    if (response.status != 200) {
      return Promise.reject({ status: response.status, response: { data: response.data.err  || response.data.error || response.data.msg || response.data.message|| '请求失败！' } })
    }
    return response
  }, function (error) {
    return Promise.reject({ response: { data: '网络请求失败，请稍后再试' } })
  })
  return instance
}
export default { create }
