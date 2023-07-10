<script>
export default {
    name: 'PhotoCard',

    props: {
        photo: Object,
    }
}
</script>

<template>
    <div>
        <div class="card p-0" style="width: 18rem;">
            <img :src="photo.img" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">{{ photo.title }}</h5>
                <router-link :to="{
                    name: 'photo-detail'
                }
                    " class="btn btn-primary btn-sm">
                    Dettaglio
                </router-link>
                <a href="" class="btn btn-outline-info">Dettaglio</a>
            </div>
        </div>
    </div>
</template>

<style>
.card-img-top {
    object-fit: cover;
    height: 200px;
}
</style>