$(document).ready(function () {
    $("#clickButton").click(function () {
        alert("hello world");
        $.ajax({
            url: "http://localhost:8080/userDirectory",
            method: "GET",
            dataType: "json"
        })
            .done(function (json) {
                $.each(json, function (index, item) {
                    $('<p></p>').val(JSON.stringify(item)).text(JSON.stringify(item)).appendTo($('#resultArea'));
                });
            });
    });

});