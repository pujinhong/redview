 
<script setup lang="ts">
import { reactive,Ref, ref, defineProps ,defineEmits,watch} from 'vue' 
import { IDataSource} from '../types/IDataSource'
 
 

const dbTypes = ref([
    { text: "MySQL", value: "mysql", driver: "com.mysql.cj.jdbc.Driver", template  : "jdbc:mysql://<host>:<port>/<instance>" },
    { text: "SQL Server", value: "sqlserver", driver: "com.microsoft.sqlserver.jdbc.SQLServerDriver", template:"jdbc:sqlserver://<host>:<port>;encrypt=true;databaseName={instance};integratedSecurity=true" },
    { text: "Oracle", value: "oracle" , driver: "oracle.jdbc.driver.OracleDriver", template:"jdbc:oracle:thin:@//<host>:<port>/<instance>"},
    { text: "PostgreSQL", value: "postgresql" , driver: "org.postgresql.Driver", template:"jdbc:postgresql://<host>:<port>/<instance>"},
    { text: "SQLite", value: "sqlite" , driver: "com.mysql.cj.jdbc.Driver", template:""},
    { text: "DB2", value: "db2" , driver: "com.mysql.cj.jdbc.Driver", template:""},
    { text: "MariaDB", value: "mysql" , driver: "com.mysql.cj.jdbc.Driver", template:""},
    { text: "其他", value: "other" },
])

const props = defineProps({
    source: {
        type: Object as ()=> IDataSource,
        required: false, // 是否必传
        default: () => ({
            dbType: ''
        })
    }
})

let formData = reactive<IDataSource>( props.source)

const reset = () => {
    if(props.source){
        formData = Object.assign({ }, props.source);
    }
}

watch(()=> formData.dbType, (newValue, oldValue) => {
    let itemDb = dbTypes.value.find(item => item.value === formData.dbType)
    formData.dbUrl = itemDb?(itemDb.template+''):''
})


const emit = defineEmits(['ok','close'])
const onSubmit = () => { 
    
    if(formData.dbType !== 'other'){ 
        let itemDb = dbTypes.value.find(item => item.value === formData.dbType)
        formData.dbDriver = itemDb?(itemDb.driver+''):''
    }
    emit('ok',formData) 
}
 

</script>

<template>
    <el-form label-position="left" label-width="auto" :model="formData" style="max-width: 600px">
        <el-form-item label="名称"><el-input v-model="formData.name" /> </el-form-item>
        <el-form-item label="备注"><el-input v-model="formData.remark" /></el-form-item>
        <el-form-item label="类型" style=""> 
            <el-select v-model="formData.dbType" placeholder="" clearable>
                <el-option v-for=" item   in dbTypes" :label="item.text" :value="item.value" />
            </el-select> 
        </el-form-item> 
        <el-row :gutter="10">
            <el-col :span="4" ></el-col>
            <el-col :span="20" >
                <el-form-item label="ClassName：" label-position="right" v-show="formData.dbType == 'other'"><el-input v-model="formData.dbDriver" /></el-form-item>
            </el-col>
        </el-row>
        <el-form-item label="JDBC URL"><el-input v-model="formData.dbUrl" /></el-form-item>
        <el-form-item label="用户名"><el-input v-model="formData.dbUsername" /></el-form-item>
        <el-form-item label="密码"><el-input v-model="formData.dbPassword" /></el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
            <el-button @click="emit('close',null)">取消</el-button>
        </el-form-item>
    </el-form>
</template>

<style lang="less" scoped></style>../types/IDataSource