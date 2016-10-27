/**
 * Created by Administrateur on 26/10/2016.
 */

(function($) {
    $(document).ready(function() {
        jQuery('.dropdown')
            .dropdown()
        ;
    });
})( jQuery );

function afficherModale(id){
    $('#' + id).modal('show');
}

function afficherEmployeModale(id, idEmploye, nom, prenom, email, password, role){

    $('#modaleEmploye [name=employeId]').val(idEmploye);
    $('#modaleEmploye [name=nomCollabo]').val(nom);
    $('#modaleEmploye [name=prenomCollabo]').val(prenom);
    $('#modaleEmploye [name=emailCollabo]').val(email);
    $('#modaleEmploye [name=mdpCollabo]').val(password);

    var libelle = $('#modaleEmploye option[value='+role+']').html();
    $('#modaleEmploye .ui.dropdown.selection div.text').html(libelle);
    $('#modaleEmploye .item').attr('class', 'item');
    $('#modaleEmploye .item[data-value='+role+']').attr('class', 'item active selected');
    $('#modaleEmploye [name=selectRole]').val(role);

    afficherModale(id);

}
