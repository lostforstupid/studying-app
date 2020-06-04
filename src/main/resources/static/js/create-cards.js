Vue.component('v-select', VueSelect.VueSelect);

Vue.component('new-card', {
    props: ['card'],
    template: '#new-card',
    methods: {
        removeCard(index, id) {
            this.$parent.removeCard(index, id);
        }
    }
});

new Vue({
    el: '#app',
    data: {
        groups: [],
        chooseGroups: true,
        cards: [],
        idsOfCardsToDelete: [],
        selected: null,
        userName: '',
        userImgUrl: ''
    },
    created: function () {
        if (existingCards != null) {
            axios.get(CARDS_URL + "/" + groupId).then(response => {
                let cards = response.data;
                let index = 1;
                cards.forEach(card => {
                    card.index = index;
                    index++;
                    this.cards.push(card);
                });
            });
            this.chooseGroups = false;
            this.selected = {id: groupId};
        } else {
            axios.get(GROUPS_URL + ALL).then(response => {
                let groups = response.data;
                groups.forEach(group => this.groups.push(group));
                this.selected = this.groups[0];
            });
        }
        axios.get(USER_IMG_URL).then(response => {
            this.userImgUrl = response.data;
        });
        axios.get(USER_NAME_URL).then(response => {
            this.userName = response.data;
        });
    },
    methods: {
        addNewCard() {
            this.cards.push({index: this.cards.length + 1, group: null, front: '', back: ''});
        },
        saveCards() {
            this.cards.forEach(card => {
                let formData = new FormData();
                formData.append('front', card.front);
                formData.append('back', card.back);
                formData.append('groupId', this.selected.id);
                if (card.id != null) {
                    instance.put(CARDS_URL + "/" + card.id, formData, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    });
                } else {
                    instance.post(CARDS_URL, formData, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    });
                }
                this.deleteCards();
            });
            window.location.href = "/";
        },
        deleteCards() {
            this.idsOfCardsToDelete.forEach(id => {
                console.log(id);
                axios.delete(CARDS_URL + "/" + id);
            });
        },
        removeCard(index, id) {
            let deletedCard = this.cards.splice(index - 1, 1);
            let newIndex = 1;
            this.cards.forEach(card => {
                card.index = newIndex;
                newIndex++;
            });
            if (id != null) {
                this.idsOfCardsToDelete.push(id);
            }
        }
    }
});