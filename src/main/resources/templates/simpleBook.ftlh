<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>${book.getShortName()}</title>
    <script>

    </script>
</head>
<body>
    <div>
        <div>
            <#list textsBefore as list>
                <tr>
                    <td>${list.text}</td>
                    <td>${list.owner}</td>
                </tr>
            </#list>
        </div>
        <div>
            <label>
                <input type="text" value=${textEqual.text}>
            </label>
        </div>
        <div>
            <#list textsAfter as list>
                <tr>
                    <td>${list.text}</td>
                    <td>${list.owner}</td>
                </tr>
            </#list>
        </div>
    </div>
</body>
</html>