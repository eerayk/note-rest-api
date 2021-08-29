package com.example.noterestapi.controller;

import com.example.noterestapi.dto.NoteDto;
import com.example.noterestapi.dto.ResponseDto;
import com.example.noterestapi.model.Note;
import com.example.noterestapi.service.impl.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/getNotes")
    public ResponseEntity<List<Note>> getNotes() {
        try {
            return ResponseEntity.ok(this.noteService.getAllNotes());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/getNotesOfTheUser")
    public ResponseEntity<List<Note>> getNotesOfTheUser(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(this.noteService.getAllNotesOfUser(userId));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping("/addNote")
    public ResponseEntity<ResponseDto> addNote(@RequestBody NoteDto noteDto) {
        try {
            return ResponseEntity.ok(noteService.addNote(noteDto));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.ok(new ResponseDto(true,"An error occured while adding note to db."));
        }
    }

    @PutMapping("/updateNote")
    public ResponseEntity<ResponseDto> updateNote(@RequestBody NoteDto noteDto) {
        try {
            return ResponseEntity.ok(noteService.updateNote(noteDto));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.ok(new ResponseDto(true,"An error occured while adding note to db."));
        }
    }

    @DeleteMapping("/deleteNote")
    public ResponseEntity<ResponseDto> deleteNote(@RequestParam Long noteId) {
        try {
            noteService.deleteNote(noteId);
            return ResponseEntity.ok(new ResponseDto(false,"Note deleted successfully."));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.ok(new ResponseDto(true,"An error occured while deleting note to db."));
        }
    }
}
