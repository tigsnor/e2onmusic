<template>
  <div id="app">
    <Top />
    <div>
    </div>
    <div id="content" class="content">
      <router-view></router-view>
    </div>
    <Down :check-result="checkResult" />
  </div>
</template>

<script>
import Top from './components/layout/Top.vue'
import Down from './components/layout/Down.vue'
import axios from "@/axios.js"


export default {
  name: 'App',
  components: {
    Top,
    Down
  },
  data() {
    return{
      checkResult: null,
    }
  },
  mounted() {
    {
      axios.get("http://localhost:9090/security/check")
        .then(res => {
          if(res != null){
          this.checkResult = res.data;
          }
          console.log(res.data);
        });
    }
  }
}
</script>
<style>
</style>
