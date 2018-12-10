$(function () {
    $(document).tooltip({
        show: {
            effect: "slideDown",
            delay: 0
        },

        hide: {
            effect: "slideUp",
            delay: 0
        }
    });
});


$(function () {
    $("input[type=radio]").checkboxradio();
});


$(function () {
    $(".drag").draggable();
});


$(function(){
    $(".drop").droppable({
        drop: function (event, ui) {                                      
          //display the ID in the console log:
          $(this).val( ui.draggable.attr("id"));
        }
    });
});