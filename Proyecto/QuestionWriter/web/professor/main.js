$(function () {
    function runEffect() {
        var options = {};
        $("#effect1").toggle("slide", options, 500);
    }
    ;

    $("#button1").on("click", function () {
        runEffect();
    });
    runEffect();
}
);

$(function () {
    function runEffect() {
        var options = {};
        $("#effect2").toggle("slide", options, 500);
    }
    ;

    $("#button2").on("click", function () {
        runEffect();
    });
    runEffect();
}
);

$(function () {
    $(".radio").checkboxradio();
});

$(function () {
    var $radios = $('.radio');
    if ($radios.is(':checked') === false) {
        $radios.filter('[value=Off]').prop('checked', true);
    }
});

$(function () {
    $("#tabs").tabs();
}
);

function validateForm() {
    var x = document.forms["contenidoPreg"]["contenido"].value;
    var y = document.forms["contenidoPreg"]["textA"].value;
    var z = document.forms["contenidoPreg"]["respA"].value;
    if (x === "") {
        alert("La pregunta necesita contenido");
        return false;
    }

    if (y === "") {
        alert("La opcion A es obligatoria");
        return false;
    }

    if (z === "") {
        alert("Los puntos A es obligatoria");
        return false;
    }
}