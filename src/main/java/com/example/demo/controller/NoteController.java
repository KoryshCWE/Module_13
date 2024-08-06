package com.example.demo.controller;

import com.example.demo.Note;
import com.example.demo.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/note")
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @GetMapping("/list")
    public String listAll(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "noteList";
    }

    @PostMapping("/delete")
    public String deleteById(@RequestParam long id) {
        try {
            noteService.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting note with id " + id, e);
        }
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam long id, Model model) {
        try {
            Note note = noteService.getById(id);
            model.addAttribute("note", note);
        } catch (Exception e) {
            logger.error("Error fetching note with id " + id, e);
        }
        return "noteEdit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Note note) {
        try {
            noteService.update(note);
        } catch (Exception e) {
            logger.error("Error updating note with id " + note.getId(), e);
        }
        return "redirect:/note/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("note", new Note());
        return "noteAdd";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Note note) {
        noteService.add(note);
        return "redirect:/note/list";
    }
}
