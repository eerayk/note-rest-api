package com.example.noterestapi.service;

import com.example.noterestapi.dto.NoteDto;
import com.example.noterestapi.dto.ResponseDto;
import com.example.noterestapi.model.Note;

import java.util.List;

public interface INoteService {
    NoteDto addNote(NoteDto noteDto);
    ResponseDto updateNote(NoteDto noteDto);
    void deleteNote(Long noteId);
    List<Note> getAllNotes();
    List<Note> getAllNotesOfUser(Long userId);
}
