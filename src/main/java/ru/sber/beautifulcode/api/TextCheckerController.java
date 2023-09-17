package ru.sber.beautifulcode.api;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.beautifulcode.api.error.ErrorMessage;
import ru.sber.beautifulcode.core.TextCheckerService;
import ru.sber.beautifulcode.model.CheckBracketsRequest;
import ru.sber.beautifulcode.model.CheckBracketsResponse;

@RestController
@RequestMapping(Paths.API)
@RequiredArgsConstructor
public class TextCheckerController {

    private final TextCheckerService service;

    @PostMapping(path = Paths.CHECK_BRACKETS, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The check completed",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CheckBracketsResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    public CheckBracketsResponse checkBrackets(@RequestBody CheckBracketsRequest request) {
        return new CheckBracketsResponse(service.checkBrackets(request.getText()));
    }
}
