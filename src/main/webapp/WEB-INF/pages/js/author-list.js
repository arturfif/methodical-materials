$(document).ready(function() {
    var max_fields      = 10; //maximum input boxes allowed
    var wrapper         = $(".input_authors_wrap"); //Fields wrapper
    var add_button      = $(".add_author_button"); //Add button ID

    var x = 1; //initial text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div><input type="text" class="form-control" pattern="([а-яА-ЯҐґ\'ЄєЇїІі-]){1,50} (([а-яА-ЯҐґ\'ЄєЇїІі-]){1}.){1,2}" ' +
            'placeholder="Формат: Фамилия И.О. или Фамилия И." name="authorSet"><a href="#" class="remove_author">Удалить</a></div>'); //add input box
        }
    });

    $(wrapper).on("click",".remove_author", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent('div').remove(); x--;
    })
});