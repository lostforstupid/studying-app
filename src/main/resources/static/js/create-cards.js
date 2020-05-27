Vue.component('v-select', VueSelect.VueSelect);

Vue.component('new-card', {
    props: ['card'],
    template: '#new-card'
});

new Vue({
    el: '#app',
    data: {
        groups: [],
        cards: [],
        selected: null,
    },
    created: function () {
        axios.get(GROUPS_URL + ALL).then(response => {
            let groups = response.data;
            groups.forEach(group => this.groups.push(group));
            this.selected = this.groups[0];
        });
    },
    methods: {
        addNewCard() {
            this.cards.push({id: this.cards.length, group: null, front: '', back: ''});
        },
        saveCards() {
            this.cards.forEach(card => {
                card.group = this.selected;
                console.log(card.front + " " + card.back + " " + card.group.name);
            });
        }
    }
});