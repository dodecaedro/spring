<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Customers list</title>
</head>
<body>
  <div>
    <h3>Customers</h3>
  </div>
  <div>
    <c:if test="${empty customers}">No customers found</c:if>
    <c:if test="${not empty customers}">
      <c:forEach var="customer" items="${customers}">
        <ul>
          <li>
            <c:out value="${customer.firstName}"/>
            <c:out value="${customer.lastName}"/>
          </li>
        </ul>
      </c:forEach>
    </c:if>
  </div>
</body>
</html>