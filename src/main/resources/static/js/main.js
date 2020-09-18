import Vue from 'vue';
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource'
import router from 'router/router';
import App from 'pages/App.vue';
import store from 'store/store'
import {connect} from './util/ws';
import 'vuetify/dist/vuetify.min.css';


if (frontendData.profile) {
  connect();
}

Vue.use(Vuetify, { iconfont: 'mdiSvg' });

const opts = {};

new Vue({
  el: '#app',
  data: {
    videoId: 'videoId',
  },
  store,
  router,
  vuetify: new Vuetify(opts),
  render: a => a(App),
  methods: {
    ready (event) {
      this.player = event.target
    },
    playing (event) {
      // The player is playing a video.
    },
    change () {
      // when you change the value, the player will also change.
      // If you would like to change `playerVars`, please change it before you change `videoId`.
      // If `playerVars.autoplay` is 1, `loadVideoById` will be called.
      // If `playerVars.autoplay` is 0, `cueVideoById` will be called.
      this.videoId = 'another video id'
    },
    stop () {
      this.player.stopVideo()
    },
    pause () {
      this.player.pauseVideo()
    }
  }
});