import './assets/main.css' 
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createApp } from 'vue'
import App from './App.vue'

const app = createApp(App)

app.use(ElementPlus)
app.mount('#app')
