$(document).ready(function() {



    $("#faculty").on("change", function (e) {

        var facultyId = $(this).val();
        var changeSelect = false;

        $("#specialty").find("option").each(function() {

            if($(this).attr("title") != facultyId) {
                $(this).hide();
            } else {
                if(!changeSelect) {
                    $(this).attr('selected', 'selected');
                    changeSelect = true;
                }
                $(this).show();
            }
        });
    });
    
});