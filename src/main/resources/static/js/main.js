import Vue from 'vue';
import Vuetify from 'vuetify'
import 'api/resource'
import App from 'pages/App.vue';
import {connect} from './util/ws';
import 'vuetify/dist/vuetify.min.css';


if (frontendData.profile) {
  connect();
}

Vue.use(Vuetify, { iconfont: 'mdiSvg' });

const opts = {};

new Vue({
  el: '#app',
  vuetify: new Vuetify(opts),
  render: a => a(App),
});