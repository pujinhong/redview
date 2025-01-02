import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  base: '/manager/',
  build: {
    outDir: 'dist',
  },
  proxy: { 
    '/dev-api': {
      target: 'https://f2fjhyjk-5k52p02l-wh5ivav6pp9c.vcc8.mcprev.cn:20000',
      changeOrigin: true,
      rewrite: (p) => p.replace(/^\/dev-api/, '')
    }
  }
})
