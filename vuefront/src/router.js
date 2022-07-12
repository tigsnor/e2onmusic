import Vue from "vue";
import VueRouter from "vue-router";
import Home from "./views/Home";
import About from "./views/About";

Vue.use(VueRouter);

const router = new VueRouter({
    mode: "history",
    routes: [
        {path:"/board", component: Home},
        {path:"/board?:page", component: Home},
        {path:"/about", component: About},
    ]
})

// 생성한 VueRouter 인스턴스 익스포트
export default router