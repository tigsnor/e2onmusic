<template>
    <div align="center">
        <div v-if="loginResult != null || checkResult != null">
            <div v-if="loginResult !=null">
                <div v-if="loginResult.result = 'success'">
                    {{loginResult.data.username}}님 환영합니다.
                    <b-button v-on:click="logout()">로그아웃</b-button>
                </div>
            </div>
            <div v-else-if="checkResult !=null">
                <div v-if="checkResult.result = 'true'">
                    {{checkResult.member.username}}님 환영합니다.
                    <b-button v-on:click="logout()">로그아웃</b-button>
                </div>
            </div>
        </div>
        <div v-else>
            <b-button @click="login" v-b-modal.login-modal>로그인</b-button>
            <b-button  @click="signup" v-b-modal.signup-modal>회원가입</b-button>
        </div>

<!-- 모달 -->
        <b-modal
            id="login-modal" title="로그인" size="sm"
            @ok="login"
        >
            <p>아이디:</p>
            <p><input type="text" v-model="username"></p>
            <p>비밀번호:</p>
            <p><input type="password" v-model="password"></p>
        </b-modal>

        <b-modal
            id="signup-modal" title="회원가입" size="sm"
            @ok="signup"
            >
            <p>아이디:</p>
            <p><input type="text" v-model="username"></p>
            <p>비밀번호:</p>
            <p><input type="password" v-model="password"></p>
        </b-modal>
    </div>
</template>
<script>
import axios from "@/axios.js"
export default {
    name: 'Down',
    props: ['checkResult'],
    data(){
        return{
            username: undefined,
            password: undefined,
            role: 'USER',
            loginResult: null
        }
    },
    methods:{
        login(){
            const formData = new FormData();
            formData.append('username', this.username)
            formData.append('password', this.password)
            {
                axios.post("http://localhost:9090/login1", formData)
                .then(res => {
                    this.loginResult = res.data;
                    console.log(res);
                })
                .catch(function (error) {
                    console.log(error);
                })
            }
        },
        signup(){
            const formData = new FormData();
            formData.append('username', this.username)
            formData.append('password', this.password)
            formData.append('role', this.role)
            {
                axios.post("http://localhost:9090/user", formData)
                .then(res => {
                    console.log(res);
                })
                .catch(function (error) {
                    console.log(error);
                })
            }
        },
        logout(){
            axios.get("http://localhost:9090/logout");
            this.$router.go();
        }
    }

}
</script>