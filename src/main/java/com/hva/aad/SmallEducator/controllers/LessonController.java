package com.hva.aad.SmallEducator.controllers;

import com.hva.aad.SmallEducator.models.request.CreateLessonRequestModel;
import com.hva.aad.SmallEducator.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that receives all the HTTP requests on the lesson property.
 *
 * @author Tijmen Stor
 */
@RestController
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping(value = "/lesson")
    public ResponseEntity<?> createLesson(@RequestBody CreateLessonRequestModel createLessonRequestModel) {
        return lessonService.createLesson(createLessonRequestModel);
    }

    @GetMapping(value = "/lesson/{lessonId}")
    public ResponseEntity<?> getLesson(@PathVariable int lessonId) {
        return lessonService.getLesson(lessonId);
    }

    @DeleteMapping(value = "/lesson/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable int lessonId) {
        return lessonService.deleteLesson(lessonId);
    }

}
