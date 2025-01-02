import axios from "axios"

const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: "dev-api",
    // 超时
    timeout: 10000
})

/** 基本请求 */
export const searchAction = (url, params) => {
    return service.get(url, { params })
}

export const jsonAction = (url, params) => {
    return service.post(url, params)
}

export const formAction = (url, params) => {
    return service.post(url, params, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}
export const updateAction = (url, params) => {
    return service.put(url, params)
}
export const deleteAction = (url, params) => {
    return service.delete(url, params)
}


/**
 * 业务请求
 */

export const getBentos = (params) => {
    return searchAction('/bento/list', params)
}

export const delBento = (id) => {
    return deleteAction('/bento/del', { id })
}
export const getBento = (id) => {
    return searchAction('/bento/get', { id })
}

// 数据源
export const getDatasources = (params) => {
    return searchAction('/ds/list', params)
}
export const insertDatasource = (data) => {
    console.log(data)
    return jsonAction('/ds', data)
}
export const updateDatasource = (data) => {
    return updateAction('/ds', data)
}
export const deleteDatasource = (id) => {
    return deleteAction('/ds/' + id)
}
export const tryDatasource = (id) => {
    return jsonAction('/ds/try', { id })
}