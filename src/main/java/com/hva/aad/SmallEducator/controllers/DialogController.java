package com.hva.aad.SmallEducator.controllers;

import com.hva.aad.SmallEducator.models.request.CreateDialogRequestModel;
import com.hva.aad.SmallEducator.services.DialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that receives all the HTTP requests on the dialog property.
 *
 * @author Tijmen Stor
 */
@RestController
public class DialogController {

    private final DialogService dialogService;

    @Autowired
    public DialogController(DialogService dialogService) {
        this.dialogService = dialogService;
    }

    @PostMapping(value = "/dialog")
    public ResponseEntity<?> createDialog(@RequestBody CreateDialogRequestModel createDialogRequestModel) {
        return dialogService.createDialog(createDialogRequestModel);
    }

    @GetMapping(value = "/dialog/{dialogId}")
    public ResponseEntity<?> getDialog(@PathVariable int dialogId) {
        return dialogService.getDialog(dialogId);
    }


    @DeleteMapping(value = "/dialog/{dialogId}")
    public ResponseEntity<?> deleteDialog(@PathVariable int dialogId) {
        return dialogService.deleteDialog(dialogId);
    }
}
