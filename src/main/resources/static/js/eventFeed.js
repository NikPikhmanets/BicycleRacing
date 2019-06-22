$(document).ready(function () {
    $.ajax({
        url: "events/1",
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
            '<div class="card" style="width: 50rem;">' +
            '    <div class="card-header">' +
                         content[i].title +
            '     </div>' +
            '        <div class="card-body">' +
            '            <h5 class="card-title">' + content[i].type + '</h5>' +
            '            <h6 class="card-subtitle mb-2 text-muted"> Time start: ' + localDate + '</h6>' +
            '            <h6 class="card-subtitle mb-2 text-muted"> Distance: ' + content[i].distance + ' km</h6>' +
            '            <a href="/view_event?eventId=' + content[i].id + '" class="btn btn-primary">View details event</a>' +
            '<p></p>' +
            '            <a href="/join?eventId=' + content[i].id + '" class="btn btn-primary">Join</a>' +
            '<p></p>' +
            '            <a href="/result?eventId=' + content[i].id + '" class="btn btn-primary">Result</a>' +
            '            </div>' +
            '      </div>' +
            '</div>' +
            '<p></p>'
        );
    }
}

function pagination(data) {
    window.pagObj = $('#pagination').twbsPagination({
        totalPages: data.totalPages,
        visiblePages: 10,
        onPageClick: function (event, page) {
            request_page("/events/" + page);
            console.info(page + ' (from options)');
        }
    }).on('page', function (event, page) {
        console.info(page + ' (from event listening)');
    });
}