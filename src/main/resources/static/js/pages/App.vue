<template>
  <v-app>
    <v-app-bar app
               color="blue" dark>
      <v-toolbar-title>
        Social Network
      </v-toolbar-title>
      <v-btn text v-if="profile"
             :disabled="$route.path === '/'"
             @click="showMessages">
        Messages
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn text v-if="profile"
             :disabled="$route.path === '/profile'"
             @click="showProfile">
        {{ profile.name }}
      </v-btn>
      <v-btn v-if="profile" icon href="/logout">
        <v-icon dark>mdi-logout-variant</v-icon>
      </v-btn>
    </v-app-bar>
    <v-main>

      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
import {mapMutations, mapState} from 'vuex';
import {addHandler} from 'util/ws';

export default {
  // Vue не любит когда в data сразу помещается объект. Для каждого инстанса будет один и тот же объект.
  //     Чтобы этого избежать, нужно либо использовать функцию 'data: function() {...' Либо так как
  // у нас используется babel, можно 'data()'
  computed: mapState(['profile']),
  methods: {
    ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
    showMessages() {
      this.$router.push('/');
    },
    showProfile() {
      this.$router.push('/profile');
    },
  },
  created() {
    addHandler(data => {
          if (data.objectType === 'MESSAGE') {
            switch (data.eventType) {
              case 'CREATE':
                this.addMessageMutation(data.body);
                break;
              case 'UPDATE':
                this.updateMessageMutation(data.body);
                break;
              case 'REMOVE':
                this.removeMessageMutation(data.body);
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
  beforeMount() {
    if (!this.profile) {
      this.$router.replace('/auth');
    }
  },
};
</script>

<style>
/*.main-app {*/
/*  color: maroon;*/
/*}*/
</style>