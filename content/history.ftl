<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>History</title>
</head>
<body>
<table border="1">
    <thead>

    <td>username</td>
    <td>x</td>
    <td>operation</td>
    <td>y</td>
    <td>result</td>

    </thead>
    <tbody>
    <#list history as hs>
        <tr>
            <td>${hs.username}</td>
            <td>${hs.x}</td>
            <td>${hs.op}</td>
            <td>${hs.y}</td>
            <td>${hs.result}</td>
        </tr>
    </#list>
    </tbody>
</table>
<a href="/calc/do"> go to Calculator</a>
</body>
</body>
</html>