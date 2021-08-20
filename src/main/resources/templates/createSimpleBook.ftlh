<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <script>

    </script>
</head>
<body>
<div>
    <form action="/createBook" method="post">
        <label for="name"></label><input type="text" placeholder="name" name="name" id="name">
        <input type="text" hidden="true">
        <input type="submit" value="Create">
    </form>
    <input type="checkbox" name="check" id="name" value="Add users">
</div>
</body>
</html>