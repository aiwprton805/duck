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
    computed:{
        groupLink(){
            return `${sAppContextUrl}group/${this.group.id}`;
        }
    },
    methods: {
        preLeaveGroup() {
            this.$emit('show-modal', this.group);
        }
    }
};
const sGroupInputComponent = {
    props: {
        badTokenErrorText: String,
        groupAlreadyExistsErrorText: String
    },
    data() {
        return {
            accessCode: '',
            errorMessageText: '',
            visibleErrorMessage: false
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
                if(this.visibleErrorMessage) this.hideErrorMessage();
            }).catch(error => {
                if(error.response && error.response.data){
                    if(error.response.data.status === 404 && error.response.data.message === 'Bad token'){
                        this.errorMessageText = this.badTokenErrorText;
                    }else if(error.response.data.status === 409 && error.response.data.message === 'Group already exists'){
                        this.errorMessageText = this.groupAlreadyExistsErrorText;
                    }else{
                        this.errorMessageText = 'Unknown error';
                    }
                    this.showErrorMessage();
                }else{
                    console.log(error);
                    this.accessCode = '';
                }
            });
        },
        showErrorMessage(){
            this.visibleErrorMessage = true;
        },
        hideErrorMessage(){
            this.visibleErrorMessage = false;
        }
    }
};
const sGroupsPanelComponent = {
    components: {
        's-group-component': sGroupComponent,
        's-group-input-component': sGroupInputComponent
    },
    props: {
        joinGroupButtonText: String,
        hideInputFieldButtonText: String
    },
    data() {
        return {
            groups: [],
            leavingGroup: {},
            visibleGroupInputComponent: false,
            isLeaveGroupModalActive: false,
            buttonText: this.joinGroupButtonText
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
            this.buttonText = this.visibleGroupInputComponent ? this.hideInputFieldButtonText : this.joinGroupButtonText;
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
