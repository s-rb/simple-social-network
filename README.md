## Simple social network

### Install Yarn (Ubuntu):
```
curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list

sudo apt update && sudo apt install yarn
```
Then, check:
`yarn --version`

At the root - init:
`yarn init`

Some dependencies:
`yarn add vue vue-resource`

More dependencies:
` yarn add -D webpack webpack-cli webpack-dev-server babel-loader @babel/core @babel/preset-env vue-loader vue-template-compiler`
   
StompJs + client install:
`yarn add sockjs-client @stomp/stompjs`

For Vuetify deps:
`yarn add -D vue-style-loader css-loader`
`yarn add vuetify`

`yarn add vuex`
`yarn add @babel/polyfill`