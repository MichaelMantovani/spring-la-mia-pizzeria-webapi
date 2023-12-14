<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';

const pizze = ref(null);

const getPizze = async () => {
  const data = await axios.get('http://localhost:8080/api/pizza');

  pizze.value = data.data;
};

onMounted(getPizze);
</script>

<template>
  <div>
    <RouterView :pizze="pizze"></RouterView>
  </div>
</template>
