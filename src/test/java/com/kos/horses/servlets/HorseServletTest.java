package com.kos.horses.servlets;

import com.kos.horses.controllers.HorseControllerTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static com.kos.horses.servlets.HorseServletTestUtils.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HorseServletTest extends HorseControllerTestUtils {

    @Override
    public String api() {
        return "/hourse/servlet/count";
    }

    @Test
    public void count() throws Exception {

        for (HorseControllerTestUtils.QueryParamsTest value : successArgumentList) {
            perform(createRequest(value))
                    .andExpect(status().isOk()).andExpect(content().string(value.result));
        }
    }

    @Test
    public void countOutBoardBounds() throws Exception {

        for (QueryParamsTest value : outboundArgumentList) {
            try {
                perform(createRequest(value))
                        .andExpect(status().is5xxServerError());
                Assert.fail("No exception");
            } catch (IndexOutOfBoundsException e) {
                //ok
            }
        }
    }

    @Test
    public void countErrorSize() throws Exception {

        for (QueryParamsTest value : errorSizeArgumentList) {
            perform(createRequest(value))
                    .andExpect(status().isBadRequest());
        }
    }

    @Test
    public void countEmptyQueryParams() throws Exception {
        perform(new MockHttpServletRequest())
                .andExpect(status().isBadRequest());
    }
}