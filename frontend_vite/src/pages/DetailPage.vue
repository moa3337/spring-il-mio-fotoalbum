<script>
import PhotoCard from '../components/PhotoCard.vue';
import axios from 'axios';

export default {
    name: 'DetailPage',

    data() {
        return {
            photo: null,
        };
    },

    components: { PhotoCard },

    created() {
        axios
            .get(`http://localhost:8080/api/v1/photos/${this.$route.params}`)
            .then((response) => {
                // Se la chiamata va a buon fine:
                this.photo = response.data;
            })
            .catch((err) => {
                // Se la chiamata non va a buon fine:
                this.$router.push({ name: 'not-found' });
            })
            .finally(() => {
                // Comunque vada:
            });
    },
};
</script>

<template>
    <h2 class="my-3">Dettaglio della foto {{ photo?.title }}</h2>
    <PhotoCard v-if="photo" :photo="photo" />
</template>

<style lang="scss" scoped></style>