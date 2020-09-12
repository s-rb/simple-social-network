<template>
  <v-app>
    <v-app-bar app
               color="blue" dark>
      <v-toolbar-title>
        Social Network
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <span v-if="profile">
        {{ profile.name }}
      </span>
      <v-btn v-if="profile" icon href="/logout">
        <v-icon dark>mdi-logout-variant</v-icon>
      </v-btn>
    </v-app-bar>
    <v-main>
      <v-container>
        <div v-if="!profile">Необходимо авторизоваться через
          <a href="/login">Google</a>
        </div>
        <div v-if="profile">
          <messages-list :messages="messages"/>
        </div>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import MessagesList from 'components/messages/MessageList.vue';
import {addHandler} from 'util/ws';
import {getIndex} from 'util/collections';
import {mdiExitToApp} from '@mdi/js';

export default {
  // Vue не любит когда в data сразу помещается объект. Для каждого инстанса будет один и тот же объект.
  //     Чтобы этого избежать, нужно либо использовать функцию 'data: function() {...' Либо так как
  // у нас используется babel, можно 'data()'
  components: {
    MessagesList,
  },
  data() {
    return {
      messages: frontendData.messages,
      profile: frontendData.profile,
      logout: mdiExitToApp,
    };
  },
  created() {
    addHandler(data => {
      let index = getIndex(this.messages, data.id);
      if (index > -1) {
        this.messages.splice(index, 1, data);
      } else {
        this.messages.push(data);
      }
    });
  },
};
</script>

<style>
/*.main-app {*/
/*  color: maroon;*/
/*}*/
</style>