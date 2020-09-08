<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться через
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
                        <messages-list :messages="messages"/>
        </div>
    </div>
</template>

<script>
  import MessagesList from 'components/messages/MessageList.vue'

  export default {
    // Vue не любит когда в data сразу помещается объект. Для каждого инстанса будет один и тот же объект.
    //     Чтобы этого избежать, нужно либо использовать функцию 'data: function() {...' Либо так как
    // у нас используется babel, можно 'data()'
    components: {
      MessagesList
    },
    data() {
      return {
        messages: frontendData.messages,
        profile: frontendData.profile,
      };
    },
  };
</script>

<style>

</style>