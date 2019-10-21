const lAppContextUrl = document.getElementById('lector-app').getAttribute('data-app-context-url');
const lGroupId = document.getElementById('lector-app').getAttribute('data-group-id');
const lCsrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

const termComponent = {
    props:{
        studentProfileId: Number,
        studentTerm: Object
    },
    methods:{
        removeTerm(){
            axios.post(`${lAppContextUrl}group/student/${this.studentProfileId}/remove-term/${this.studentTerm.id}`,
                {},
                {
                    headers: {'X-XSRF-TOKEN': lCsrfToken}
                }).then(response => {
                    this.$emit('term-removed', this.studentTerm.id);
                }).catch(error => {
                    console.log(error);
                });
        }
    }
};
const studentComponent = {
    components:{
        'term-component': termComponent
    },
    props:{
        profileId: Number,
        terms: Array
    },
    data(){
        return {
            selectedTermId: 0,
            isActive: false,
            studentTerms: []
        }
    },
    methods: {
        postAddTerm(){
            if(this.selectedTermId === 0) return;
            const addedTermId = this.selectedTermId;
            axios.post(`${lAppContextUrl}group/add-term`,
                {
                    studentProfileId: this.profileId,
                    termId: this.selectedTermId
                },
                {
                    headers: {'X-XSRF-TOKEN': lCsrfToken}
                }).then(response => {
                    this.selectedTermId = 0;
                    this.$emit('term-added', addedTermId);
                    if(this.isActive){
                        this.getStudentTerms();
                    }
                }).catch(error => {
                    console.log(error);
                });
        },
        removeStudent(){
            axios.post(`${lAppContextUrl}group/${lGroupId}/remove-student/${this.profileId}`,
                {},
                {
                    headers: {'X-XSRF-TOKEN': lCsrfToken}
                }).then(response => {
                    this.$destroy();
                    this.$el.parentNode.removeChild(this.$el);
                }).catch(error => {
                    console.log(error);
                });
        },
        switchActiveStudent(){
            this.isActive = !this.isActive;
            if(this.isActive) {
                this.getStudentTerms();
            }
            else {
                this.studentTerms = [];
            }
        },
        getStudentTerms(){
            axios.get(`${lAppContextUrl}group/terms/profile/${this.profileId}`)
                .then(response => {
                    this.studentTerms = response.data;
                })
                .catch(error => {
                    console.log(error);
                });
        },
        excludeStudentTerm(studentTermId){
            this.studentTerms.splice(this.studentTerms.findIndex((element, index, array) => {return element.id === studentTermId;}), 1);
            this.$emit('student-term-excluded');
        }
    }
};

const lectorApp = new Vue({
    el: '#lector-app',
    components: {
        'student-component': studentComponent
    },
    created(){
        this.getTerms();
    },
    data(){
        return {
            terms: []
        }
    },
    methods:{
        getTerms() {
            axios.get(`${lAppContextUrl}group/terms/`)
                .then(response => {
                    this.terms = response.data;
                })
                .catch(error => {
                    console.log(error);
                });
        },
        excludeTerm(termId){
            this.terms.splice(this.terms.findIndex((element, index, array) => {return element.id === termId;}), 1);
        }
    }
});
