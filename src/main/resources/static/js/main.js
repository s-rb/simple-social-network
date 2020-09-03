// Фигурные скобки в пути говорят что эта часть необязательная
var messageApi = Vue.resource('/message{/id}');


Vue.component('message-row', {
    props: ['message'],
    template: '<div><i>({{ message.id }})</i> {{ message.text }}</div>'
});

Vue.component('messages-list', {
    props: ['messages'],
    template: '<div><message-row v-for="message in messages" :key="message.id" :message="message"/></div>',
    created: function () {
        // Отправлен запрос на сервер
        messageApi.get()
            // После ответа поток выполнения возвращается в метод then
            .then(
                result =>
                    result.json().then(data =>
                        data.forEach(message => this.messages.push(message))
                    )
            )
    }
});

// Через :message - передаетса message параметром в компонент, который принимает в props message
var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
});