package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnection;

public class Bill {

	public Bill() {

	}

	// Billing Table
	public String readBills() {
		String out = "";
		Connection connection = DBConnection.getConnection();
		out = "<table border='1'><tr><th>Customer Name</th><th>Account No</th><th>Date</th><th>units</th><th>totalPrice</th><th>Update</th><th>Remove</th></tr>";

		String query = "SELECT * FROM billing";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String billid = Integer.toString(resultSet.getInt("billid"));
				String cusName = resultSet.getString("cusName");
				String cusAcc = resultSet.getString("cusAcc");
				String cusDate = resultSet.getString("cusDate");
				String units = resultSet.getString("units");
				String totalPrice = resultSet.getString("totalPrice");

				out += "<tr>";

				out += "<td><input type='hidden' id='hiddenbilIDUpdate' name='hiddenbilIDUpdate' value='" + billid
						+ "'/>"+cusName+"</td>";
//				out += "<td>" + cusName + "</td>";
				out += "<td>" + cusAcc + "</td>";
				out += "<td>" + cusDate + "</td>";
				out += "<td>" + units + "</td>";
				out += "<td>" + totalPrice + "</td>";

				out += "<td><input id='btnUpdate' name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"

						+ "<td><input id='btnRemove' name='btnRemove' type='button' value='Remove' class='btn btn-danger' data-billid='"
						+ billid + "'>";
				
				out += "</td>";
				out += "</tr>";
				
			}
			

			out += "</table>";
			connection.close();
		} catch (Exception e) {
			out = "Reading Interrupted by Error! ";
			System.err.println(e.getMessage());
		}
		
		return out;
	}
	
	public String insertBills(String cusName,String cusAcc,String cusDate, String units, String totalPrice ) {
		String out="";
		Connection connection = DBConnection.getConnection();
		String query = "INSERT INTO `billing`" + "(`cusName`,`cusAcc`,`cusDate`,`units`,`totalPrice`)"
				+ "values (?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, cusName);
			preparedStatement.setString(2, cusAcc);
			preparedStatement.setString(3, cusDate);
			preparedStatement.setString(4, units);
			preparedStatement.setString(5, totalPrice);

			preparedStatement.execute();
			connection.close();
			String newBills = readBills();
			out="{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
		} catch (Exception e) {
			out="{\"status\":\"error\", \"data\": \"Error While Inserting The Bill!\"}";
			System.err.println(e.getMessage());
		}
		return out;
	}
	
	public String updateBills(String billid,String cusName,String cusAcc,String cusDate, String units, String totalPrice  ) {
		String out="";
		Connection connection = DBConnection.getConnection();
		String query = "UPDATE `billing` SET"
				+ " `cusName` = ?, `cusAcc` = ?, `cusDate` = ? , `units` = ?, `totalPrice` = ?" + "WHERE billid = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, cusName);
			preparedStatement.setString(2, cusAcc);
			preparedStatement.setString(3, cusDate);
			preparedStatement.setString(4, units);
			preparedStatement.setString(5, totalPrice);

			preparedStatement.setInt(6, Integer.parseInt(billid));
			preparedStatement.execute();
			connection.close();
			String updatedBills = readBills();
			out="{\"status\":\"success\", \"data\": \"" + updatedBills + "\"}";
		} catch (Exception e) {
			out="{\"status\":\"error\", \"data\": \"Error While Updating Bill ID = "+billid+"!\"}";
			System.err.println(e.getMessage());
		}
		return out;
		
	}
	
	public String deleteBill(String billid) {
		String out="";
		Connection connection = DBConnection.getConnection();
	
		String query = "DELETE FROM billing WHERE billid = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(billid));
			preparedStatement.execute();
			connection.close();
			String remainBills = readBills();
			out="{\"status\":\"success\", \"data\": \"" + remainBills + "\"}";
		} catch (Exception e) {
			out="{\"status\":\"error\", \"data\": \"Error While Deleting Bill ID = "+billid+"!\"}";
			System.err.println(e.getMessage());
		}
		return out;
	}
}
