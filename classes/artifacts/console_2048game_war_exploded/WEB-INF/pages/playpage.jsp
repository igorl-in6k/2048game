<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>play</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css"/>
    <script>
        jQuery(document).bind('keydown', function (evt) {
            var direction;
            switch (evt.which) {
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
                url: "/",
                data: "direction=" + direction,
                type: "POST",
                async: true,
                success: function (response) {
                    var values = response.split(",");
                    for (var i = 0; i < 16; i++) {
                        document.getElementsByClassName("cell")[i].innerHTML = values[i] == 0 ? "" : values[i];
                        document.getElementsByClassName("cell")[i].setAttribute("id", "val" + values[i]);
                    }
                    document.getElementById("score").innerHTML = "Score: " + values[16];
                }
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div id="gamefield-header">
        <h2 id="score"></h2>

        <div id="gamefield">
            <div class="row">
                <div class="cell">
                </div>
                <div class="cell">
                </div>
                <div class="cell">
                </div>
                <div class="cell">
                </div>
            </div>
            <div class="row">
                <div class="cell">
                </div>
                <div class="cell">
                </div>
                <div class="cell">
                </div>
                <div class="cell">
                </div>
            </div>
            <div class="row">
                <div class="cell">
                </div>
                <div class="cell">
                </div>
                <div class="cell">
                </div>
                <div class="cell">
                </div>
            </div>
            <div class="row">
                <div class="cell">
                </div>
                <div class="cell">
                </div>
                <div class="cell">
                </div>
                <div class="cell">
                </div>
            </div>
        </div>
    </div>
    <form method="post" action="/newgame" id="newgameform">
        <input type="submit" value="New Game" id="newgamebutton">
    </form>
</div>

</body>
</html>
