<template>
  <v-card>

    <v-flex v-if="type === 'href'" xs12 sm6 offset-sm3>
      <v-img v-if="message.linkCover" :src="message.linkCover" aspect-ratio="2.75"></v-img>
      <v-card-title>
        <h3>
          <a :href="message.link">{{ message.linkTitle || message.link }}</a>
        </h3>
        <div v-if="message.linkDescription">{{ message.linkDescription }}</div>
      </v-card-title>
    </v-flex>

    <v-flex v-if="type === 'image'" xs12 sm6 offset-sm3>
      <a :href="message.link">
      <v-img v-if="message.linkCover" :src="message.linkCover" aspect-ratio="2.75"></v-img>
        {{message.link}}
      </a>
    </v-flex>

    <v-flex v-if="type === 'youtube'" xs12 sm6 offset-sm3>
      <youtube :videoId="videoId" player-width="640" player-height="375" :player-vars="{autoplay: 0}"></youtube>
    </v-flex>

  </v-card>
</template>

<script>
import { getIdFromURL } from 'vue-youtube-embed'

export default {
  name: 'Media',
  props: ['message'],
  data() {
    return {
      type: 'href',
    };
  },
  // Перед монтированием проверяем какой объект
  beforeMount() {
    console.log('Message link: ' + this.message.link)
    if (this.message.link.indexOf('youtu') > -1) {
      this.type = 'youtube'
    } else if (this.message.link.match(/\.(jpeg|jpg|gif|png)$/) !== null) {
      this.type = 'image'
    } else {
      this.type = 'href'
    }
  },
  computed: {
    videoId() {
      console.log('Message link computed: ' + this.message.link)
      let videoIdentifier = getIdFromURL(this.message.link)
      return videoIdentifier;
    }
  }
};
</script>

<style scoped>

</style>