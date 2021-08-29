package com.example.noterestapi.dto;

public class UserDto extends ResponseDto {
    private Long userId;
    private String username;

    public UserDto(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public UserDto(Boolean success, Long userId, String username) {
        super(success);
        this.userId = userId;
        this.username = username;
    }

    public UserDto(Boolean success, String message, Long userId, String username) {
        super(success, message);
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
