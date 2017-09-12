populate();

$("#submit").click(function(e) {
    var name = $("#name").val();
    var termino = $("#dataTermino").val();
    var feito = $("#dataTermino").val();

    $.ajax({
        type: "POST",
        url: "/task/rest/task/new",
        data: JSON.stringify({
            name: name,
            dateFinish: termino,
            done: feito == "Sim",
        }),
        crossDomain: true,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(){
            window.location.reload();
            alert("Tarefa cadastrada!");
        },
        error: function(){
            $.notify("Falha ao criar tarefa!", "error");
        }
    });
});

function populate() {
    $.ajax({
        type: "GET",
        url: "/task/rest/task/all",
        dataType: "json",
        success: function(res) {
            $("#tasks").append(res);
        }
    });
}