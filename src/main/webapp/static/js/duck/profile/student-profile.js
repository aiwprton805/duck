const sTermTabItems = {
    all: 'all',
    ru: 'ru',
    de: 'de'
};

const sAppContextUrl = document.getElementById('student-app').getAttribute('data-app-context-url');
const sCsrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

const sGroupComponent = {
    props: {
        group: Object
    },
    methods: {
        preLeaveGroup() {
            this.$emit('show-modal', this.group);
        }
    }
};
const sGroupInputComponent = {
    data() {
        return {
            accessCode: ''
        }
    },
    methods: {
        postJoinGroup() {
            if (this.accessCode === '') return;
            axios.post(sAppContextUrl + 'profile/student/join-group',
                {
                    token: this.accessCode
                },
                {
                    headers: {'X-XSRF-TOKEN': sCsrfToken}
                }).then(response => {
                this.$emit('join-group', response.data);
                this.accessCode = '';
            }).catch(error => {
                console.log(error);
                this.accessCode = '';
            });
        }
    }
};
const sGroupsPanelComponent = {
    components: {
        's-group-component': sGroupComponent,
        's-group-input-component': sGroupInputComponent
    },
    props: {
        addGroupsButtonText: String,
        hideInputFieldButtonText: String
    },
    data() {
        return {
            groups: [],
            leavingGroup: {},
            visibleGroupInputComponent: false,
            isLeaveGroupModalActive: false,
            buttonText: this.addGroupsButtonText
        }
    },
    created() {
        this.getGroups();
    },
    methods: {
        getGroups() {
            axios.get(sAppContextUrl + 'profile/student/groups')
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
            this.leavingGroup = group;
            this.isLeaveGroupModalActive = true;
        },
        hideModal() {
            this.leavingGroup = {};
            this.isLeaveGroupModalActive = false;
        },
        leaveGroup() {
            axios.post(sAppContextUrl + 'profile/student/group/leave/' + this.leavingGroup.id,
                {},
                {
                    headers: {'X-XSRF-TOKEN': sCsrfToken}
                }).then(response => {
                this.groups.splice(this.groups.indexOf(this.leavingGroup), 1);
                this.hideModal();
            }).catch(error => {
                console.log(error);
                this.hideModal();
            });
        }
    }
};
const sTermComponent = {
    props: {
        term: Object
    }
};
const sTermsPanelComponent = {
    components: {
        's-term-component': sTermComponent
    },
    data() {
        return {
            activeTab: sTermTabItems.all,
            terms: []
        }
    },
    created() {
        this.getTerms();
    },
    methods: {
        switchTab(tab) {
            switch (tab) {
                case sTermTabItems.all:
                case sTermTabItems.ru:
                case sTermTabItems.de: {
                    this.activeTab = tab;
                    this.getTerms();
                    break;
                }
                default:
                    console.log('wrong termTab on switchTab (student-profile.js)');
            }
        },
        getTerms() {
            axios.get(`${sAppContextUrl}profile/student/terms/${this.activeTab}`)
                .then(response => {
                    this.terms = response.data;
                })
                .catch(error => {
                    console.log(error);
                });
        }
    }
};

const sApp = new Vue({
    el: '#student-app',
    components: {
        's-groups-panel-component': sGroupsPanelComponent,
        's-terms-panel-component': sTermsPanelComponent
    }
});
