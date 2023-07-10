import { createRouter, createWebHistory } from "vue-router";

// Import pages
//import HomePage from '../pages/HomePage.vue';
//import ContactPage from '../pages/ContactPage.vue';
import DetailPage from '../pages/DetailPage.vue';
//import TypeProjectPage from '../pages/TypeProjectsPage.vue';
//import NotFoundPage from '../pages/NotFoundPage.vue';

// Creation routes
const router = createRouter({
    history: createWebHistory(),
    linkActiveClass: 'active',
    linkExtraActiveClass: '',

    routes: [
        {
            path: '/projects/:slug',
            name: 'project-detail',
            component: DetailPage,
        },
    ],
});

export { router };