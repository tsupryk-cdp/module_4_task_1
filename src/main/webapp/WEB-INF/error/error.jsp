
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<center><h2>Some errors occured</h2></center>
<p></p>
<p>
    <c:if test="${not empty exception.message}">
        <center>
            <h3>Message: ${exception.message}</h3>
        <center>
    </c:if>
</p>

</body>
</html>
