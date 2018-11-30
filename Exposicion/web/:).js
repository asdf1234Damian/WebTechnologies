$(function () {
    //Se declara el widget

    $.widget("custom.colorize", {
        options: {
            red: 250,
            green: 250,
            blue: 250
        },
        //Constructor
        _create: function () {
            //Se le da una clase para que se aplique el tema
            this.element.addClass("custom-colorize");
            //El widget tendra un boton
            this.changer = $("<button>", {
                text: "Cambiar",
                "class": "custom-colorize-changer"
            }).appendTo(this.element).button();
            //_on relaciona al widget con un evento
            // el evento en este caso es click en changer, que activa la fucion random
            this._on(this.changer, {click: "random"});
        },
        // Cada que se cambia alguna opcion
        _refresh: function () {
            this.element.css("background-color", "rgb(" +
                    this.options.red + "," +
                    this.options.green + "," +
                    this.options.blue + ")"
                    );
            //Cambio ( terminar el click o enter)
            this._trigger("change");
        },
        // Genera valores aleatorios para el widget
        random: function (event) {
            //Crea un objeto
            var colors = {
                red: Math.floor(Math.random() * 256),
                green: Math.floor(Math.random() * 256),
                blue: Math.floor(Math.random() * 256)
            };
            // En caso de que se cancele
            if (this._trigger("random", event, colors) !== false) {
                this.option(colors);
            }
        },
        //Destructor
        _destroy: function () {
            // remove generated elements
            this.changer.remove();
            this.element
                    .removeClass("custom-colorize")
                    .enableSelection()
                    .css("background-color", "transparent");
        },
        //funciones estandar , más en la documentacion
        _setOptions: function () {
            this._superApply(arguments);
            this._refresh();
        }
    });
    // Senicializa el elemento del html como un widget
    $("#my-widget1").colorize();
});
