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
import messagesApi from 'api/messages';

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
      const message = {id: this.id, text: this.text};

      if (this.id) {
        messagesApi.update(message).then(result =>
            result.json().then(data => {
              const index = this.messages.findIndex(item => item.id === data.id);

              if (index > -1) {
                this.messages.splice(index, 1, data);
              } else {
                this.messages.push(data);
              }
            }));
      } else {
        messagesApi.add(message).then(result =>
            result.json().then(data => {
              this.messages.push(data);
            }),
        );
      }

      this.text = '';
      this.id = '';
    },
  },
};
</script>

<style>

</style>