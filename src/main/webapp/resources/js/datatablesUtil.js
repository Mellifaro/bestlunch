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
            successNoty('Saved');
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
    var editText = getTextByLocale("Edit", "Редактир", "Редактув");
    if(type == 'display'){
        return '<a class="btn btn-xs btn-primary" onclick="updateRow(' + row.id + ',\'' + key + '\');">' + editText + '</a>';
    }
}

function renderDeleteBtn(data, type, row){
    var deleteText = getTextByLocale("Delete", "Удалить", "Видалити");
    if(type == 'display'){
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' +row.id + ')">' + deleteText + '</a>';
    }
}

function renderLunchesBtn(type, row, key){
    var lunchesText = getTextByLocale("Lunches", "Ланчи", "Ланчі");
    if(type == 'display'){
        return '<a class="btn btn-xs btn-primary" onclick="moveToLunches(' +row.id + ')">' + lunchesText + '</a>';
    }
}

function renderDishesBtn(type, row, key){
    var dishesText = getTextByLocale("Dishes", "Блюда", "Страви");
    if(type == 'display'){
        return '<a class="btn btn-xs btn-primary" onclick="moveToDishes(' +row.id + ')">' + dishesText + '</a>';
    }
}

function moveToLunches(id) {
    window.location.href = window.location.href + id + '/lunches/';
}

function moveToDishes(id) {
    window.location.href = window.location.href + id + '/dishes/';
}

function getLanguageRef(){
    var locale = getLocaleCookie();
    switch (locale){
        case "ru": return "//cdn.datatables.net/plug-ins/1.10.15/i18n/Russian.json";
        case "ua": return "//cdn.datatables.net/plug-ins/1.10.15/i18n/Ukrainian.json";
    }
    return "//cdn.datatables.net/plug-ins/1.10.15/i18n/English.json";    
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
    return "en";
}

function getLocaleCookie(){
    return getCookie("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE");
}

function getTextByLocale(enText, ruText, uaText){
    var locale = getLocaleCookie();
    switch (locale){
        case "ru": return ruText;
        case "ua": return uaText;
    }
    return enText;

}
