 var pageContent;
$(document).ready(function(){

    pageContent = $('.container');

});

 function showUsersPages() {
    $.get('/users', function (responce) {
        loadHTML(responce);
    });
}


 function loadHTML(html) {
    pageContent.html(html);
}
