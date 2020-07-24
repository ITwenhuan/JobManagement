/**
  * 通用调用接口包装方法
  */
import axiosCreate from './http'
let myAjax = axiosCreate.create({})
export { myAjax }

export const ajaxPostJsonData = (url, bodyParams) => {
  return myAjax({ url: url, method: 'post', data: bodyParams, headers: { 'Content-Type': 'application/json' }})
};
