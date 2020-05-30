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
        if (existingGroup != null) {
            this.group.name = existingGroup.name;
            this.group.description = existingGroup.description;
        }
    },
    methods: {
        saveGroup() {
            let formData = new FormData();
            formData.append('name', this.group.name);
            formData.append('description', this.group.description);
            if (existingGroup == null) {
                instance.post(GROUPS_URL, formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }).then(function () {
                    window.location.reload();
                });
            } else {
                instance.put(GROUPS_URL + "/" + existingGroup.id, formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }).then(function() {
                    window.location.href = "/";
                })
            }
        }
    }
});