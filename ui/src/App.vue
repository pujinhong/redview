<script setup lang="ts"> 
import Bento from './components/Bento.vue'
import Source from './components/Source.vue'
</script>

<template> 
    <el-tabs tab-position="right" class="demo-tabs">
    <el-tab-pane label="视图">
      <Bento></Bento>
    </el-tab-pane>
    <el-tab-pane label="数据源">
      <Source></Source>
    </el-tab-pane>
  </el-tabs>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
