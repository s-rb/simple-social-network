## Simple social network

![Java](https://img.shields.io/badge/-Java-05122A?style=flat&logo=Java&logoColor=FFA518) ![VueJS](https://img.shields.io/badge/-VueJS-05122A?style=flat&logo=vuedotjs) ![WebService](https://img.shields.io/badge/-WebService-05122A?style=flat) ![WebSockets](https://img.shields.io/badge/-WebSockets-05122A?style=flat) ![Oauth2](https://img.shields.io/badge/-Oauth2-05122A?style=flat&logo=oauth) ![Javascript](https://img.shields.io/badge/-Javascript-05122A?style=flat&logo=Javascript) ![Spring](https://img.shields.io/badge/-Spring-05122A?style=flat&logo=Spring&logoColor=71b23c) ![Springboot](https://img.shields.io/badge/-SpringBoot-05122A?style=flat&logo=Springboot&logoColor=71b23c) ![Gradle](https://img.shields.io/badge/-Gradle-05122A?style=flat&logo=gradle&logoColor=fffffb) ![Postgresql](https://img.shields.io/badge/-Postgresql-05122A?style=flat&logo=Postgresql&logoColor=fffffb) ![Lombok](https://img.shields.io/badge/-Lombok-05122A?style=flat&logo=lombok) ![JSoup](https://img.shields.io/badge/-JSoup-05122A?style=flat&logo=jsoup) ![Thymeleaf](https://img.shields.io/badge/-Thymeleaf-05122A?style=flat&logo=Thymeleaf) ![REST](https://img.shields.io/badge/-REST-05122A?style=flat) ![Html](https://img.shields.io/badge/-Html-05122A?style=flat&logo=html) ![Css](https://img.shields.io/badge/-Css-05122A?style=flat&logo=css)

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
`yarn add vue-router`
`yarn add vue-youtube-embed`