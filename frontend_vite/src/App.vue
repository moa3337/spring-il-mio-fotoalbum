<script>
import AppHeader from './components/AppHeader.vue';
import PhotoList from './components/PhotoList.vue';
import axios from 'axios';

export default {
  data() {
    return {
      title: 'Spring PhotoAlbum',
      photos: [],
    };
  },

  components: { AppHeader, PhotoList },

  methods: {
    fetchPhotos() {
      axios.get('http://localhost:8080/api/v1/photos')
        .then((response) => {
          this.photos = response.data;
        });
    },
  },
  created() {
    this.fetchPhotos();
  },
};
</script>

<template>
  <div>
    <AppHeader />
    <main class="container">
      <!-- Router component-->
      <PhotoList :photos="photos" :title="title" />
    </main>
  </div>
</template>

<style scoped></style>
