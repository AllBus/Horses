package com.kos.horses.controllers;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(HorseController.class)
public class HorseControllerTest extends HorseControllerTestUtils {

    @Autowired
    private MockMvc mockMvc;


    @Override
    public String api() {
        return "/hourse/rest/count";
    }

    @Test
    public void count() throws Exception {

        for (QueryParamsTest value : successArgumentList) {
            this.mockMvc.perform(get(createQuery(value.width, value.height, value.start, value.end)))
                    .andExpect(status().isOk()).andExpect(content().string(value.result));
        }
    }

    @Test
    public void countOutBoardBounds() throws Exception {

        for (QueryParamsTest value : outboundArgumentList) {
            Assertions.assertThatThrownBy(() ->
                    this.mockMvc.perform(get(createQuery(value.width, value.height, value.start, value.end)))
                            .andExpect(status().is5xxServerError())
            ).hasCauseInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    @Test
    public void countErrorSize() throws Exception {

        for (QueryParamsTest value : errorSizeArgumentList) {
            this.mockMvc.perform(get(createQuery(value.width, value.height, value.start, value.end)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Test
    public void countEmptyQueryParams() throws Exception {
        this.mockMvc.perform(get(api()))
                    .andExpect(status().isBadRequest());
    }


}