<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>play</title>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css" />
  <script>
      jQuery(document).bind('keydown', function (evt){
          var direction;
          switch(evt.which) {
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
              default: direction = "no_direction";
          }
          $.ajax({
              url: "/game",
              data : "direction=" + direction,
              type : "POST",
              async: true,
              success : function(response) {
                  var values = response.split(",");
                  for (var i = 0; i < 16; i++) {
                      document.getElementsByClassName("cell")[i].innerHTML = values[i] == 0 ? "" : values[i];
                      document.getElementsByClassName("cell")[i].setAttribute("id", "val" + values[i]);
                  }
              }
          });
      });
  </script>
</head>
<body>
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
        <div class="cell" >
        </div>
        <div class="cell" >
        </div>
        <div class="cell" >
        </div>
    </div>
    <div class="row">
        <div class="cell" >
        </div>
        <div class="cell" >
        </div>
        <div class="cell" >
        </div>
        <div class="cell" >
        </div>
    </div>
</div>
</body>
</html>
