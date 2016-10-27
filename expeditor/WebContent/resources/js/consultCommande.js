(function ($) {
    // Modification de l'url pour enlever le paramètre contenant le paramètre
    history.pushState('', '', 'manager');

    $(document).ready(function () {
        // Initialisation du tri sur les tables
        $('table').tablesort();

        // Fermeture d'un message
        $("i.icon.close").click(function () {
            $(this).parent().slideUp();
        });

        // Si le message de validation d'import est présent, on le ferme au bout de 30 secondes
        var $idMessagesRow = $("#messagesRow");
        if ($idMessagesRow.length) {
            var slideUpMessagesRow = function () {
                $idMessagesRow.slideUp();
            };
            setTimeout(slideUpMessagesRow, 10000);
        }
    });
})(jQuery);