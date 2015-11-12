<%@ page import="game2048.core.GameField" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>play</title>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
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
          var data = 'direction=' + direction;
          $.ajax({
              url: "/game",
              data : data,
              type : "POST",
          });
      });
  </script>
</head>
<body>
<%GameField gameField = (GameField) session.getAttribute("gameField");%>
<table align="center" width="30%">
<%
    for (int[] ints : gameField.getValues()) {%>
        <tr>
        <%for (int anInt : ints) {%>
            <td style="font-size: 25px;"><%=anInt%></td>
        <%}%>
        </tr>
    <%}%>
</table>
</body>
</html>
