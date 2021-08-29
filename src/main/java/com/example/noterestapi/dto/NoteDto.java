package com.example.noterestapi.dto;

import com.example.noterestapi.model.Note;

public class NoteDto extends ResponseDto {

    private Long noteId;
    private Long userId;
    private String title;
    private String content;
    private java.util.Date createDate;
    private java.util.Date lastModifyDate;

    public NoteDto(){}

    public NoteDto(Boolean error, String message){
        super(error, message);
    }

    public NoteDto(Boolean error, Note note) {
        super(error);
        this.noteId = note.getId();
        this.userId = note.getUser().getId();
        this.title = note.getTitle();
        this.content = note.getContent();
        this.createDate = note.getCreateDate();
        this.lastModifyDate = note.getLastModifyDate();
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public java.util.Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(java.util.Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}
