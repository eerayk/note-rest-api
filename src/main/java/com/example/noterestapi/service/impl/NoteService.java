package com.example.noterestapi.service.impl;

import com.example.noterestapi.dto.NoteDto;
import com.example.noterestapi.dto.ResponseDto;
import com.example.noterestapi.model.Note;
import com.example.noterestapi.model.User;
import com.example.noterestapi.repository.INoteRepository;
import com.example.noterestapi.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {

    @Autowired
    private INoteRepository noteRepository;

    @Autowired
    private UserService userService;

    /**
     * This method creates a note in the database.
     *
     * @param noteDto NoteDto instance to transform Note object and then save.
     * @return NoteDto Created note object.
     */
    @Override
    public NoteDto addNote(NoteDto noteDto) {
        User user = userService.getUserById(noteDto.getUserId());
        if (user != null) {
            Date now = new Date();
            Note note = new Note(user, noteDto.getTitle(), noteDto.getContent(), now, now);
            this.noteRepository.save(note);
            return new NoteDto(false, note);
        } else {
            return new NoteDto(true, "An error occured while adding new note.");
        }
    }

    /**
     * This method updates note in database.
     *
     * @param noteDto The updated note data.
     * @return ResponseDto Returns the result of the operation
     */
    @Override
    public ResponseDto updateNote(NoteDto noteDto) {
        Note noteToUpdate = noteRepository.findById(noteDto.getNoteId()).orElse(null);
        if(noteToUpdate != null){
            noteToUpdate.setLastModifyDate(new Date());
            noteToUpdate.setTitle(noteDto.getTitle());
            noteToUpdate.setContent(noteDto.getContent());
            this.noteRepository.save(noteToUpdate);
            return new ResponseDto(false, "Note updated successfully.");
        }else{
            return new ResponseDto(true, "Can not find selected note to update.");
        }
    }

    /**
     * This method deletes the note with given id from the database.
     *
     * @param noteId The id of the note to delete.
     */
    @Override
    public void deleteNote(Long noteId) {
        this.noteRepository.deleteById(noteId);
    }

    /**
     * This method returns all the notes.
     *
     * @return List<Note> All note records.
     */
    @Override
    public List<Note> getAllNotes() {
        return this.noteRepository.findAll();
    }


    /**
     * This method returns all notes of the user.
     *
     * @param userId The id of the user which we are trying to get notes.
     * @return List<Note> The notes of the user.
     */
    @Override
    public List<Note> getAllNotesOfUser(Long userId) {
        return getAllNotes().stream()
                .filter(note -> note.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

}
