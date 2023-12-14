import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../pages/HomePage.vue';
import PizzaForm from '../pages/PizzaForm.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: HomePage,
      name: 'home',
    },
    {
      path: '/pizza/create',
      component: PizzaForm,
      name: 'pizza-form',
    },
  ],
});

export { router };
