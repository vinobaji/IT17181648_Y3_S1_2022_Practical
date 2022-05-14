package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bill;

/**
 * Servlet implementation class BillAPI
 */
@WebServlet("/BillAPI")
public class BillAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Bill bill = new Bill();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BillAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	private static Map getParamtersToMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");

			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
			System.out.print("HASHMAP ERROR:-");
			System.out.println(e.getMessage());
		}

		return map;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cusName = request.getParameter("cusName");
		String cusAcc = request.getParameter("cusAcc");
		String cusDate = request.getParameter("cusDate");
		String units = request.getParameter("units");
		String totalPrice = request.getParameter("totalPrice");
		String out = bill.insertBills(cusName, cusAcc, cusDate, units, totalPrice);
//		System.out.println(" IO:_ "+out);
		response.getWriter().write(out);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		Map parameter = getParamtersToMap(request);

		String billid = parameter.get("billid").toString();
		String cusName = parameter.get("cusName").toString();
		String cusAcc = parameter.get("cusAcc").toString();
		String cusDate = parameter.get("cusDate").toString();
		String units = parameter.get("units").toString();
		String totalPrice = parameter.get("totalPrice").toString();
		String out = bill.updateBills(billid, cusName, cusAcc, cusDate, units, totalPrice);

		parameter.clear();
		response.getWriter().write(out);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		@SuppressWarnings("rawtypes")
		Map parameter = getParamtersToMap(request);

		String billid = parameter.get("billid").toString();
		String out = bill.deleteBill(billid);
		
		parameter.clear();
		response.getWriter().write(out);
	}

}