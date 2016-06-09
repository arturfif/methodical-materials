$(document).ready(function() {
    var max_fields      = 10; //maximum input boxes allowed
    var wrapper         = $(".input_specialties_wrap"); //Fields wrapper
    var add_button      = $(".add_specialty_button"); //Add button ID

    var x = 1; //initial text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append($("#specialty").clone() +'<a href="#" class="remove_specialty">Удалить</a></div>'); //add input box
        }
    });

    $(wrapper).on("click",".remove_specialty", function(e){ //user click on remove text
        e.preventDefault();
        $(this).parent('div').remove(); x--;
    });

    $('#doit').click(function(e){
        e.preventDefault();
        if(x < max_fields) {
            x++;
            var $select = $('select[id^="specialty"]:last');

            var num = parseInt( $select.prop("id").match(/\d+/g), 10 ) +1;
            var $newSelect = $select.clone().prop('id', 'specialty'+ num );

            $(wrapper).append('<div>').append($newSelect).append('<a href="#" class="remove_specialty">Удалить</a></div>');
            //$select.after( $newSelect ).after('<a href="#" class="remove_specialty">Удалить</a></div>');
        }

    });

});