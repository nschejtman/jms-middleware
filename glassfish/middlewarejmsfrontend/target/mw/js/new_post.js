// Register form listener
let $form = $('#new-post-form');

$form.submit(function(){
    $form.find('#j_date_time').val(new Date());
});