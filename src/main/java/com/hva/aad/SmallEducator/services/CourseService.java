package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.models.request.CourseStudentRequestModel;
import com.hva.aad.SmallEducator.models.request.CreateCourseRequestModel;
import com.hva.aad.SmallEducator.dao.CourseDao;
import com.hva.aad.SmallEducator.dao.CourseStudentDao;
import com.hva.aad.SmallEducator.dao.StudentDao;
import com.hva.aad.SmallEducator.dao.TeacherDao;
import com.hva.aad.SmallEducator.models.Course;
import com.hva.aad.SmallEducator.models.CourseStudent;
import com.hva.aad.SmallEducator.models.Student;
import com.hva.aad.SmallEducator.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for the course property to communicate with the repository.
 *
 * @author Burak Inan & Tijmen Stor
 */
@Service
public class CourseService {

    private final CourseDao courseDAO;
    private final TeacherDao teacherDAO;
    private final StudentDao studentDAO;
    private final CourseStudentDao courseStudentDAO;

    @Autowired
    public CourseService(CourseDao courseDAO, TeacherDao teacherDAO, StudentDao studentDAO, CourseStudentDao courseStudentDAO) {
        this.courseDAO = courseDAO;
        this.teacherDAO = teacherDAO;
        this.studentDAO = studentDAO;
        this.courseStudentDAO = courseStudentDAO;
    }

    public ResponseEntity<?> createCourse(final CreateCourseRequestModel courseModel) {
        if (courseModel.getCourseName() == null || courseModel.getCourseCode() == null
                || courseModel.getCourseDescription() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        }

        if (courseDAO.existsByCourseCode(courseModel.getCourseCode())) {
            return new ResponseEntity<>("Course Code already exists.", HttpStatus.CONFLICT);
        }

        if(!teacherDAO.existsById(courseModel.getTeacherId())) {
            return new ResponseEntity<>("Teacher does not exist.", HttpStatus.BAD_REQUEST);
        }

        Teacher teacher = teacherDAO.findById(courseModel.getTeacherId());

        Course newCourse = Course.builder()
                .courseName(courseModel.getCourseName())
                .courseDescription(courseModel.getCourseDescription())
                .courseCode(courseModel.getCourseCode())
                .teacherId(teacher.getId())
                .build();
        int newCourseId = courseDAO.createCourse(newCourse);

        return new ResponseEntity<>(newCourseId, HttpStatus.OK);


    }

    public ResponseEntity<?> addStudentToCourse(final CourseStudentRequestModel courseStudentRequestModel) {
        if (courseStudentRequestModel.getCourseId() == 0
                || courseStudentRequestModel.getStudentId() == 0) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        }

        if (!studentDAO.existsById(courseStudentRequestModel.getStudentId())) {
            return new ResponseEntity<>("Student not found.", HttpStatus.NOT_FOUND);
        }

        if (!courseDAO.existsById(courseStudentRequestModel.getCourseId())) {
            return new ResponseEntity<>("Course not found.", HttpStatus.NOT_FOUND);
        }

        if (courseStudentDAO.existsByCourseIdAndStudentId(courseStudentRequestModel.getCourseId(),
                courseStudentRequestModel.getStudentId())) {
            return new ResponseEntity<>("Student already belongs to the course", HttpStatus.CONFLICT);
        }

        Course course = courseDAO.findById(courseStudentRequestModel.getCourseId());
        Student student = studentDAO.findStudentById(courseStudentRequestModel.getStudentId());

        CourseStudent newCourseStudent = CourseStudent.builder()
                .courseId(course.getId())
                .studentId(student.getId())
                .studentEntryCode(UUID.randomUUID().toString())
                .build();

        courseStudentDAO.createCourseStudent(newCourseStudent);

        return new ResponseEntity<>(newCourseStudent.getStudentEntryCode(), HttpStatus.OK);
    }

    public ResponseEntity<?> getCoursesByTeacher(int teacherId) {
        List<Course> courseList = courseDAO.findAllByTeacherId(teacherId);
//        if(courseList.size() == 0) {
//            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
//        }
//
//        List<CourseListResponseModel> newCourseList = new ArrayList<>();
//        for (int i = 0; i < courseList.get().size(); i++) {
//            Optional<List<CourseStudentModel>> courseStudentModels = courseStudentRepository.findAllByCourse_Id(courseList.get().get(i).getId());
//            if (!courseStudentModels.isPresent()) {
//                CourseListResponseModel courseListResponseModel = CourseListResponseModel.builder()
//                        .id(courseList.get().get(i).getId())
//                        .courseName(courseList.get().get(i).getCourseName())
//                        .courseCode(courseList.get().get(i).getCourseCode())
//                        .courseDescription(courseList.get().get(i).getCourseDescription())
//                        .students(new ArrayList<>())
//                        .build();
//                newCourseList.add(courseListResponseModel);
//            } else {
//                List<StudentModel> studentList = new ArrayList<>();
//                for (int j = 0; j < courseStudentModels.get().size(); j++) {
//                    studentList.add(courseStudentModels.get().get(j).getStudent());
//                }
//                CourseListResponseModel courseListResponseModel = CourseListResponseModel.builder()
//                        .id(courseList.get().get(i).getId())
//                        .courseName(courseList.get().get(i).getCourseName())
//                        .courseCode(courseList.get().get(i).getCourseCode())
//                        .courseDescription(courseList.get().get(i).getCourseDescription())
//                        .students(studentList)
//                        .build();
//                newCourseList.add(courseListResponseModel);
//            }
//        }
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }
}
