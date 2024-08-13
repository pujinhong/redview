<script setup lang="ts">
import SourceItem from './ds/SourceItem.vue'
import { ElDrawer, ElMessageBox } from 'element-plus'
import { ElNotification } from 'element-plus'
import {getCurrentInstance, ref, provide, computed } from "vue" 
import { getDatasources ,insertDatasource, updateDatasource, deleteDatasource, tryDatasource} from "../api" 
import { IDataSource} from '../types/IDataSource'

const loading = ref(false) 

// 查询
const queryParams: any = ref({}) 
const dataSourceList = ref([])  
const handleQuery = () => {
    console.log(queryParams.value)

    getDatasources({
        name: queryParams.value.dsName,
        db_type: queryParams.value.dsType
    }).then(res => {
        console.log(res)
        dataSourceList.value = res.data.data
    })
}
const resetQuery = () => {
    queryParams.value = {}
    handleQuery()
}


// 创建
const createCompVisable = ref(false) 
const createSourceOK = (row: IDataSource) => {
    insertDatasource(row).then(()=>{
        handleQuery()
        createCompVisable.value = false
    })
}
const createSourceClose = ()=>{
    createCompVisable.value = false
}
 

// 编辑
const  editCompVisable = ref(false)
const editSource = ref({})
const openEditSource = (source) => {
    console.log(source.id,source)
    editSource.value = source
    editCompVisable.value = true
}
const editSourceOK = (row : IDataSource)=>{
    updateDatasource(row).then(()=>{
        editCompVisable.value = false
    })
}
const editSourceClose = ()=>{
    editSource.value = {}
    editCompVisable.value = false
}


//删除
const selection = ref([]) 
const handleSelectionDelete = (ids:number[]) => {

    for (let index = 0; index < ids.length; index++) {
        deleteDatasource(ids[index])
    }
    handleQuery(); 
}
const handleSelectionChange = (rows: any) => {
    selection.value = rows.map((row: any) => row.id);
}
const handlePreview = (row: any) => {

    tryDatasource(row.id).then((d) => {

        if(d.success){
            ElNotification({
                title: '测试连接',
                message: '连接成功！',
                type: 'success',
            })
        }else{
            ElNotification({
                title: '测试连接',
                message: '连接异常，请修改参数。',
                type: 'error',
            })
        }
 
    })

}

// 初始化
handleQuery(); 

</script>

<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryRef" :inline="true">
            <el-form-item label="数据源名称" prop="tableName">
                <el-input v-model="queryParams.dsName" placeholder="请输入数据源名称" clearable style="width: 200px" />
            </el-form-item>
            <el-form-item label="类型" prop="tableName">
                <el-input v-model="queryParams.dsType" placeholder="请输入数据源类型" clearable style="width: 200px" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handleQuery">搜索</el-button>
                <el-button @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>
        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain @click="createCompVisable = true">创建</el-button>
            </el-col>
            <!-- <el-col :span="1.5">
                <el-button type="success" plain :disabled="selection.length === 1" @click="openEditSource">修改</el-button>
            </el-col> -->
            <el-col :span="1.5">
                <el-button type="danger" plain :disabled="selection.length === 1" @click="handleSelectionDelete(selection)">删除</el-button>
            </el-col>
        </el-row>
        <el-table v-loading="loading" :data="dataSourceList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" align="center" width="55"></el-table-column>
            <el-table-column label="序号" type="index" width="100" align="center">
                <template #default="scope">
                    <span>{{ scope.$index + 1 }}</span>
                </template>
            </el-table-column>
            <el-table-column label="名称" align="center" prop="name" :show-overflow-tooltip="true" />
            <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true" />
            <el-table-column label="JDBC URL" align="center" prop="dbUrl" :show-overflow-tooltip="true" />
            <el-table-column label="类型" align="center" prop="dbType" width="160" />
            <el-table-column label="创建时间" align="center" prop="createTime" width="160" />
            <el-table-column label="更新时间" align="center" prop="updateTime" width="160" />
            <el-table-column label="操作" align="center" width="330" class-name="small-padding fixed-width">
                <template #default="scope">
                    <el-button link type="primary" @click="handlePreview(scope.row)">测试连接</el-button>
                    <el-button link type="primary" @click="openEditSource(scope.row)">编辑</el-button>
                    <el-button link type="primary" @click="handleSelectionDelete([scope.row.id])">删除</el-button>
                </template>
            </el-table-column>
        </el-table> 
        <el-drawer v-model="createCompVisable" title="创建数据源" direction="rtl" size="50%" :close-on-click-modal="false" :destroy-on-close="true">
            <SourceItem ref="createRef" @ok="createSourceOK" @close="createSourceClose" > </SourceItem>
        </el-drawer>
        <el-drawer v-model="editCompVisable" title="编辑数据源" direction="rtl" size="50%" :close-on-click-modal="false" :destroy-on-close="true" >
            <SourceItem ref="editRef" @ok="editSourceOK" :source="editSource" @close="editSourceClose"> </SourceItem>
        </el-drawer>
        
    </div>
</template>

<style lang="less">
.app-container {
    height: 100%;
    width: 100%;
}
</style>