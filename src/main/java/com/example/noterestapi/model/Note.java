package com.example.noterestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition="TEXT")
    private String content;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createDate;

    @Column(name = "last_modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date lastModifyDate;

    public Note() {
    }

    public Note(User user, String title, String content,java.util.Date createDate, java.util.Date lastModifyDate) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastModifyDate = lastModifyDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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
