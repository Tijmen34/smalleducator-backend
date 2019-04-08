package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.DialogDao;
import com.hva.aad.SmallEducator.dao.LessonDao;
import com.hva.aad.SmallEducator.models.Dialog;
import com.hva.aad.SmallEducator.models.request.CreateDialogRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service for the dialog property to communicate with the repository.
 *
 * @author Tijmen Stor
 */
@Service
public class DialogService {

    private final LessonDao lessonDao;
    private final DialogDao dialogDao;

    @Autowired
    public DialogService(LessonDao lessonDao, DialogDao dialogDao) {
        this.lessonDao = lessonDao;
        this.dialogDao = dialogDao;
    }


    public ResponseEntity<?> createDialog(CreateDialogRequestModel createDialogRequestModel) {
        if (createDialogRequestModel.getLessonId() == 0 || createDialogRequestModel.getDialogName() == null ||
                createDialogRequestModel.getDialogDescription() == null) {
            return new ResponseEntity<>("Incomplete request", HttpStatus.BAD_REQUEST);
        }
        if (!lessonDao.existsById(createDialogRequestModel.getLessonId())) {
            return new ResponseEntity<>("Lesson does not exist.", HttpStatus.NOT_FOUND);
        }

        Dialog dialog = Dialog.builder()
                .lessonId(createDialogRequestModel.getLessonId())
                .dialogName(createDialogRequestModel.getDialogName())
                .dialogDescription(createDialogRequestModel.getDialogDescription())
                .build();

        Integer newDialogId = dialogDao.createDialog(dialog);

        return new ResponseEntity<>(newDialogId, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteDialog(int dialogId) {
        if (!dialogDao.existsById(dialogId)) {
            return new ResponseEntity<>("Dialog not found.", HttpStatus.NOT_FOUND);
        }
        dialogDao.deleteById(dialogId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getDialog(int dialogId) {
        if (!dialogDao.existsById(dialogId)) {
            return new ResponseEntity<>("Dialog not found.", HttpStatus.NOT_FOUND);
        }
        Dialog dialog = dialogDao.findById(dialogId);
        return new ResponseEntity<>(dialog, HttpStatus.OK);
    }
}
