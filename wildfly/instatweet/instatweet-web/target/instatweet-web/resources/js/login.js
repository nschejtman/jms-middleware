function login(){
    let $form = $('#login-form');
    $form.attr('action', 'login');
    $form.attr('method', 'post');
    $form.submit();
}

function register(){
    let $form = $('#login-form');
    $form.attr('action', 'register');
    $form.attr('method', 'post');
    $form.submit();
}