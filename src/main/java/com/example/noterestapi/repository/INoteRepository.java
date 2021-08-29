package com.example.noterestapi.repository;

import com.example.noterestapi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INoteRepository extends JpaRepository<Note,Long> {
}
