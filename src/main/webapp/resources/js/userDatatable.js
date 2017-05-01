var ajaxUrl = 'ajax/admin/users/';
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
                "data": "name"
            },
            {
                "data": "email",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<a href="mailto:' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            },
            {
                "data": "roles"
            },
            {
                "data": "password"
            },
            {
                "data": "enabled",
                "render": function(data, type, row){
                    if(type == 'display'){
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="enable($(this),' + row.id + ');" />';
                    }
                    return data;
                }
            },
            {
                "data": "registered",
                "render": function (data, type, row) {
                    return data.replace('T', ' ').substr(0, 16);
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": function (data, type, row) {
                    return renderEditBtn(type, row, getTextByLocale("Edit user", "Редактировать пользователя", "Редактувати користувача"));
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
        "createdRow": function(row, data){
            if (!data.enabled) {
                $(row).css("text-decoration", "line-through");
            }
        },
        "initComplete": makeEditable
    });
});

