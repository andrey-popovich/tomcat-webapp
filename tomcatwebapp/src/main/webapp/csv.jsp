<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CSV</title>
</head>
<body>
<h2>Available CSV files:</h2>
<c:forEach var="fileInfo" items="${fileInfoList}">
    <h3>Filename: ${fileInfo.name}</h3>
    <span>Number of rows: ${fileInfo.numberOfRows}</span>
    <ul>Columns:
        <c:forEach var="column" items="${fileInfo.columns}">
            <li>${column}</li>
        </c:forEach>
    </ul>
    <ul>Columns empty values:
        <c:forEach var="key" items="${fileInfo.emptyValuesInColumn}">
            <li>${key}</li>
        </c:forEach>
    </ul>
    <br>
</c:forEach>
</body>
</html>
