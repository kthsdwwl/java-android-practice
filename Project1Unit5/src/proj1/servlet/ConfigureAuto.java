package proj1.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import proj1.client.SocketIO;
import proj1.model.Automobile;

/**
 * Servlet implementation class ConfigureAuto
 */
public class ConfigureAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get automobile object according to parameter "modelName"
		SocketIO socketIO = new SocketIO("localhost", 8888);
		String modelName = request.getParameter("modelName");
		Automobile auto = socketIO.getModel(modelName);
		ArrayList<String> optionSetList = auto.getOptionSetList();
		
		// pass the automobile object to JSP and let it show the form.
		request.setAttribute("auto", auto);
		request.setAttribute("optionSetList", optionSetList);
		request.getRequestDispatcher("/ConfigureAuto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get automobile object according to parameter "modelName"
		SocketIO socketIO = new SocketIO("localhost", 8888);
		String modelName = request.getParameter("modelName");
		Automobile auto = socketIO.getModel(modelName);
		ArrayList<String> optionSetList = auto.getOptionSetList();
		
		// set option choices for automobile object
		for (String optionSetName : optionSetList) {
			String choice = request.getParameter(optionSetName);
			auto.setOptionChoice(optionSetName, choice);
		}
		
		// pass the automobile object to JSP and let it show the choices.
		request.setAttribute("auto", auto);
		request.setAttribute("optionSetList", optionSetList);
		request.getRequestDispatcher("/Choices.jsp").forward(request, response);
	}

}
