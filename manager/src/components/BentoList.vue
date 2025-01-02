<script setup lang="ts">
import { ref, provide, computed } from "vue"
const queryParams: any = ref({})

const selection = ref([])

const tableList = ref([])

const loading = ref(false)

const total = computed(()=>tableList.value.length)

const single = computed(()=>{
    return selection.value.length === 1
})

const handleQuery = () => {
  console.log(queryParams.value.tableName)
}

const resetQuery = () => {
  queryParams.value = {}
}

const openCreateBento = () => {
}

const openEditBento = () => {
}
const handleDelete = (row:any) => {

}
const handleSelectionChange = ()=>{

}
const handlePreview = (row:any) => {
}

const handleEdit = (row:any) => {
}



</script>

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true">
      <el-form-item label="视图名称" prop="tableName">
        <el-input v-model="queryParams.tableName" placeholder="请输入视图名称" clearable style="width: 200px" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery">搜索</el-button>
        <el-button @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain @click="openCreateBento">创建</el-button>
      </el-col>
      <el-col :span="1.5"> 
        <el-button type="success" plain :disabled="single" @click="openEditBento">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain :disabled="single" @click="handleDelete(selection)">删除</el-button>
      </el-col>
    </el-row>
    <el-table v-loading="loading" :data="tableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" align="center" width="55"></el-table-column>
      <el-table-column label="序号" type="index" width="100" align="center">
        <template #default="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="视图名称" align="center" prop="tableName" :show-overflow-tooltip="true" />
      <el-table-column label="类型" align="center" prop="createTime" width="160" />
      <el-table-column  label="视图描述" align="center" prop="tableComment" :show-overflow-tooltip="true" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="160" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="160" />
      <el-table-column label="操作" align="center" width="330" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-tooltip content="预览" placement="top">
            <el-button link type="primary" icon="View" @click="handlePreview(scope.row)"></el-button>
          </el-tooltip>
          <el-tooltip content="编辑" placement="top">
            <el-button link type="primary" icon="Edit" @click="handleEdit(scope.row)"></el-button>
          </el-tooltip>
          <el-tooltip content="删除" placement="top">
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"></el-button>
          </el-tooltip>  
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="handleQuery"
    />
  </div>
</template>

<style >
.app-container {
  height: 100%;
  width: 100%;
}
</style>