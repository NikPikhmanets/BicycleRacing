$(document).ready(function () {
    $.ajax({
        url: "profile/1",
        async: true,
        dataType: 'json',
        success: function (data) {
            parse_response(data);
            console.log(data);
        }
    });
});

function request_page(link) {
    $.ajax({
        url: link,
        async: true,
        dataType: 'json',
        success: function (data) {
            parse_response(data);
            console.log(data);
        }
    });
}

function parse_response(data) {
    createe_cards(data);
    pagination(data);
}

function createe_cards(data) {
    var content = data.content;
    var wrapper = '.card-wrapper';

    $(wrapper).children().remove();

    for (var i = 0; i < content.length; i++) {
        var localDate = new Date(content[i].timeStart).toLocaleString();

        $(wrapper).append(
            '<div class="card w-50">' +
            '    <div class="card-header">' +
                    content[i].nameEvent +
            '    </div>' +
            '    <div class="card-body">' +
            '        <div class="row">' +
            '            <div class="col-sm-6">' +
            '                <h6 class="card-title">' +
                                content[i].typeEvent +
            '                </h6>' +
            '                <h6 class="card-subtitle mb-2 text-muted"> Time start: ' + localDate + '</h6>' +
            '                <h6 class="card-subtitle mb-2 text-muted"> Distance: ' + content[i].distance + ' km</h6>' +
            '                <h6 class="card-subtitle mb-2 text-muted"> Track name: ' + content[i].trackName + '</h6>' +
            '            </div>' +
            '            <div class="col-sm-6">' +
            '                <div class="text-right">' +
            '                    <a href="/result?eventId=' + content[i].eventId + '" class="btn btn-primary">Result</a>' +
            '                    <a href="/view_event?eventId=' + content[i].eventId + '" class="btn btn-primary">Upload gpx file</a>' +
            '                </div>' +
            '            </div>' +
            '            <br><br>' +
            '        </div>' +
            '    </div>' +
            '</div>'
        );
    }
}

function pagination(data) {
    window.pagObj = $('#pagination').twbsPagination({
        totalPages: data.totalPages,
        visiblePages: 10,
        onPageClick: function (event, page) {
            request_page("/profile/" + page);
            console.info(page + ' (from options)');
        }
    }).on('page', function (event, page) {
        console.info(page + ' (from event listening)');
    });
}