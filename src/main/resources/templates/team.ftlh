<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Team</title>
    <script>
        function openCloseAddUser() {
            document.getElementById("add-user").hidden = !document.getElementById("add-user").hidden;
        }
    </script>
</head>
<body>
<div>
    <input type="button" value="Add user" id="add-user-button" onclick="openCloseAddUser();">
    <div id="add-user" hidden="true">
        <form action="/team-add-user" method="post">
            <input type="text" placeholder="username" name="username" id="username">
            <input type="submit" value="Add user" id="add-user-submit">
        </form>
    </div>
</div>
</body>
</html>