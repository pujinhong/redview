import axios from "axios"

const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: "dev-api",
    // 超时
    timeout: 10000
  })

  /** 基本请求 */
export const searchAction = (url: string, params?: object) => {
    return service.get(url, { params })
}

export const jsonAction = (url: string, params?: object) => {
    return service.post(url, params)
}

export const formAction = (url: string, params?: object) => {
    return service.post(url, params, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}
export const updateAction = (url: string, params?: object) => {
    return service.put(url, params)
}
export const deleteAction = (url: string, params?: object) => {
    return service.delete(url, params)
}


/**
 * 业务请求
 */

export const getBentos = ( params : object ) => {
    return searchAction('/bento/list', params)
}

export const delBento = ( id : number ) => {
    return deleteAction('/bento/del', { id })
}
export const getBento = ( id : number ) => {
    return searchAction('/bento/get', { id })
}

export const getDatasources = ( params : object ) => {
    return searchAction('/ds/list', params)
}
export const insertDatasource = (data : object) =>{
    console.log(data)
    return jsonAction('/ds', data)
}
export const updateDatasource = (data : object) =>{
    return updateAction('/ds', data)
}
export const deleteDatasource = (id: number) =>{  
    return deleteAction('/ds/'+ id )
}