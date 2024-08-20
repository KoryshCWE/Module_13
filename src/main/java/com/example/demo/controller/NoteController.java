package com.example.demo.controller;

import com.example.demo.Note;
import com.example.demo.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // List all notes
    @GetMapping
    public List<Note> listAll() {
        return noteService.listAll();
    }

    // Get a note by ID
    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable long id) {
        Optional<Note> note = Optional.ofNullable(noteService.getById(id));
        return note.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new note
    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note note) {
        Note createdNote = noteService.add(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    // Update an existing note
    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable long id, @RequestBody Note noteDetails) {
        return noteService.getById(id) != null ?
                ResponseEntity.ok(noteService.update(noteDetails)) :
                ResponseEntity.notFound().build();
    }

    // Delete a note by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        try {
            noteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
