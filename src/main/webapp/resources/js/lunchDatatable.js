var ajaxUrl = window.location.href.replace('bestlunch', 'bestlunch/ajax');
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
                "data": "price",
                "render": function(data, type, row){
                    return data == null ? "" : data;
                }
            },
            {
                "data": "dateTime",
                "render": function (data, type, row) {
                    return data.replace('T', ' ').substr(0, 16);
                }
            },
            {
                "data": "dishes",
                "render": function ( data, type, full ) {
                    return $.map( data, function ( d, i ) {
                        return ++i + '. ' + d.name;
                    } ).join( '<br>' );
                }
            },            
            {
                "orderable": false,
                "defaultContent": "",
                "render": function (data, type, row) {
                    return renderDishesBtn(type, row, 'users.edit');
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": function (data, type, row) {
                    return renderEditBtn(type, row, 'users.edit');
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": function (data, type, row) {
                    return renderDeleteBtn(data, type, row);
                }
            }

        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
});

