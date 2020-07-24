<template>
    <el-row class="tac">
        <el-col :span="24">
            <el-menu
                default-active="/index"
                @open="handleOpen"
                @close="handleClose"
                background-color="#545c64"
                text-color="#fff"
                router
                active-text-color="#ffd04b">
                <el-menu-item index="/index">
                    <template slot="title">
                        <i class="el-icon-s-home"></i>
                        <span>首页</span>
                    </template>
                </el-menu-item>
                <el-menu-item index="/list">
                    <i class="el-icon-menu"></i>
                    <span slot="title">任务列表</span>
                </el-menu-item>
            </el-menu>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>
<style>
</style>
