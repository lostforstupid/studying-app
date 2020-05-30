Vue.component('group-view', {
    props: ['group'],
    template: '#group-view',
    methods: {
        getTestLink() {
            return TEST + "/" + this.group.id;
        },
        deleteGroup() {
            axios.delete(GROUPS_URL + "/" + this.group.id).then(function () {
                window.location.reload();
            });
        }
    }
});

Vue.component('group-list', {
    props: ['groups'],
    template: '#group-list'
});

new Vue({
    el: '#app',
    data: {
        groups: [],
        userImgUrl: ''
    },
    created: function () {
        axios.get(GROUPS_URL + ALL).then(response => {
            let groupList = response.data;
            groupList.forEach(group => this.groups.push(group));
        });
        axios.get(USER_IMG_URL).then(response => {
            this.userImgUrl = response.data;
        });
    }
});