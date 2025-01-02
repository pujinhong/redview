<script setup>
import BentoList from './components/BentoList.vue'
import SourceList from './components/SourceList.vue'
</script>

<template>
    <el-tabs tab-position="right">
    <el-tab-pane label="视图">
      <BentoList></BentoList>
    </el-tab-pane>
    <el-tab-pane label="数据源">
      <SourceList></SourceList>
    </el-tab-pane>
  </el-tabs>
</template>

<style scoped>

</style>
