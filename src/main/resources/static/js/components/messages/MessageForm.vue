<template>
  <v-layout row class="ma-2" align-start justify-space-around>
    <v-text-field
        justify="center"
        label="New message"
        placeholder="Write something" v-model="text">
    </v-text-field>
    <v-btn @click="save">Save</v-btn>
  </v-layout>
</template>

<script>
import {sendMessage} from 'util/ws';

export default {
  props: ['messages', 'messageAttr'],
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
    save() {
      sendMessage({id: this.id, text: this.text});
      this.text = '';
      this.id = '';

      /*const message = {text: this.text};

      if (this.id) {
        this.$resource('/message{/id}').update({id: this.id}, message).then(result =>
            result.json().then(data => {
              const index = getIndex(this.messages, data.id);
              this.messages.splice(index, 1, data);
              this.text = '';
              this.id = '';
            }));
      } else {
        this.$resource('/message{/id}').save({}, message).then(result =>
            result.json().then(data => {
              this.messages.push(data);
              this.text = '';
            }),
        );
      }*/
    },
  },
};
</script>

<style>

</style>