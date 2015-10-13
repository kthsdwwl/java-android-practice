package proj1.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import proj1.client.SocketIO;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DATA_DIRECTORY = "data";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Upload.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SocketIO socketIO = new SocketIO("localhost", 8888);
		FileInputStream fis = readFile(request);
		socketIO.sendProperties(fis);
		response.sendRedirect("Index");
	}
	
	/**
	 * Upload file to server
	 */
	@SuppressWarnings("rawtypes")
	private FileInputStream readFile(HttpServletRequest request) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) return null;
		
		FileInputStream fis = null;
		
		// create a factory for disk-based file items
	    DiskFileItemFactory factory = new DiskFileItemFactory();
		 
	    // constructs the folder where uploaded file will be stored
		String uploadFolder = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;
	
		// create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// parse the request
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					// construct file path to store the file
					String filename = new File(item.getName()).getName();
					String filePath = uploadFolder + File.separator + filename;
					
					// saves the file to upload directory
					File uploadedFile = new File(filePath);
					item.write(uploadedFile);
					
					// get file input stream
					System.out.println(uploadedFile.getPath());
					fis = new FileInputStream(filePath);
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fis;
	}

}
