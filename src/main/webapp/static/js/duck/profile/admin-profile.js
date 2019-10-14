const items = {
    g: 'guests',
    s: 'students',
    l: 'lectors',
    a: 'all',
    p: 'proposals'
}

const appContextUrl = document.getElementById('admin-app').getAttribute('app-context-url');
const appCsrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
const MAX_ROW = 50;

const rolesCellComponent = {
    template: `
<td class="has-text-centered">
    <a @click="changeRole('admin')"><span class="tag" :class="isAdmin ? 'is-success' : 'is-danger'">Admin</span></a>
    <a @click="changeRole('lector')"><span class="tag" :class="isLector ? 'is-success' : 'is-danger'">Lector</span></a>
    <a @click="changeRole('student')"><span class="tag" :class="isStudent ? 'is-success' : 'is-danger'">Student</span></a>
    <a @click="changeRole('guest')"><span class="tag" :class="isGuest ? 'is-success' : 'is-danger'">Guest</span></a>
</td>`,
    props:{
        roles: Array
    },
    data(){
        return {
            permittedRoles: ['admin', 'lector', 'student', 'guest']
        }
    },
    computed:{
        rolesNames(){
            return this.roles.map(role => role.name);
        },
        isAdmin(){
            return this.rolesNames.includes(this.permittedRoles[0]);
        },
        isLector(){
            return this.rolesNames.includes(this.permittedRoles[1]);
        },
        isStudent(){
            return this.rolesNames.includes(this.permittedRoles[2]);
        },
        isGuest(){
            return this.rolesNames.includes(this.permittedRoles[3]);
        }
    },
    methods:{
        changeRole(roleName){
            this.$emit('change-role', {roleName: roleName, existence: this.rolesNames.includes(roleName)});
        }
    }
};
const tableComponent = {
    template:`
<table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
<thead>
<tr>
  <th v-for="header in headers" :key="header" class="has-text-centered">{{header}}</th>
</tr>
</thead>
<tbody v-if="type === 'proposals'">
<tr v-for="row in rows" :key="row.id">
  <td>{{proposalName(row)}}</td>
  <td v-if="row.sex === 'MALE'" class="has-text-centered"><i class="fas fa-male"></i></td>
  <td v-else-if="row.sex === 'FEMALE'" class="has-text-centered"><i class="fas fa-female"></i></td>
  <td v-else></td>
  <td><a :href="'mailto:' + row.email">{{row.email}}</a></td>
  <td><a :href="appContextUrl + 'proposal/' + row.id">{{row.term}}</a></td>
</tr>
</tbody>
<tbody v-else-if="type === 'all'">
<tr v-for="row in rows" :key="row.userId">
  <td>{{row.username}}</td>
  <roles-cell-component :roles="row.roles" @change-role="postUser(row, ...arguments)"></roles-cell-component>
  <td>{{row.name}}</td>
  <td><a :href="'mailto:' + row.email">{{row.email}}</a></td>
</tr>
</tbody>
<tbody v-else-if="type === 'other'">
<tr v-for="row in rows" :key="row.userId">
  <td>{{row.username}}</td>
  <td>{{row.name}}</td>
  <td><a :href="'mailto:' + row.email">{{row.email}}</a></td>
</tr>
</tbody>
</table>`,
    components:{
        'roles-cell-component': rolesCellComponent
    },
    props:{
        rows: Array,
        type: String,
        headers: Array,
        appContextUrl: String
    },
    methods:{
        proposalName(proposal){
            return `${proposal.lastName} ${proposal.firstName} ${proposal.patronymic} ${proposal.nickname}`;
        },
        postUser(userProfile, changeRoleData){
            const user = {
                id: userProfile.userId,
                name: userProfile.username,
                password: userProfile.password,
                roles: changeRoleData.existence ?
                    userProfile.roles.filter(role => role.name !== changeRoleData.roleName) :
                    userProfile.roles.concat([{id: null, name: changeRoleData.roleName}])
            };
            user.roles = user.roles.map(r => {return {id: r.id, name: r.name}}); //clear Vue properties
            axios.post(appContextUrl + 'profile/admin/user', user,
                {
                    headers: {'X-XSRF-TOKEN': appCsrfToken}
                }
            ).then(() => {
                this.$emit('update-users-roles', userProfile, user.roles);
            }).catch(error => {
                console.log(error);
            });
        }
    }
};
const paginationComponent = {
    template: `
<nav v-if="pages > 1" class="pagination is-centered is-small" role="navigation" aria-label="pagination">
  <a class="pagination-previous" @click="switchPage('prev')">{{prevLabel}}</a>
  <a class="pagination-next" @click="switchPage('next')">{{nextLabel}}</a>
  <div v-if="pages <= 5">
    <ul class="pagination-list">
      <li v-for="index in pages" :key="index"><a class="pagination-link" @click="switchPage(index)" :class="{'is-current': currentPage === index}">{{index}}</a></li>
    </ul>
  </div>
  <div v-else>
    <ul v-if="currentPage < 4" class="pagination-list">
      <li v-for="index in 4" :key="index"><a class="pagination-link" @click="switchPage(index)" :class="{'is-current': currentPage === index}">{{index}}</a></li>
      <li><span class="pagination-ellipsis">&hellip;</span></li>
      <li><a class="pagination-link" @click="switchPage(pages)" :class="{'is-current': currentPage === pages}">{{pages}}</a></li>
    </ul>
    <ul v-else-if="currentPage > pages - 3" class="pagination-list">
      <li><a class="pagination-link" @click="switchPage(1)" :class="{'is-current': currentPage === 1}">1</a></li>
      <li><span class="pagination-ellipsis">&hellip;</span></li>
      <li v-for="index in 4" :key="index"><a class="pagination-link" @click="switchPage(index + pages - 4)" :class="{'is-current': currentPage === index + pages - 4}">{{index + pages - 4}}</a></li>
    </ul>
    <ul v-else class="pagination-list">
      <li><a class="pagination-link" @click="switchPage(1)" :class="{'is-current': currentPage === 1}">1</a></li>
      <li><span class="pagination-ellipsis">&hellip;</span></li>
      <li v-for="index in 3" :key="index"><a class="pagination-link" @click="switchPage(index + currentPage - 2)" :class="{'is-current': currentPage === index + currentPage - 2}">{{index + currentPage - 2}}</a></li>
      <li><span class="pagination-ellipsis">&hellip;</span></li>
      <li><a class="pagination-link" @click="switchPage(pages)" :class="{'is-current': currentPage === pages}">{{pages}}</a></li>
    </ul>
  </div>
</nav>`,
    props: {
        size: Number,
        nextLabel: String,
        prevLabel: String
    },
    data() {
        return {
            currentPage: 1
        }
    },
    computed: {
        pages() {
            return Math.ceil(this.size / MAX_ROW);
        }
    },
    methods: {
        switchPage(btnValue){
            if(typeof btnValue === 'number' && (btnValue >= 1 && btnValue <= this.pages) && btnValue != this.currentPage)
                this.currentPage = btnValue;
            if(typeof btnValue === 'string') {
                if (btnValue === 'next' && this.currentPage !== this.pages) this.currentPage++;
                if (btnValue === 'prev' && this.currentPage !== 1) this.currentPage--;
            }
        }
    },
    watch:{
        currentPage: function (val) {
            this.$parent.$emit('turn-page', val);
        }
    }
};

const app = new Vue({
    el: '#admin-app',
    components: {
        'table-component': tableComponent,
        'pagination-component': paginationComponent
    },
    data() {
        return {
            activeMenuItem: items.p,
            tableData: [],
            size: 0,
            loading: false
        }
    },
    created() {
        this.getTableData(items.p);
        this.$on('turn-page', this.onTurnPage);
    },
    methods: {
        switchMenu(item) {
            if(item === this.activeMenuItem) return;
            switch (item) {
                case items.g:
                case items.s:
                case items.l:
                case items.a:
                case items.p: {
                    this.activeMenuItem = item;
                    this.getTableData(item);
                    break;
                }
                default:
                    console.log('wrong menuItem on switchMenu (admin-profile.js)');
            }
        },
        getTableData(uri, start = 0, length = MAX_ROW){
            this.loading = true;
            this.tableData = [];
            axios.get(appContextUrl + 'profile/admin/' + uri, {
                params: {
                    start: start,
                    length: length
                }
            }).then((response) => {
                this.tableData = response.data.data;
                this.size = response.data.size;
            }).catch((error) => {
                console.log(error);
            }).finally(() => {
                this.loading = false;
            });
        },
        onTurnPage(currentPage){
            const start = (currentPage - 1) * MAX_ROW;
            this.getTableData(this.activeMenuItem, start);
        },
        updateUsersRoles(userProfile, roles){
            if(this.activeMenuItem !== items.a) return;
            this.tableData[this.tableData.indexOf(userProfile)].roles = roles;
        }
    }
});
