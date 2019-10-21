const groupTokenElement = document.getElementById('group-token');
const refreshElement = document.getElementById('refresh-group-token-link');
const profileGroupId = refreshElement.getAttribute('data-profile-group-id');
const appContextPath = refreshElement.getAttribute('data-app-context-path');
const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

refreshElement.addEventListener('click', () => {
    axios.post(`${appContextPath}group/${profileGroupId}/refresh-token`,
        {},
        {
            headers: {'X-XSRF-TOKEN': csrfToken}
        }).then(response => {
            groupTokenElement.innerText = response.data.token;
        }).catch(error => {
            console.log(error);
        });
});

