<template>
  <v-layout row class="ma-2" align-start justify-space-around>
    <v-text-field
        justify="center"
        label="New message"
        placeholder="Write something"
        v-model="text"
        @keyup.enter="save"
    />
    <v-btn @click="save">Save</v-btn>
  </v-layout>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  props: ['messageAttr'],
  data() {
    return {
      text: '',
      id: '',
    };
  },
  // При изменении messageAttr (наблюдаемого), срабатывает watch, который принимает новое и старое значения
  watch: {
    messageAttr(newVal, oldVal) {
      this.text = newVal.text;
      this.id = newVal.id;
    },
  },
  methods: {
    ...mapActions(['updateMessageAction', 'addMessageAction']),
    save() {
      const message = {id: this.id, text: this.text};

      if (this.id) {
        this.updateMessageAction(message)
      } else {
        this.addMessageAction(message)
      }

      this.text = '';
      this.id = '';
    },
  },
};
</script>

<style>

</style>