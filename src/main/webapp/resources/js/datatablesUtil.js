var form;

function makeEditable() {
    form = $('#detailsForm');
    
    form.submit(function () {
        save();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function updateRow(id, key) {
    $('#modalTitle').html(key);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(
                key === "dateTime" ? value.replace('T', ' ').substr(0, 16) : value
            );
        });
        $('#editRow').modal();
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function(){
            updateTable();
            successNoty('Deleted');
        }
    })
}

function updateTable() {
    $.ajax({
        type: 'GET',
        url: ajaxUrl,
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
        }
    });
}

function save(){
    $.ajax({
        type: 'POST',
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
        }
    });
}

function enable(chkbox, id){
    var enabled = chkbox.is(":checked");
    chkbox.closest('tr').css("text-decoration", enabled ? "none" : "line-through");
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: "enabled=" + enabled,
        success: function () {
            successNoty(enabled ? 'Enabled' : 'Disabled');
        }
    })
}

var failedNote;

function closeNoty() {
    if(failedNote){
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    var errorInfo = $.parseJSON(jqXHR.responseText);
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + '<br>' + errorInfo.cause + '<br>' + errorInfo.details.join('<br>'),
        type: 'error',
        layout: 'bottomRight'
    });
}

function add(key){
    form.find(":input").val("");
    $('#modalTitle').html(key);
    $('#editRow').modal();    
}

function renderEditBtn(type, row, key){
    if(type == 'display'){
        return '<a class="btn btn-xs btn-primary" onclick="updateRow(' + row.id + ',\'' + key + '\');">Edit</a>';
    }
}

function renderDeleteBtn(data, type, row){
    if(type == 'display'){
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' +row.id + ')">Delete</a>';
    }
}

function renderLunchesBtn(type, row, key){
    if(type == 'display'){
        return '<a class="btn btn-xs btn-primary" onclick="moveToLunches(' +row.id + ')">Lunches</a>';
    }
}

function renderDishesBtn(type, row, key){
    if(type == 'display'){
        return '<a class="btn btn-xs btn-primary" onclick="moveToDishes(' +row.id + ')">Dishes</a>';
    }
}

function moveToLunches(id) {
    window.location.href = window.location.href + id + '/lunches/';
}

function moveToDishes(id) {
    window.location.href = window.location.href + id + '/dishes/';
}
