document.addEventListener('keydown', function(event){
    var direction;
    switch (event.which) {
        case 37:
            direction = "left";
            break;
        case 38:
            direction = "up";
            break;
        case 39:
            direction = "right";
            break;
        case 40:
            direction = "down";
            break;
        default:
            direction = "no_direction";
    }
    $.ajax({
        url: "/game",
        data: "direction=" + direction,
        type: "POST",
        async: true,
        success: function (response) {
            var values = response.split(",");
            for (var i = 0; i < 16; i++) {
                document.getElementsByClassName("cell")[i].innerHTML = values[i] == 0 ? "" : values[i];
                document.getElementsByClassName("cell")[i].setAttribute("id", "val" + values[i]);
            }
            if (values[17] != "false")
                document.getElementById("score").innerHTML = "Score: " + values[16];
            else
                document.getElementById("score").innerHTML = "Game over. Your score is " + values[16];
        }
    });
});