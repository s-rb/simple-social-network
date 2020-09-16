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
  store,
  router,
  vuetify: new Vuetify(opts),
  render: a => a(App),
});