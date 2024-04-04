package Controller;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Registration;

@WebServlet(name = "search", urlPatterns = {"/search"})
public class search extends HttpServlet {
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Registration u = new Registration(session);
        try {
            if(session.getAttribute("id") != null && session.getAttribute("id").equals("1")){
            String id = request.getParameter("id");
            //request.getRequestDispatcher("Search.jsp?id=" + id).forward(request, response);
	         response.sendRedirect("Search.jsp?id="+id);            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    
    
    
  
    

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


