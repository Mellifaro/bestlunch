var ajaxUrl = 'ajax/restaurants/';
var datatableApi;

$(function () {

    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": true,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "address"
            },
            {
                "data": "phone",
                "render": function (data, type, row) {
                    return data == null ? "" : data;
                }
            },
            {
                "data": "lunch",
                "render": function(data, type, row){
                    return data == null ? "" : data.name;
                }
            },
            {
                "data": "lunch.dishes",
                "render": function ( data, type, full ) {
                    return $.map( data, function ( d, i ) {
                        return ++i + '. ' + d.name;
                    } ).join( '<br>' );
                }
            },
            {
                "data": "lunch.price",
                "render": function(data, type, row){
                    return data == null ? "" : data;
                }
            },
            {
                "data": "votes"
            }            
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
});

