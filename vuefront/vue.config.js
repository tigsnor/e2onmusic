const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave : false,
  outputDir: "../src/main/resources/static",//빌드 파일이 생성되는 디렉토리

  //proxy(서버 중계)설정
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:9090', //api가붙으면 9090으로 연결
        pathRewrite: { '^/api': '' },
        changeOrigin: true,
      }
    }
  }
})
