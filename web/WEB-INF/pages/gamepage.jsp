<%@ page import="game2048.core.GameField" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
  <title>play</title>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <link rel="stylesheet" href="/resources/css/style.css" type="text/css"/>
  <script src="/resources/js/script.js"></script>
</head>
<body>
<%GameField gf = (GameField) request.getAttribute("gameField");%>
<div class="container">
  <div id="gamefield-header">
    <h2 id="score">
      <%if (gf.hasAvailableMove()){%>
        Score: <%=gf.getScore()%>

      <%}
      else{%>
        Game over. Your score is <%=gf.getScore()%>
      <%}%>


    </h2>
  </div>
  <div id="gamefield">
      <%for (int i = 0; i < 4; i++) {%>
      <div class="row">
        <%for (int j = 0; j < 4; j++) {%>
        <div class="cell" id="val<%=gf.getValues()[i][j]%>">
        <%=gf.getValues()[i][j] == 0 ? "" : gf.getValues()[i][j]%>
        </div>
        <%}%>
      </div>
      <%}%>
  </div>
  <form method="post" action="/game/new" id="newgameform">
    <input type="submit" value="New Game" id="newgamebutton">
  </form>
</div>

</body>
</html>