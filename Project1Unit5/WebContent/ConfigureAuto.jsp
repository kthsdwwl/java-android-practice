<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList, proj1.model.Automobile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Configuration Page</title>
</head>
<body>
	<h2>Basic Car Choice</h2>
	<form method="post" action="ConfigureAuto">
	<% Automobile auto = (Automobile) request.getAttribute("auto"); %>
	<% ArrayList<String> optionSetList = (ArrayList<String>) request.getAttribute("optionSetList"); %>
		<table border="1" style="width:50%">
			<tr>
				<td><strong>Model Name</strong></td>
				<td><%=auto.getName() %></td>
			</tr>
			<tr>
				<td><strong>Base Price</strong></td>
				<td>$<%=auto.getBaseprice() %></td>
			</tr>
			<% for (String optionSet : optionSetList) { %>
			<tr>
				<td><strong><%=optionSet %></strong></td>
				<td>
					<select name=<%=optionSet %>>
					<% ArrayList<String> optionList = auto.getOptionList(optionSet); %>
					<% for (String option : optionList) { %>
						<option value="<%=option %>"><%=option %></option>
					<% } %>
					</select>
				</td>
			</tr>
			<% } %>
		</table>
		<input type="hidden" name="modelName" value="<%=auto.getName() %>"/>
		<input type="submit" value="Done"/>
	</form>
</body>
</html>