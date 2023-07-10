<script>
import PhotoList from '../components/PhotoList';
//import AppHeader from './components/AppHeader.vue';

export default {
    name: 'HomePage',
    components: {},
};
</script>

<template>
    <photoList @changePage="fetchPhotos" title="My Photos" />
</template>

<style lang="scss" scoped></style>