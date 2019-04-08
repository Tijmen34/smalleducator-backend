package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.CourseDao;
import com.hva.aad.SmallEducator.dao.DialogDao;
import com.hva.aad.SmallEducator.dao.FlashcardDao;
import com.hva.aad.SmallEducator.dao.LessonDao;
import com.hva.aad.SmallEducator.models.Dialog;
import com.hva.aad.SmallEducator.models.Flashcard;
import com.hva.aad.SmallEducator.models.Lesson;
import com.hva.aad.SmallEducator.models.request.CreateLessonRequestModel;
import com.hva.aad.SmallEducator.models.response.LessonResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for the lesson property to communicate with the repository.
 *
 * @author Tijmen Stor
 */
@Service
public class LessonService {

    private final LessonDao lessonDao;
    private final CourseDao courseDao;
    private final FlashcardDao flashcardDao;
    private final DialogDao dialogDao;

    @Autowired
    public LessonService(LessonDao lessonDao, CourseDao courseDao, FlashcardDao flashcardDao, DialogDao dialogDao) {
        this.lessonDao = lessonDao;
        this.courseDao = courseDao;
        this.flashcardDao = flashcardDao;
        this.dialogDao = dialogDao;
    }


    public ResponseEntity<?> createLesson(CreateLessonRequestModel createLessonRequestModel) {
        if (createLessonRequestModel.getCourseId() == 0 || createLessonRequestModel.getLessonName() == null ||
            createLessonRequestModel.getLessonDescription() == null) {
            return new ResponseEntity<>("Incomplete request", HttpStatus.BAD_REQUEST);
        }
        if (!courseDao.existsById(createLessonRequestModel.getCourseId())) {
            return new ResponseEntity<>("Course does not exist.", HttpStatus.NOT_FOUND);
        }

        Lesson lesson = Lesson.builder()
                .courseId(createLessonRequestModel.getCourseId())
                .lessonName(createLessonRequestModel.getLessonName())
                .lessonDescription(createLessonRequestModel.getLessonDescription())
                .build();

        Integer newLessonId = lessonDao.createLesson(lesson);

        return new ResponseEntity<>(newLessonId, HttpStatus.OK);
    }

    public ResponseEntity<?> getLesson(int lessonId) {
        if (!lessonDao.existsById(lessonId)) {
            return new ResponseEntity<>("Lesson does not exist.", HttpStatus.NOT_FOUND);
        }
        Lesson currLesson = lessonDao.findById(lessonId);
        List<Flashcard> flashcardList = flashcardDao.findAllByLessonId(lessonId);
        List<Dialog> dialogList = dialogDao.findAllByLessonId(lessonId);

        LessonResponseModel lessonResponseModel = LessonResponseModel.builder()
                .id(currLesson.getId())
                .lessonName(currLesson.getLessonName())
                .lessonDescription(currLesson.getLessonDescription())
                .courseId(currLesson.getCourseId())
                .flashcardList(flashcardList)
                .dialogList(dialogList)
                .build();

        return new ResponseEntity<>(lessonResponseModel, HttpStatus.OK);

    }

    public ResponseEntity<?> deleteLesson(int lessonId) {
        if (!lessonDao.existsById(lessonId)) {
            return new ResponseEntity<>("Lesson not found.", HttpStatus.NOT_FOUND);
        }
        lessonDao.deleteByLessonId(lessonId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
