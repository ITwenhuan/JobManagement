<template>
    <div>
        <el-card>
            <span style="color: #0074D9">WelCome to Job Console!</span>
        </el-card>
    </div>
</template>
<script>
    export default {
        name: 'Index',
        data() {
            return {
                active: 0
            };
        },
        methods: {
            next() {
                if (this.active++ > 2) this.active = 0;
            }
        }
    }
</script>
<style scoped>
</style>
