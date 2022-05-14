<?xml version="1.0" encoding="utf-8" ?>
<%@page import="model.Bill"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="refresh" content="15" />

<title>Bill Management</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script type='text/javascript' src='./Components/Bill.js'></script>
</head>

<body>

	<div class="container">
		<h1 class="text-center">Bill Management</h1>
		<div class="row">
			<div class="col">
				<form id="formBil" method="post" action="Bill.jsp">
					<div class="form-group">
						Customer Name:<input class="form-control form-control-sm"
							id="cusName" name="cusName" type="text" required /> <br />
						Account Number:<input class="form-control form-control-sm"
							id="cusAcc" name="cusAcc" type="text" /> <br />
						Bill Date:<input class="form-control form-control-sm"
							id="cusDate" name="cusDate" type="date" required /> <br />
						Used Units: <input class="form-control form-control-sm"
							id="units" name="units" type="text" required /> <br />
						Total Price: <input class="form-control form-control-sm"
							id="totalPrice" name="totalPrice" type="text" required /> <br />
						<input class="btn btn-primary" id="btnSave" name="btnSave"
							type="button" value="Save" /> <input id="billid"
							name="billid" type="hidden" />

					</div>
				</form>



				<div class="alert alert-success" id="alertSuccess"></div>
				<div class="alert alert-danger" id="alertError"></div>
				<br /> <br />
				<div id="divBilGrid">
					<%
						Bill bil = new Bill();
					out.print(bil.readBills());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>