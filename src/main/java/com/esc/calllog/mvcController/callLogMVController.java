package com.esc.calllog.mvcController;

import com.esc.calllog.entity.CallLogEntity;
import com.esc.calllog.response.UploadExcelResponse;
import com.esc.calllog.service.CallLogService;
import com.esc.calllog.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class callLogMVController {
    @Autowired
    CallLogService callLogService;
    //upload call log excel data
    @PostMapping("/uploadExcelFile")
    public String uploadAuditableArea(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        InputStream is = file.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

//        List<String[]> dat = Helper.readXcelDocuments(is);
        List<String[]> data = new ArrayList<>();
        try {
            //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": Attempting to read excel document to ==> ");
            if (!file.isEmpty()) {
                if ((fileName.substring(fileName.length() - 3).equalsIgnoreCase("xls")
                        && contentType.equalsIgnoreCase("application/vnd.ms-excel"))
                        || (fileName.substring(fileName.length() - 4).equalsIgnoreCase("xlsx")
                        && contentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) {
                    //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": Valid excel file");

                    //start to read from file
                    //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": start to read from excel file ==> ");

                    data = Helper.readXcelDocuments(bis);

                    //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": Size of data is ==> " + data.size());

                    if (data.size() == 0) {
//                        return new ResponseEntity<>
//                                (new BaseResponse("204", "Excel file has no data", data), HttpStatus.NO_CONTENT);
                    } else {
                        //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": successfully read Excel file");
                        //changed the method in uploadService to static
                        UploadExcelResponse response = callLogService.uploadData(data);

                        if (!response.getResponseCode().equals("000") || response.getResponseCode() != "000") {
//                            return new ResponseEntity<>
//                                    (new BaseResponse(response.getResponseCode(), response.getResponseMessage()), HttpStatus.PRECONDITION_REQUIRED);
                        }

//                        return new ResponseEntity<>
//                                (new BaseResponse("000", "successfully Uploaded call log entry"), HttpStatus.OK);
                    }
                } else {
                    //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": Not a valid excel or csv file");
//                    return new ResponseEntity<>
//                            (new RestResponse("415", "Not a valid excel or csv file"), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
                }
            } else {
                //file is empty........
                //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": File is empty");
//                return new ResponseEntity<>
//                        (new RestResponse("204", "Excel file is empty"), HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //LOGGER.severe(MOBILE_SERVICES_CONTROLLER + ": Oops Something went wrong " + e);
//            return new ResponseEntity<>
//                    (new RestResponse("99", "Oops Something went wrong while reading document. " + "\n" + "Ensure that all fields in the auditable area data have been filled" + "\n" + e.toString()), HttpStatus.PRECONDITION_FAILED);
        }

        return "redirect:/logs";
    }

    //home
    @RequestMapping("/")
    public String home(){
        return "index";
    }

    //get all entries
    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public String getAll(Model model){
        //List<CallLogEntity> entries = callLogService.getAllEntries();
        model.addAttribute("callLogEntity", callLogService.getAllEntries());
        return "logs";
    }
//
    //get by entry by id @{/findOne/__${emp.id}__}
    @GetMapping(value = "/findOne")
    @ResponseBody
    public CallLogEntity findOne(Long id){
        //callLogService.getEntry(id);
        return callLogService.getEntry(id);
    }


    //add new call log entry
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addNewLog(@ModelAttribute CallLogEntity callLogEntity, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("errors "+bindingResult);
            return "logs";
        }
        callLogService.addEntry(callLogEntity);
        return "redirect:/logs";
    }


    //update record by id
    @RequestMapping(value = "logs/{id}", method = RequestMethod.PUT)
    public String updateCallLogEntry(@PathVariable("id") Long id, @RequestBody CallLogEntity callLogEntity ){
        CallLogEntity updateEnt = callLogService.getEntry(id);
        updateEnt.setName(callLogEntity.getName());
        updateEnt.setPhone_number(callLogEntity.getPhone_number());
        updateEnt.setDate_dialled(callLogEntity.getDate_dialled());
        updateEnt.setDate_received(callLogEntity.getDate_received());
        updateEnt.setTime_dialled(callLogEntity.getTime_dialled());
        updateEnt.setTime_received(callLogEntity.getTime_received());
        updateEnt.setSource(callLogEntity.getSource());
        callLogService.updateEntry(updateEnt);
        return "redirect:logs";
    }
//
    //delete entry
    @GetMapping(value = "/deleteLog")
    public String deleteEntry(Long id){
        callLogService.deletEntry(id);
        return "redirect:/logs";
    }

//    //delete all
//    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteLogs")
//    public String deleteAll(){
//        callLogService.deleteAllEntries();
//        return "redirect:logs";
//    }

    //create User
    @RequestMapping("/createUser")
    public String create(){
        return "createUser";
    }

}
