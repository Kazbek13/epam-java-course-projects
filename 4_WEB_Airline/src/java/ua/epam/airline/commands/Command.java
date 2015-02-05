package ua.epam.airline.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrew
 */
public interface Command {
    public void execute(HttpServletRequest request, HttpServletResponse response);
}
