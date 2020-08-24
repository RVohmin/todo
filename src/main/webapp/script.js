$.ajax({
    type: 'GET',
    crossdomain: true,
    url: 'http://localhost:8080/todo/task',
}).done(function (data) {
    if (data === null) {
        return;
    }
    let mainCheckbox = $('#showTasks');
    mainCheckbox.prop('checked', true);
    for (let i = 0; i < data.length; data[i++]) {
        let idNum = data[i][0];
        let message = data[i][1];
        let date = data[i][2];
        let state = data[i][3];
        let user = data[i][4];
        let status;
        state ? status = ' в работе' : status = " выполнено";
        let el = `<label><input type="checkbox" id="${idNum}"><span>${status}</span></label>`;
        $("#table").find('tbody')
            .append($('<tr>')
                .append($('<td>')
                    .text(message)
                ).append($('<td>')
                    .text(date)
                )
                .append($('<td>')
                    .text(user)
                ).append($('<td >')
                    .append($(el)))
            );
        if (!state) {
            $(`#${idNum}`).prop('checked', true);
        }
    }
    $('input:checkbox').click(function () {
        if (this.id === 'showTasks') {
            return;
        }
        let id = $(this).get()[0].id;
        let status = $(this).parent().find('span').html();
        if ($(this).is(':checked')) {
            status = " выполнено";
            $(this).parent().css('color', 'blue');
            $(this).parent().find('span').empty().html(`${status}`);
            updateStatus(id, false);
        } else {
            let status = " в работе";
            $(this).parent().css('color', 'black');
            $(this).parent().find('span').empty().html(`${status}`);
            updateStatus(id, true);
        }
    });
    mainCheckbox.click(function () {
        if (!mainCheckbox.is(':checked')) {
            $('input:checkbox:checked').each(function () {
                $(this).parent().parent().parent().hide();
            });
        }
        if (mainCheckbox.is(':checked')) {
            $('input:checkbox:checked').each(function () {
                $(this).parent().parent().parent().show();
            });
        }
    });
});

function updateStatus(id, state) {
    $.ajax({
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/todo/task',
        data: {
            id: id + "",
            describe: "text",
            done: state + ""
        }
    }).done(function () {
        console.log("updateStatus OK");
    }).fail(function (err) {
        alert("updateStatus Error" + err);
    })
}

function doSend() {
    let describe = $("textarea").val();
    $.ajax({
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/todo/task',
        data: {
            id: 0,
            describe: describe
        }
    }).done(function () {
    }).fail(function (err) {
        alert(err);
    });
}
