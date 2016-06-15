$(document).ready(function () {

    var formInputs = [];

    $('input[name=surname]').on('focusout', function () {
        var surnameText = $(this).val();
        var surnamePattern = new RegExp('^[а-яА-Я- ]{3,50}$');
        var valid = surnamePattern.test(surnameText);
        if(!valid) {
            var surnameError = $('#surnameError');
            surnameError.val('Введите минимум 3 символа. Допускается русский алфавит, дефис и пробел');
            surnameError.show();
        } else {
            $('#surnameError').hide();
        }

    })
    
    
});

