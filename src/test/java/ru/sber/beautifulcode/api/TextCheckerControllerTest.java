package ru.sber.beautifulcode.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.sber.beautifulcode.core.TextCheckerService;
import ru.sber.beautifulcode.exception.InvalidArgumentException;
import ru.sber.beautifulcode.util.TestUtils;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TextCheckerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TextCheckerService textCheckerService;

    private static final String TEXT = "text";

    @Test
    void testCheckBracketsForException() throws Exception {
        String errorMessage = "invalid text";
        when(textCheckerService.checkBrackets(TEXT)).thenThrow(new InvalidArgumentException(errorMessage));

        mockMvc.perform(
                post(Paths.API + Paths.CHECK_BRACKETS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.loadFileContent("check-brackets-request.json"))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(errorMessage));

        verify(textCheckerService).checkBrackets(TEXT);
        verifyNoMoreInteractions(textCheckerService);
    }

    @Test
    void testCheckBracketsForSuccess() throws Exception {
        when(textCheckerService.checkBrackets(TEXT)).thenReturn(true);

        mockMvc.perform(
                        post(Paths.API + Paths.CHECK_BRACKETS)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtils.loadFileContent("check-brackets-request.json"))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isCorrect").value(true));

        verify(textCheckerService).checkBrackets(TEXT);
        verifyNoMoreInteractions(textCheckerService);
    }

    @Test
    void testCheckBracketsForFailure() throws Exception {
        when(textCheckerService.checkBrackets(TEXT)).thenReturn(false);

        mockMvc.perform(
                        post(Paths.API + Paths.CHECK_BRACKETS)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtils.loadFileContent("check-brackets-request.json"))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isCorrect").value(false));

        verify(textCheckerService).checkBrackets(TEXT);
        verifyNoMoreInteractions(textCheckerService);
    }
}
