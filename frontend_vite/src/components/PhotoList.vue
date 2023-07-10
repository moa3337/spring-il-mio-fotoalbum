<script>
import AppPagination from './AppPagination.vue';
import PhotoCard from './PhotoCard.vue';
import axios from 'axios';

export default {
    data() {
        return {
            photos: {
                data: [],
                pages: [],
            },
        };
    },

    props: {
        photos: Array,
        title: String,
    },

    components: { PhotoCard, AppPagination },

    emits: ['changePage'],

    methods: {
        fetchPhotos(endpoint = null) {
            if (!endpoint) endpoint = 'http://localhost:8080/api/v1/photos'
            axios.get(endpoint)
                .then((response) => {
                    this.photos.data = response.data.data;
                    this.photos.pages = response.data.links;
                })
                .catch((err) => {
                    this.error = err.message;
                });
        },
    },

    created() {
        this.fetchPhotos();
    },
}
</script>

<template>
    <section class="container">
        <h1 class="text-warning">Lista delle foto</h1>
        <div class="row">
            <PhotoCard class="col-3" v-for="photo in photos" :key="photo.id" :photo="photo" />
            <AppPagination :pages="photos.pages" @changePage="fetchPhotos" />
        </div>
    </section>
</template>

<style></style>