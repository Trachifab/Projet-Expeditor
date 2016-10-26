(function($) {
    $(document).ready(function() {
        // Fermeture d'un message
        $(".item .sign.out").click(function(){
            $("#deconnexionForm").find("button.deconnexion").click();
        });
    });
})( jQuery );