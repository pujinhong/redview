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
      target: 'https://localhost:20000',
      changeOrigin: true,
      rewrite: (p) => p.replace(/^\/dev-api/, '')
    }
  }
})
 