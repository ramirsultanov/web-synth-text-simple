<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Team</title>
    <script>

    </script>
</head>
<body>
<div>
    <form action="/regTeam" method="post">
        <input type="text" placeholder="name" name="name" id="name">
        <input type="submit" value="Register team" id="submit">
    </form>
</div>
</body>
</html>