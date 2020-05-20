Vue.component('group-view', {
    props: ['group'],
    template: '#group-view'
});

Vue.component('group-list', {
    props: ['groups'],
    template: '#group-list'
});

new Vue({
    el: '#app',
    template: '<group-list :groups="groups"/>',
    data: {
        groups: [],
    },
    created: function () {
        axios.get(GROUPS_URL + ALL).then(response => {
            let groupList = response.data;
            groupList.forEach(group => this.groups.push(group))
        });
    }
});