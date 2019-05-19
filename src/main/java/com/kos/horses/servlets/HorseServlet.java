package com.kos.horses.servlets;

import com.kos.horses.problems.horses.HorseProblem;
import com.kos.horses.problems.horses.HorseProblemFactory;
import com.kos.horses.problems.horses.HorseProblemParams;
import com.kos.horses.structures.IProblem;
import com.kos.horses.structures.ISolverResult;
import com.kos.horses.utils.ConvertException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.kos.horses.problems.horses.HorseConstants.NO_SOLUTION_VALUE;


@WebServlet("/hourse/servlet/count")
public class HorseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        findHourseStepsCount(request, response);

    }

    private void findHourseStepsCount(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HorseProblemParams problemParams = readRequestParams(request);

        if (problemParams == null) {
            responseBadRequest(response);
            return;
        }

        IProblem problem = new HorseProblem(problemParams);
        ISolverResult solve = problem.solveProblem();

        if (solve.hasSolution()) {
            responseOk(response, solve.getResult());
        } else
            responseOk(response, NO_SOLUTION_VALUE);
    }

    @Nullable
    private HorseProblemParams readRequestParams(HttpServletRequest request) {
        String widthString = request.getParameter("width");
        String heightString = request.getParameter("height");
        String start = request.getParameter("start");
        String end = request.getParameter("end");

        try {
            return HorseProblemFactory.build(widthString, heightString, start, end);
        } catch (ConvertException | IllegalArgumentException ignored) {

        }
        return null;
    }

    private void responseOk(HttpServletResponse response, long result) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(result);
        }
    }

    private void responseBadRequest(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("Bad arguments");
        }
    }

}
