new Vue({
    el: '#app',
    data: {
        group: {
            name: '',
            description: ''
        },
        userImgUrl: ''
    },
    created: function() {
        axios.get(USER_IMG_URL).then(response => {
            this.userImgUrl = response.data;
        });
    },
    methods: {
        saveGroup() {
            let formData = new FormData();
            formData.append('name', this.group.name);
            formData.append('description', this.group.description);
            instance.post(GROUPS_URL, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then(function () {
                window.location.reload();
            });
        }
    }
});