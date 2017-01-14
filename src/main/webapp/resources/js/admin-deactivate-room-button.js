$('.for-table tbody').on('click', '.deactivateButton', function () {
    var dialog = $('#deactivateModal');
    var btn = this;
    var tr = $(btn).parents('tr');
    var roomId = tr.find('td').eq(0).text();
    verifyRoomBookingState(roomId);
    dialog.modal('show');
    $('#deactivateYesButton').unbind('click');
    $('#deactivateYesButton').click(function () {
        changeActiveRoomState(roomId, btn);
        dialog.modal('hide');
    });
    $('#deactivateNoButton').click(function () {
        dialog.modal('hide');
    });
});

$('.for-table tbody').on('click', '.activateButton', function () {
    var dialog = $('#activateModal');
    var btn = this;
    var tr = $(btn).parents('tr');
    dialog.modal('show');
    $('#activateYesButton').unbind('click');
    $('#activateYesButton').click(function () {
        var roomId = tr.find('td').eq(0).text();
        changeActiveRoomState(roomId, btn);
        dialog.modal('hide');
    });
});

function changeActiveRoomState(roomId, btn) {
    var src = 'adm-edit-room?id=' + roomId;
    var inputData = {id : roomId};
    $.ajax({
        url: src,
        type: 'POST',
        data: JSON.stringify(inputData),
        success: function () {
            $(btn).toggleClass('activateButton delete deactivateButton save');
        }
    });
}

function verifyRoomBookingState(roomId) {
    var src = 'adm-edit-room\\warnings';
    var inputData = {id : roomId};
    var warningMessages = [];
    $.ajax({
        url: src,
        data: inputData,
        success: function (data) {
            data = JSON.parse(data);
            if (data != []) {
                if (data.includes(constants.room.warnings.active)) {
                    warningMessages.push(messages.room.warnings.active);
                }
                if (data.includes(constants.room.warnings.planning)) {
                    warningMessages.push(messages.room.warnings.planning);
                }
                $('#warningMessages').html('');
                $.each(warningMessages, function (index, value) {
                    $('#warningMessages').append('<div class = warningMessage>' + value + '</div>');
                })
            }
        }
    });
}
