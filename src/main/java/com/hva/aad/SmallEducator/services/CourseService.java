package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.CourseDao;
import com.hva.aad.SmallEducator.dao.CourseStudentDao;
import com.hva.aad.SmallEducator.dao.StudentDao;
import com.hva.aad.SmallEducator.dao.TeacherDao;
import com.hva.aad.SmallEducator.models.CourseModel;
import com.hva.aad.SmallEducator.models.TeacherModel;
import com.hva.aad.SmallEducator.requestmodels.CourseStudentAPIModel;
import com.hva.aad.SmallEducator.models.CourseStudentModel;
import com.hva.aad.SmallEducator.models.StudentModel;
import com.hva.aad.SmallEducator.requestmodels.CreateCourseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for the course property to communicate with the repository.
 *
 * @author Burak Inan & Tijmen Stor
 */
@Service
public class CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final CourseStudentDao courseStudentDao;
    private final TeacherDao teacherDao;

    @Autowired
    public CourseService(final CourseDao courseDao, final StudentDao studentDao, final CourseStudentDao courseStudentDao, final TeacherDao teacherDao) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.courseStudentDao = courseStudentDao;
        this.teacherDao = teacherDao;
    }

    public ResponseEntity<?> createCourse(final CreateCourseModel courseModel) {
        if (courseModel.getCourseName() == null ||
                courseModel.getCourseCode() == null ||
                courseModel.getCourseDescription() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        } else {
            if (courseDao.existsByCourseCode(courseModel.getCourseCode())) {
                return new ResponseEntity<>("Course Code already exists.", HttpStatus.CONFLICT);
            } else {
                final Optional<TeacherModel> teacherModel = teacherDao.findById(courseModel.getTeacherId());
                if (!teacherModel.isPresent()) {
                    return new ResponseEntity<>("Server failed to retrieve information.", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                final CourseModel newCourse = new CourseModel(0, courseModel.getCourseName(),
                        courseModel.getCourseCode(), courseModel.getCourseDescription(),
                        teacherModel.get(), null);
                courseDao.save(newCourse);
                return new ResponseEntity<>(newCourse.getId(), HttpStatus.OK);
            }
        }
    }

    public ResponseEntity<?> addStudentToCourse(final CourseStudentAPIModel courseStudentAPIModel) {
        if (courseStudentAPIModel.getCourseId() == 0 || courseStudentAPIModel.getStudentId() == 0) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        } else {
            if (!studentDao.existsById(courseStudentAPIModel.getStudentId())) {
                return new ResponseEntity<>("Student not found.", HttpStatus.NOT_FOUND);
            } else {
                if (!courseDao.existsById(courseStudentAPIModel.getCourseId())) {
                    return new ResponseEntity<>("Course not found.", HttpStatus.NOT_FOUND);
                } else {
                    if (courseStudentDao.existsByCourse_IdAndStudent_Id(courseStudentAPIModel.getCourseId(),
                            courseStudentAPIModel.getStudentId())) {
                        return new ResponseEntity<>("Student already belongs to the course", HttpStatus.CONFLICT);
                    } else {
                        final Optional<CourseModel> courseModel = courseDao.findById(courseStudentAPIModel.getCourseId());
                        final Optional<StudentModel> studentModel = studentDao.findById(courseStudentAPIModel.getStudentId());

                        if (!courseModel.isPresent() || !studentModel.isPresent()) {
                            return new ResponseEntity<>("Server failed to retrieve information.", HttpStatus.INTERNAL_SERVER_ERROR);
                        }

                        final CourseStudentModel newCourseStudentModel = new CourseStudentModel(0, studentModel.get(), courseModel.get(), UUID.randomUUID().toString());
                        courseStudentDao.save(newCourseStudentModel);
                        return new ResponseEntity<>(newCourseStudentModel.getStudentEntryCode(), HttpStatus.OK);

                    }
                }
            }
        }
    }
}
