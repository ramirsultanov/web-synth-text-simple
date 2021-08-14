<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script>

    </script>
</head>
<body>
<div>
    <form action="/login" method="post">
        <input type="text" placeholder="username" name="username" id="username">
        <input type="password" placeholder="password" name="password" id="password">
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>