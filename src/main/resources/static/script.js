$(document).ready(function () {
    $('body').on('click', '#chek', function () {
        if ($(this).hasClass('allChecked')) {
            $('input[type="checkbox"]', '#options').prop('checked', false);
        } else {
            $('input[type="checkbox"]', '#options').prop('checked', true);
        }
        $(this).toggleClass('allChecked');
    })
});
