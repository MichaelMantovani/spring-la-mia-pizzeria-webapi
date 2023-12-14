<script setup>
import { ref } from 'vue';
const props = defineProps({
  pizze: {
    type: Array,
    required: true,
  },
});
</script>

<template>
  <div class="container mt-5">
    <div class="d-flex gap-3">
      <form class="d-flex mb-4" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" name="searchValue" />
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
      <div>
        <RouterLink class="btn btn-primary" :to="{ name: 'pizza-form' }" :pizze="pizze"
          >Crea una nuova pizza</RouterLink
        >
      </div>
    </div>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Nome</th>
          <th scope="col">Ingredienti</th>
          <th scope="col">Prezzo</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="pizza in pizze" :key="pizza.id">
          <th>{{ pizza.name }}</th>
          <td>
            <ul class="d-flex list-unstyled gap-1">
              <li v-for="ingrediente in pizza.ingredienti">{{ ingrediente.name }},</li>
            </ul>
          </td>
          <td>{{ pizza.prezzo }} €</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
