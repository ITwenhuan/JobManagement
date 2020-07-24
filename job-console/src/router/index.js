import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/pages/Index'
import List from '@/pages/List'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Default',
            component: Index
        },
        {
            path: '/index',
            name: 'Index',
            component: Index
        },
        {
            path: '/list',
            name: 'List',
            component: List
        }
    ]
})
