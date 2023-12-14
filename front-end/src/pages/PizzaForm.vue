<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const form = ref({
  name: null,
  prezzo: null,
  descrizione: null,
  foto: null,
});

const router = useRouter();
const emits = defineEmits();

const storePizza = async () => {
  const store = await axios.post('http://localhost:8080/api/pizza/create', form.value);
  console.log('inviato');
  router.push({ name: 'home' });
  emits('storeNewPizza');
};
</script>

<template>
  <div class="container mt-5">
    <form id="pizzaForm" method="post" @submit.prevent="storePizza">
      <div class="row gap-3 mb-3">
        <div class="col">
          <label for="name" class="form-label">Nome della pizza</label>
          <div class="input-group">
            <input type="text" class="form-control" id="name" placeholder="Es. Margherita" v-model="form.name" />
          </div>
        </div>
        <div class="col">
          <label for="prezzo" class="form-label">Prezzo</label>
          <div class="input-group">
            <div class="input-group-text">€</div>
            <input type="number" step="0.50" class="form-control" id="prezzo" v-model="form.prezzo" />
          </div>
        </div>
      </div>
      <div class="mb-3">
        <label for="descrizione" class="form-label">Descrizione</label>
        <textarea
          class="form-control"
          id="descrizione"
          rows="3"
          placeholder="Es. Pomodoro, Mozzarella..."
          v-model="form.descrizione"
        ></textarea>
      </div>
      <div class="mb-3">
        <label for="foto" class="form-label">Link alla foto</label>
        <input
          type="text"
          class="form-control"
          id="foto"
          placeholder="Es. https://images.prismic.io/eataly-us..."
          v-model="form.foto"
        />
      </div>
      <button type="submit" class="btn btn-primary btn-sm me-1">Crea pizza</button>
    </form>
  </div>
</template>
