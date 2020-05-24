Vue.component('new-card', {
    props: ['id', 'front', 'back'],
    template: '#new-card'
});

new Vue({
    el: '#app',
    data: {
        groups: [],
        cards: [],
        selected: null
    },
    created: function () {
        axios.get(GROUPS_URL + ALL).then(response => {
            let groups = response.data;
            groups.forEach(group => this.groups.push(group));
            this.selected = this.groups[0];
        });
    },
    methods: {
        options() {
            return this.groups;
        }
    }
});