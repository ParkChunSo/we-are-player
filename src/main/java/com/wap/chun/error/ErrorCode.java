package com.wap.chun.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "Entity Not Found"),
    INVALID_TYPE_VALUE(400, "Invalid Type Value"),
    METHOD_NOT_ALLOWED(405, "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "Server Error"),


    // Member
    MEMBER_CANNOT_FOUND(400, "Can't found member"),
    EMAIL_DUPLICATION(400, "This Email Already Exists"),
    LOGIN_INPUT_INVALID(400, "Login input is invalid"),

    // Club
    CLUB_CANNOT_FOUND(400, "Can't found Club"),
    CLUB_DUPLICATION(400, "This Club already exists"),

    //Club Member
    MEMBER_CANNOT_FOUND_IN_CLUB(400, "Can't found member in club"),

    //Security
    INVALID_TOKEN(403, "invalid JWT token"),
    EXPIRED_TOKEN(403, "Expired JWT token"),
    HANDLE_ACCESS_DENIED(403, "Access is Denied"),
    AUTHORIZATION(403, "No permission"),

    //Match
    MATCH_NOT_FOUND(400, "Can't found match"),

    //Invitation
    INVITATION_NOT_FOUND(400, "Can't found Invitation");
    ;
    private final int code;
    private final String message;

}
