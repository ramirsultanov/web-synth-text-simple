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
    <form action="/register" method="post">
        <label for="username"></label><input type="text" placeholder="username" name="username" id="username">
        <label for="password"></label><input type="password" placeholder="password" name="password" id="password">
        <input type="submit" value="Register">
    </form>
</div>
</body>
</html>