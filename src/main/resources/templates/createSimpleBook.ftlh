<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Explore world</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="../static/css/index/index.css" th:href="@{/css/index/index.scss}" />
    <script>

    </script>
</head>
<body>
    <div>
        <form action="/book" method="post">
            <input type="text" placeholder="name" name="name" id="name">
            <input type="submit" value="Create">
        </form>
    </div>
</body>
</html>