<template>
    <div>
        <b-navbar type="dark" variant="info">
            <b-navbar-brand href="/board">NavBar</b-navbar-brand>
            <!-- Right aligned nav items -->
            <b-navbar-nav>
                <b-nav-form @submit="onSubmit">
                    <select style="width: 80px" class="form-select" v-model:name="form.musicselect">
                        <option id="musicname" value="musicname">제목</option>
                        <option id="artist" value="artist">가수</option>
                    </select>
                    <b-form-input class="" id="keyword" placeholder="Search" v-model:name="form.keyword"></b-form-input>
                    <b-button type="submit">Search</b-button>
                </b-nav-form>
            </b-navbar-nav>
        </b-navbar>
    </div>
</template>
<script>
export default {
    name: "top",
    data(){
        return {
            form:{
                musicselect: null,
                keyword: null

            }
        }
    },
    methods: {
        onSubmit(event){
            event.preventDefault()
            this.$emit("searchEvent")
            console.log(this.form)
        }
    }
}
</script>