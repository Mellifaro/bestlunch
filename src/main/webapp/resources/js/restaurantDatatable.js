var ajaxUrl = 'ajax/restaurants/';
var datatableApi;

var currentVote;
var ajaxCurrentVoteURL = 'ajax/votes/currentVote/';
var ajaxVoteURL = 'ajax/votes/';
var limitHour = 11;

$(function () {
    receiveCurrentVote();
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
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": function (data, type, row) {
                    return renderVoteBtn(type, row, 'users.edit');
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": function (data, type, row) {
                    return renderLunchesBtn(type, row, 'users.edit');
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
                    return renderEditBtn(type, row, getTextByLocale("Edit кestaurant", "Редактировать ресторан", "Редактувати ресторан"));
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

function receiveCurrentVote(){
    $.ajax({
        async:false,
        url: ajaxCurrentVoteURL,
        type: 'GET',
        success: function(data) {
            currentVote = data;
        }
    });
    renderMainCancelButton();
}

function renderMainCancelButton() {
    var cancelButton = $("#cancelButton");
    var now = new Date();
    if(currentVote != null && now.getHours() > limitHour){
        cancelButton.show();
        cancelButton.addClass("disabled");
    }
    if(currentVote != null && now.getHours() < limitHour){
        cancelButton.show();
    }
}

function renderVoteBtn(type, row, key){
    var now = new Date();
    var cancelText = getTextByLocale("Cancel", "Отмена", "Відміна");
    var voteText = getTextByLocale("Vote", "Голос", "Голос");
    if(currentVote != null && currentVote.restaurant.id == row.id && now.getHours() < limitHour){
        return '<a class="btn btn-xs btn-danger" onclick="cancelVote()">' + cancelText + '</a>';
    }else if(currentVote != null && currentVote.restaurant.id == row.id && now.getHours() > limitHour){
        return '<a class="btn btn-xs btn-danger disabled">' + cancelText + '</a>';
    }else if(currentVote != null && currentVote.restaurant.id != row.id){
        return '<a class="btn btn-xs btn-primary disabled">' + voteText + '</a>';
    }else{
        return '<a class="btn btn-xs btn-primary" onclick="vote(' +row.id + ')">' + voteText + '</a>'
    }
}

function cancelVote() {
    $.ajax({
        url: ajaxCurrentVoteURL,
        type: 'DELETE',
        success: function () {
            successNoty('Your vote was deleted');
        }
    });
}

function vote(id){
    $.ajax({
        url: ajaxVoteURL + id,
        type: 'POST',
        success: function(){
            updateTable();
            successNoty('Deleted');
        }
    });
}


