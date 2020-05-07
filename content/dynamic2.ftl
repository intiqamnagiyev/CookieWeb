<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <table border="1">
        <tbody>
        <#list students as student>
            <tr>
                <td>${student.name}</td>
                <td>${student.uname()}</td>
                <td>${student.age}</td>
                <td>${student.group}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</body>
</html>
