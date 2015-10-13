<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList, proj1.model.Automobile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
</head>
<body>
	<h2>Here is what you selected</h2>
	<% Automobile auto = (Automobile) request.getAttribute("auto"); %>
	<% ArrayList<String> optionSetList = (ArrayList<String>) request.getAttribute("optionSetList"); %>
	<table border="1" style="width:50%">
		<tr>
			<td><strong>Model Name</strong></td>
			<td>--</td>
			<td><%=auto.getName() %></td>
		</tr>
		<tr>
			<td><strong>Base Price</strong></td>
			<td>--</td>
			<td>$<%=auto.getBaseprice() %></td>
		</tr>
		<% for (String optionSet : optionSetList) { %>
		<tr>
			<td><strong><%=optionSet %></strong></td>
			<td><%=auto.getOptionChoice(optionSet) %></td>
			<td>$<%=auto.getOptionChoicePrice(optionSet) %></td>
		</tr>
		<% } %>
		<tr>
			<td><strong>Total Cost</strong></td>
			<td></td>
			<td><strong>$<%=auto.getTotalPrice() %></strong></td>
		</tr>
	</table>
</body>
</html>