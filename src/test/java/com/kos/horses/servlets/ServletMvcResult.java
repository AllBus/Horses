package com.kos.horses.servlets;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ServletMvcResult implements MvcResult {

    private final MockHttpServletRequest mockRequest;
    private final MockHttpServletResponse mockResponse;

    public ServletMvcResult(MockHttpServletRequest mockRequest, MockHttpServletResponse mockResponse) {
        this.mockRequest = mockRequest;
        this.mockResponse = mockResponse;
    }

    @Override
    public MockHttpServletRequest getRequest() {
        return mockRequest;
    }

    @Override
    public MockHttpServletResponse getResponse() {
        return mockResponse;
    }

    @Override
    public Object getHandler() {
        return null;
    }

    @Override
    public HandlerInterceptor[] getInterceptors() {
        return new HandlerInterceptor[0];
    }

    @Override
    public ModelAndView getModelAndView() {
        return null;
    }

    @Override
    public Exception getResolvedException() {
        return null;
    }

    @Override
    public FlashMap getFlashMap() {
        return null;
    }

    @Override
    public Object getAsyncResult() {
        return null;
    }

    @Override
    public Object getAsyncResult(long l) {
        return null;
    }
}
