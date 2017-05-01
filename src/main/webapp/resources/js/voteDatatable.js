var ajaxUrl = window.location.href.replace('bestlunch', 'bestlunch/ajax');
var datatableApi;

$(function () {
    var languageRef = getLanguageRef();
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": true,
        "info": true,
        "language": {
            "url": languageRef
        },
        "columns": [
            {
                "data": "restaurant.name"
            },            
            {
                "data": "time",
                "render": function (data, type, row) {
                    return data.replace('T', ' ').substr(0, 16);
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

