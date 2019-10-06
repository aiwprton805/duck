new Vue({
    el: '#main-navbar',
    data: {
        burgerIsActive: false
    },
    methods:{
        switchBurgerActive(){
            this.burgerIsActive = !this.burgerIsActive;
        }
    }
});
