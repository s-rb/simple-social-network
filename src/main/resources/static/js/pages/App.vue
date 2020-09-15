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
          <messages-list/>
        </div>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
import MessagesList from 'components/messages/MessageList.vue';
import {addHandler} from 'util/ws';

export default {
  // Vue не любит когда в data сразу помещается объект. Для каждого инстанса будет один и тот же объект.
  //     Чтобы этого избежать, нужно либо использовать функцию 'data: function() {...' Либо так как
  // у нас используется babel, можно 'data()'
  components: {
    MessagesList,
  },
  computed: mapState(['profile']),
  methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
  created() {
    addHandler(data => {
          if (data.objectType === 'MESSAGE') {
            switch (data.eventType) {
              case 'CREATE':
                this.addMessageMutation(data.body)
                break;
              case 'UPDATE':
                this.updateMessageMutation(data.body)
                break;
              case 'REMOVE':
                this.removeMessageMutation(data.body)
                break;
              default:
                console.error(`Looks like the event type is unknown "${data.eventType}"`);
                break;
            }
          } else {
            console.error(`Looks like the object type is unknown "${data.objectType}"`);
          }
        },
    );
  },
};
</script>

<style>
/*.main-app {*/
/*  color: maroon;*/
/*}*/
</style>