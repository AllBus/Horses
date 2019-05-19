package com.kos.horses.servlets;


import com.kos.horses.controllers.HorseControllerTestUtils;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.servlet.ServletException;
import java.io.IOException;

public class HorseServletTestUtils {

    public static MockHttpServletRequest createRequest(HorseControllerTestUtils.QueryParamsTest value) {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.GET.name());

        request.addParameter("width", String.valueOf(value.width));
        request.addParameter("height", String.valueOf(value.height));
        request.addParameter("start", String.valueOf(value.start));
        request.addParameter("end", String.valueOf(value.end));

        return request;
    }

    public static ResultActions perform(MockHttpServletRequest request) throws ServletException, IOException {
        HorseServlet servlet = new HorseServlet();
        MockHttpServletResponse response = new MockHttpServletResponse();
        servlet.doGet(request, response);

        MvcResult mvcResult1 = new ServletMvcResult(request, response);

        return new ResultActions() {
            final MvcResult mvcResult = mvcResult1;

            public ResultActions andExpect(ResultMatcher matcher) throws Exception {
                matcher.match(mvcResult);
                return this;
            }

            public ResultActions andDo(ResultHandler handler) throws Exception {
                handler.handle(mvcResult);
                return this;
            }

            public MvcResult andReturn() {
                return mvcResult;
            }
        };

    }
}
