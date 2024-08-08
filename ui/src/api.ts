import axios from "axios"

const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: process.env.AXIOS_BASE_URL,
    // 超时
    timeout: 10000
  })

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
    return service.delete(url, { params })
}


export const getBentos = ( params : object ) => {
    return searchAction('/bento/list', params)
}

export const delBento = ( id : number ) => {
    return deleteAction('/bento/del', { id })
}
export const getBento = ( id : number ) => {
    return searchAction('/bento/get', { id })
}