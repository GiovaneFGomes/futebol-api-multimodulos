package com.giovane.soccer.annotations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.giovane.soccer.annotations.TeamConstantsSwagger.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Team has been updated"),
        @ApiResponse(responseCode = "400", description = "An incorrect request has been sent"),
        @ApiResponse(responseCode = "404", description = "Team's ID does not exist"),
        @ApiResponse(responseCode = "400", description = "Body contains invalid JSON. Min 3 and Max 40 characters"),
        @ApiResponse(responseCode = "400", description = "Body contains invalid JSON. Must not be blank")
})
@Operation(summary = TEAM_PUT_SUMMARY, description = TEAM_PUT_DESCRIPTION)
public @interface TeamPutStandardCodes {

}
