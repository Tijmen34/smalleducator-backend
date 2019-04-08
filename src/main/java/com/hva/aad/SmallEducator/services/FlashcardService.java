package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.FlashcardDao;
import com.hva.aad.SmallEducator.dao.LessonDao;
import com.hva.aad.SmallEducator.models.Flashcard;
import com.hva.aad.SmallEducator.models.Lesson;
import com.hva.aad.SmallEducator.models.request.CreateFlashcardRequestModel;
import com.hva.aad.SmallEducator.models.request.CreateLessonRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service for the flashcard property to communicate with the repository.
 *
 * @author Tijmen Stor
 */
@Service
public class FlashcardService {

    private final LessonDao lessonDao;
    private final FlashcardDao flashcardDao;

    @Autowired
    public FlashcardService(LessonDao lessonDao, FlashcardDao flashcardDao) {
        this.lessonDao = lessonDao;
        this.flashcardDao = flashcardDao;
    }


    public ResponseEntity<?> createFlashcard(CreateFlashcardRequestModel createFlashcardRequestModel) {
        if (createFlashcardRequestModel.getLessonId() == 0 || createFlashcardRequestModel.getFlashcardName() == null ||
                createFlashcardRequestModel.getFlashcardQuestion() == null || createFlashcardRequestModel.getFlashcardSolution() == null) {
            return new ResponseEntity<>("Incomplete request", HttpStatus.BAD_REQUEST);
        }
        if (!lessonDao.existsById(createFlashcardRequestModel.getLessonId())) {
            return new ResponseEntity<>("Lesson does not exist.", HttpStatus.NOT_FOUND);
        }

        Flashcard flashcard = Flashcard.builder()
                .lessonId(createFlashcardRequestModel.getLessonId())
                .flashcardName(createFlashcardRequestModel.getFlashcardName())
                .flashcardQuestion(createFlashcardRequestModel.getFlashcardQuestion())
                .flashcardSolution(createFlashcardRequestModel.getFlashcardSolution())
                .build();

        Integer newFlashcardId = flashcardDao.createFlashcard(flashcard);

        return new ResponseEntity<>(newFlashcardId, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteFlashcard(int flashcardId) {
        if (!flashcardDao.existsById(flashcardId)) {
            return new ResponseEntity<>("Flashcard not found.", HttpStatus.NOT_FOUND);
        }
        flashcardDao.deleteById(flashcardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getFlashcard(int flashcardId) {
        if (!flashcardDao.existsById(flashcardId)) {
            return new ResponseEntity<>("flashcard not found.", HttpStatus.NOT_FOUND);
        }
        Flashcard flashcard= flashcardDao.findById(flashcardId);
        return new ResponseEntity<>(flashcard, HttpStatus.OK);
    }

}
