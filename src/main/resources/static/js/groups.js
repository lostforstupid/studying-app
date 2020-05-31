Vue.component('group-view', {
    props: ['group'],
    template: '#group-view',
    methods: {
        test() {
            axios.get(GROUPS_URL + IS_GROUP_HAS_CARDS + "/" + this.group.id).then(response => {
                let isGroupHasCards = response.data;
                console.log(isGroupHasCards);
                if (isGroupHasCards) {
                    window.location.href = TEST + "/" + this.group.id;
                } else {
                    alert("Group " + this.group.name + " doesn't have any cards");
                }
            });
        },
        getEditGroupLink() {
            return EDIT_GROUP + "/" + this.group.id;
        },
        deleteGroup() {
            if (confirm("All cards in this group will be deleted. " +
                "Do you still want to delete the group?")) {
                axios.delete(GROUPS_URL + "/" + this.group.id).then(function () {
                    window.location.reload();
                });
            }
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