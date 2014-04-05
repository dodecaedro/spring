<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Customers</title>
</head>
<body>
  <div>
    <h3>Customer</h3>
  </div>
  <div>
    <c:if test="${empty customer}">Customer not found</c:if>
    <c:if test="${not empty customer}">
    Id: <c:out value="${customer.customerId}"/> </br>
    First name: <c:out value="${customer.firstName}"/> </br>
    Last name: <c:out value="${customer.lastName}"/> </br>
    Address: <c:out value="${customer.address}"/> </br>
    Phone: <c:out value="${customer.phoneNumber}"/> </br>
    </c:if>
  </div>
</body>
</html>