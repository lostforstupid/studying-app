Vue.component('card-view', {
    props: ['card'],
    template: '#card-view'
});

Vue.component('card-list', {
    props: ['cards'],
    template: '#card-list'
});

new Vue({
    el: '#app',
    template: '<card-list :cards="cards"/>',
    data: {
        cards: [],
    },
    created: function () {
        axios.get(CARDS_URL).then(response => {
            let cardList = response.data;
            cardList.forEach(card => this.cards.push(card))
        });
    }
});