Vue.component('v-select', VueSelect.VueSelect);

Vue.component('new-card', {
    props: ['card'],
    template: '#new-card',
    methods: {
        removeCard(cardId) {
            this.$parent.removeCard(cardId);
        }
    }
});

new Vue({
    el: '#app',
    data: {
        groups: [],
        cards: [],
        selected: null,
        userImgUrl: ''
    },
    created: function () {
        axios.get(GROUPS_URL + ALL).then(response => {
            let groups = response.data;
            groups.forEach(group => this.groups.push(group));
            this.selected = this.groups[0];
        });
        axios.get(USER_IMG_URL).then(response => {
            this.userImgUrl = response.data;
        });
    },
    methods: {
        addNewCard() {
            this.cards.push({id: this.cards.length + 1, group: null, front: '', back: ''});
        },
        saveCards() {
            this.cards.forEach(card => {
                card.groupId = this.selected.id;
                let formData = new FormData();
                formData.append('front', card.front);
                formData.append('back', card.back);
                formData.append('groupId', card.groupId);
                instance.post(CARDS_URL, formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
            });
        },
        removeCard(cardId) {
            this.cards.splice(cardId - 1, 1);
            let index = 1;
            this.cards.forEach(card => {
                card.id = index;
                index++;
            });
        }
    }
});