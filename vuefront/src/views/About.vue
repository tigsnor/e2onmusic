<template>
    <div>
        <table class="table table-striped">
            <thead>
            <tr class="table-primary">
                <th style="width: 10%">번호</th>
                <th style="width: 30%">제목</th>
                <th style="width: 20%">아티스트</th>
                <th style="width: 20%">발매일</th>
                <th style="width: 10%">재생</th>
                <th style="width: 10%">다운로드</th>
            </tr>
            </thead>
            <tbody>
            <tr v-if="boardList.content == null">
                <td colspan="6">검색결과가 없습니다.</td>
            </tr>
            <tr v-else v-for="(it, index) in boardList.content" :key="it.idx">
                <td>{{index+1}}</td>

                <td v-on:click="clickModal(it)" v-b-modal="'modal26'" style="cursor:pointer;">{{it.musicName}}</td>

                <td>{{it.singer}}</td>
                <td>{{it.date}}</td>
                <td><button v-on:click="playMusic('api/play/'+it.musicFile)">
                    <b-icon icon="play"/>
                </button></td>
                <td><button v-on:click="downMusic('api/download?filename='+it.musicFile+'&fileoriname='+it.musicOriFile)">
                    <b-icon icon="download"/>
                </button></td>
            </tr>
            </tbody>
        </table>

        <div class="overflow-auto">
            <b-pagination-nav align="center" @page-click="pageClick" :number-of-pages="boardList.totalPages" use-router/>
        </div>

        <!--  상세페이지 모달  -->
        <b-modal ref="modal26">
            <div v-if="item != null">
                <img class="image-fluid" v-bind:src="'/api/picture/'+item.imgFile">
                제목{{item.musicName}}
                앨범명{{item.albumName}}
                가수{{item.singer}}
                발매일{{item.date}}
            </div>
        </b-modal>
    </div>
</template>

<script>
    // var params = new URLSearchParams([['page', 0]])
    var audio = new Audio;
    import axios from "@/axios.js"

    export default {
        name :'musicList',
        data() {
            return{
                selected: false,
                boardList: [],
                item: null,
                selectPage: 0
            }
        },
        mounted() {
            axios.get("/api/board", {params: {page:this.selectPage}})
                .then(res=>{
                    this.boardList = res.data;
                    console.log(res.data);
                });
        },
        methods:{
            playMusic(url) {
                audio.autoplay = true
                audio.src = url;
                audio.load();
                audio.play();
            },
            downMusic(url) {
                location.href = url;
            },
            pageClick(){
                this.selectPage = page-1;
                // this.$router.go();
            },
            clickModal(it){
                this.item = it;
                this.$refs['modal26'].show();
            }

        }

    }
</script>