<template>
  <v-footer padless class="footer" style="background-color: #CFD8DC">
    <v-col
        class="text-center"
        cols="12"
    >
      {{ new Date().getFullYear() }} — <strong>Book Village</strong>
    </v-col>
  </v-footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
.footer{
  background-color: #CFD8DC;
  position: fixed;
  bottom: 0;
  width: 100%;
  display: flex;
  text-align: center;
}
</style>