$(document).ready(function () {
    $("#submitButton").submit(function (event) {
        event.preventDefault();
        uploadFile();
    });
});

function uploadFile() {
    $.ajax({
        url: "/upload",
        type: "POST",
        data: new FormData($("#upload-file-form")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function () {
            // Handle upload success
            $("#upload-file-message").text("File succesfully uploaded");
        },
        error: function () {
            // Handle upload error
            $("#upload-file-message").text("File not uploaded");
        }
    });
}