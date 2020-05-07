<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
       <!-- <link rel="stylesheet" href="/static/css/style.css">-->
        <title>Calculator</title>
    </head>

    <body>
        <form method="post" action="/calc/do">
          Number1:  <input type="number" name="x" value="${result.x}"><br>
          Number2:  <input type="number" name="y" value="${result.y}"><br>
            <select name="op">

                <option disabled selected>choose</option>
                <option value="add">ADD</option>
                <option value="sub">SUBTRACT</option>
                <option value="mul">MULTIPLY</option>
                <option value="div">DIVIDE</option>
            </select>
            <br>
            <input type="submit" value="DoCalc"><br>
            <input type="text" placeholder="result" readonly value="${result.result}">
        </form>

        <a href="/calc/history">go to History</a>
    </body>

</html>
