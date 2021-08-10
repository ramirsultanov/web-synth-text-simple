<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${book.getShortName()}</title>
    <script>

    </script>
</head>
<body>
    <div>
        <div>
            <#list texts as list>
                <tr>
                    <td>${list.text}</td>
                    <td>${list.owner}</td>
                </tr>
            </#list>
        </div>
        <div>

        </div>
        <div>

        </div>
    </div>
</body>
</html>