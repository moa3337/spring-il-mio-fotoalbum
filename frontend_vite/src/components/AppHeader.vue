<script>
//import { router } from '../router';

export default {
  name: "AppHeader",
  props: { title: String, },
  //components: { router }
};
</script>

<template>
  <!-- As a link -->
  <nav class="navbar navbar-expand-lg bg-dark bg-gradient">
    <div class="container">
      <span class="navbar-brand text-light">{{ title }}</span>
      <div class="text-light">
        <router-link :to="{ name: 'home' }" class="nav-link">
          Homepage
        </router-link>
        <router-link :to="{ name: 'contacts' }" class="nav-link">
          Contacts
        </router-link>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
