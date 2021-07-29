<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $("#searchText").keyup(function(event) {
            if (event.keyCode === 13) {
                search();
            }
        });

        function search() {
            let queryValue = document.querySelector('#searchText').value;
            $.ajax({
                url: "/search",
                dataType: "json",
                method: "GET",
                    data: {'query': queryValue},
                success: function(data) {
                    updateView(data);
                },
                error: function(error) {
                    alert(error + "- error");
                }
            });
        }

        function updateView(data) {
            document.querySelector("#result").innerHTML = "";
            if (data.text.length !== 0) {
                for (let i = 0; i < data.text.length; i++) {
                    document.querySelector("#result").innerHTML += "<h2>" + data.text[i].text + "</h2>";
                }
            } else {
                document.querySelector("#result").innerHTML = "<h2>No matches</h2>";
            }
        }
    </script>
</head>
<body>
<h1 id="welcome">Search thoughts</h1>
<form id="searchForm" onsubmit="search(); return false;">
    <label for="searchText">Search</label><input type="text" id="searchText" name="searchText" autofocus
                                                 placeholder=
                                                 "Write keywords (for example: truth space life), then press Enter">
</form>
<div id="result"></div>
</body>
</html>