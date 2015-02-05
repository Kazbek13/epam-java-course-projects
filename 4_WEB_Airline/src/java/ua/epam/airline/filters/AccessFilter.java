package ua.epam.airline.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.epam.airline.model.entities.Employee;

/**
 *
 * @author Andrew
 */
public class AccessFilter implements Filter {
    
    public AccessFilter() {
    }    
   
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String uri = httpRequest.getRequestURI();
        
        if (session.getAttribute("employee") != null) {
            Employee emp = (Employee) session.getAttribute("employee");
            
            // limiting dispatcher access
            if (emp.getPosition().equals("dispatcher") && (uri.contains("FlightController") 
                                                        || uri.contains("aircrafts")
                                                        || uri.contains("AircraftController")
                                                        || uri.contains("flightPersonnel")
                                                        || uri.contains("EmployeeController")
                                                        || uri.contains("flights")
                                                        || uri.contains("destinations")
                                                        || uri.contains("AirportController"))) {
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/authorizationError.jsp");
                rd.forward(httpRequest, httpResponse);
            }
            // limiting admin access
            else if (emp.getPosition().equals("admin") && (uri.contains("ShuttleFlightsController") 
                                                        || uri.contains("shuttleFlights"))) {
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/authorizationError.jsp");
                rd.forward(httpRequest, httpResponse);
            }
            else {
                chain.doFilter(request, response);
            }
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/login.jsp");
            rd.forward(httpRequest, httpResponse);
        }
        
    }

    @Override
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }
    
}
