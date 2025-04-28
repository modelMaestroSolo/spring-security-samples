package com.example.secure_notes.controllers;

import com.example.secure_notes.models.Note;
import com.example.secure_notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public Note createNote(@RequestBody String content, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        return noteService.createNoteForUser(username, content);
    }

    @GetMapping
    public List<Note> getUserNotes(@AuthenticationPrincipal UserDetails userDetails){
        return noteService.getNotesForUser(userDetails.getUsername());
    }

    @PutMapping("/{noteId}")
    public Note updateNoteForUser(@PathVariable Long noteId, @RequestBody String content, @AuthenticationPrincipal UserDetails userDetails){
        return noteService.updateNoteForUser(noteId, userDetails.getUsername(), content);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNoteForUser(@PathVariable Long noteId, @AuthenticationPrincipal UserDetails userDetails){
        noteService.deleteNoteForUser(noteId, userDetails.getUsername());
    }

}
