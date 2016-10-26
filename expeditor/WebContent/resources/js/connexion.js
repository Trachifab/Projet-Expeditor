(function($) {
    $(document).ready(function() {
        // Fermeture d'un message
        $("i.icon.close").click(function(){
            $(this).parent().slideUp();
        });

        var $idMdpOublie = $("#mdpOublie");

        // Ouverture de la modale
        $idMdpOublie.click(function(){
            $('.small.modal')
                .modal({
                    selector    : {
                        approve  : '.actions .positive, .actions .approve, .actions .ok'
                    },
                    onApprove : function() {
                        $(".ui.message.success").slideDown();
                    }
                })
                .modal('show')
            ;
        });
    });
})( jQuery );