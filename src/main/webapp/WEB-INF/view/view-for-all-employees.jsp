<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rizvan
  Date: 28.12.23
  Time: 09:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Information for all Employees</h1>
<br>
<br>
<security:authorize access="hasRole('HR')">
    <input type="button" name="name" value="Salary" onclick="window.location.href='hr_info'"/>
    Only for HR staff
</security:authorize>
<br>
<br>
<security:authorize access="hasRole('MANAGER')">
<input type="button" name="name" value="Performance" onclick="window.location.href='manager_info'"/>
Only for Management staff
</security:authorize>

<br>
<br>
</body>
</html>
