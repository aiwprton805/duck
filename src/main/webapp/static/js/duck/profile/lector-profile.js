const termTabItems = {
    new: 'new',
    chk: 'checking',
    ru: 'ru',
    de: 'de'
};

const lAppContextUrl = document.getElementById('lector-app').getAttribute('data-app-context-url');
const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

const groupComponent = {
    props: {
        group: Object
    },
    computed:{
        groupLink(){
            return `${lAppContextUrl}group/${this.group.id}`;
        }
    },
    methods: {
        preDeleteGroup() {
            this.$emit('show-modal', this.group);
        }
    }
};
const groupInputComponent = {
    data() {
        return {
            groupName: ''
        }
    },
    methods: {
        postGroup() {
            if (this.groupName === '') return;
            axios.post(lAppContextUrl + 'profile/lector/group',
                {
                    name: this.groupName
                },
                {
                    headers: {'X-XSRF-TOKEN': csrfToken}
                }).then(response => {
                    this.$emit('add-group', response.data);
                    this.groupName = '';
                }).catch(error => {
                    console.log(error);
                    this.groupName = '';
                });
        }
    }
};
const groupsPanelComponent = {
    components: {
        'group-component': groupComponent,
        'group-input-component': groupInputComponent
    },
    props: {
        addGroupsButtonText: String,
        hideInputFieldButtonText: String
    },
    data() {
        return {
            groups: [],
            removableGroup: {},
            visibleGroupInputComponent: false,
            isRemoveGroupModalActive: false,
            buttonText: this.addGroupsButtonText
        }
    },
    created() {
        this.getGroups();
    },
    methods: {
        getGroups() {
            axios.get(lAppContextUrl + 'profile/lector/groups')
                .then(response => {
                    this.groups = response.data;
                })
                .catch(error => {
                    console.log(error);
                });
        },
        addGroup(group) {
            this.groups.push(group);
        },
        switchVisibilityGroupInputComponent() {
            this.visibleGroupInputComponent = !this.visibleGroupInputComponent;
            this.buttonText = this.visibleGroupInputComponent ? this.hideInputFieldButtonText : this.addGroupsButtonText;
        },
        showModal(group) {
            this.removableGroup = group;
            this.isRemoveGroupModalActive = true;
        },
        hideModal() {
            this.removableGroup = {};
            this.isRemoveGroupModalActive = false;
        },
        removeGroup() {
            axios.post(lAppContextUrl + 'profile/lector/group/delete/' + this.removableGroup.id,
                {},
                {
                    headers: {'X-XSRF-TOKEN': csrfToken}
                }).then(response => {
                this.groups.splice(this.groups.indexOf(this.removableGroup), 1);
                this.hideModal();
            }).catch(error => {
                console.log(error);
                this.hideModal();
            });
        }
    }
};
const termComponent = {
    props: {
        term: Object
    }
};
const termGroupInputComponent = {
    data() {
        return {
            ruTermName: '',
            deTermName: ''
        }
    },
    methods: {
        postTermGroup() {
            if (this.ruTermName === '' || this.deTermName === '') return;
            axios.post(`${lAppContextUrl}profile/lector/termgroup`,
                {
                    ruTermName: this.ruTermName,
                    deTermName: this.deTermName
                },
                {
                    headers: {'X-XSRF-TOKEN': csrfToken}
                }).then(response => {
                this.$emit('add-terms', response.data);
                this.ruTermName = '';
                this.deTermName = '';
            }).catch(error => {
                console.log(error);
                this.ruTermName = '';
                this.deTermName = '';
            });
        }
    }
};
const termsPanelComponent = {
    components: {
        'term-component': termComponent,
        'term-group-input-component': termGroupInputComponent
    },
    props: {
        addTermsButtonText: String,
        hideInputFieldsButtonText: String
    },
    data() {
        return {
            activeTab: termTabItems.new,
            terms: [],
            visibleTermInputComponent: false,
            buttonText: this.addTermsButtonText
        }
    },
    created() {
        this.getTerms();
    },
    methods: {
        switchTab(tab) {
            switch (tab) {
                case termTabItems.new:
                case termTabItems.chk:
                case termTabItems.ru:
                case termTabItems.de: {
                    this.activeTab = tab;
                    this.getTerms();
                    break;
                }
                default:
                    console.log('wrong termTab on switchTab (lector-profile.js)');
            }
        },
        getTerms() {
            axios.get(`${lAppContextUrl}profile/lector/terms/${this.activeTab}`)
                .then(response => {
                    this.terms = response.data;
                })
                .catch(error => {
                    console.log(error);
                });
        },
        switchVisibilityTermInputComponent() {
            this.visibleTermInputComponent = !this.visibleTermInputComponent;
            this.buttonText = this.visibleTermInputComponent ? this.hideInputFieldsButtonText : this.addTermsButtonText;
        },
        addTerms(newTerms) {
            if (this.activeTab !== termTabItems.new) return;
            this.terms = this.terms.concat(newTerms);
        }
    }
};

const lApp = new Vue({
    el: '#lector-app',
    components: {
        'groups-panel-component': groupsPanelComponent,
        'terms-panel-component': termsPanelComponent
    }
});
