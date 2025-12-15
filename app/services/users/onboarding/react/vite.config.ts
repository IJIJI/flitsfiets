import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
    plugins: [react()],
    server: {
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8010',
                changeOrigin: true,
                secure: false,
                // rewrite: (path) => path.replace(/^\/tapi/, ''),
            },
            // Proxying websockets or socket.io:
            // ws://localhost:5173/socket.io
            //   -> ws://localhost:5174/socket.io
            // Exercise caution using `rewriteWsOrigin` as it can leave the
            // proxying open to CSRF attacks.
            // '/socket.io': {
            //   target: 'ws://localhost:5174',
            //   ws: true,
            //   rewriteWsOrigin: true,
            // },
        },
    },
})
