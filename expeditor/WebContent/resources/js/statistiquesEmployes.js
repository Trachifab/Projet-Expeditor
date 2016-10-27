/**
 * Created by d1502doreyf on 26/10/2016.
 */


$(document).ready(
    $.ajax({
            method: 'GET',
            url : 'manager',
            data : {action : 'histo'}
        }

    ).done(function(jsonStats){
        afficherHisto(jsonStats);
    })
)

function afficherHisto(jsonStats) {

    var obj = JSON.parse(jsonStats);

    $('#histogramme').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Performances employé du jour'
        },

        xAxis: {
            categories: obj.colonnes,
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Nombre de commandes traitées'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Commandes traitées',
            data: obj.valeurs
        }]
    });
};