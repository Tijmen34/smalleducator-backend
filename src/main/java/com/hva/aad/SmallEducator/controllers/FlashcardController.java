package com.hva.aad.SmallEducator.controllers;

import com.hva.aad.SmallEducator.models.request.CreateFlashcardRequestModel;
import com.hva.aad.SmallEducator.services.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that receives all the HTTP requests on the flashcard property.
 *
 * @author Tijmen Stor
 */
@RestController
public class FlashcardController {

    private final FlashcardService flashcardService;

    @Autowired
    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping(value = "/flashcard")
    public ResponseEntity<?> createFlashcard(@RequestBody CreateFlashcardRequestModel createFlashcardRequestModel) {
        return flashcardService.createFlashcard(createFlashcardRequestModel);
    }

    @GetMapping(value = "/flashcard/{flashcardId}")
    public ResponseEntity<?> getLesson(@PathVariable int flashcardId) {
        return flashcardService.getFlashcard(flashcardId);
    }

    @DeleteMapping(value = "/flashcard/{flashcardId}")
    public ResponseEntity<?> deleteFlashcard(@PathVariable int flashcardId) {
        return flashcardService.deleteFlashcard(flashcardId);
    }
}
