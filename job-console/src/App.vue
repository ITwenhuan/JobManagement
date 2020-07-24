<template>
    <div id="app">
        <el-container>
            <el-header class="header">
                <Header></Header>
            </el-header>
            <el-container>
                <el-aside width="200px"><NavMenu></NavMenu></el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import Header from './components/Header'
    import NavMenu from './components/NavMenu'
    import Router from '@/router/index'

    export default {
        name: 'App',
        data () {
            return {

            }
        },
        components: {
            Header,
            NavMenu
        },
        methods: {
            navTo: e => {
                console.log('do nav To')
                Router.push('index')
            }
        }
    }
</script>

<style>
    .header {
        background-color: #545c64;
        color: #ffffff;
    }
</style>
