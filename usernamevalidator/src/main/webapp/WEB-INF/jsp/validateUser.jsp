<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Username Validator</title>
</head>
<body>
	<form method="post">
		Name : <input type="text" name="name"/> 
		<br>
		<font color="red">${errorMessage}</font>
		<font color="green">${nameAccepted}</font>
		<br>
		<input value="Validate" type="submit"/>
		
		<c:if test="${!empty(suggestedNamesList)}">

			<H1>Suggested User Names</H1>
			<table>
				<thead>
					<tr>
						<th>User Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="suggestedName" items="${suggestedNamesList}">
						<tr>
							<td><c:out value="${suggestedName}" /> </td>
						</tr>				
					</c:forEach>
				</tbody>			
			</table>
		</c:if>		
	</form>
</body>
</html>