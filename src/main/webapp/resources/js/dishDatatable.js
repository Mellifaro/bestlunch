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
                "orderable": false,
                "defaultContent": "",
                "render": function (data, type, row) {
                    return renderEditBtn(type, row, getTextByLocale("Edit dish", "Редактировать блюдо", "Редактувати страву"));
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

