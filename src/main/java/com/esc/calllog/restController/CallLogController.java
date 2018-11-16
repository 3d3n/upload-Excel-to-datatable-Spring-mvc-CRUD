package com.esc.calllog.restController;

import com.esc.calllog.entity.CallLogEntity;
import com.esc.calllog.response.BaseResponse;
import com.esc.calllog.response.RestResponse;
import com.esc.calllog.response.UploadExcelResponse;
import com.esc.calllog.service.CallLogService;
import com.esc.calllog.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CallLogController {
    @Autowired
    CallLogService callLogService;

//    //upload call log excel data
//    @RequestMapping(value = "/uploadExcelFile", method = RequestMethod.POST)
//    public ResponseEntity<?> uploadAuditableArea(@RequestParam("file") MultipartFile file) throws Exception {
//        String fileName = file.getOriginalFilename();
//        String contentType = file.getContentType();
//        InputStream is = file.getInputStream();
//        BufferedInputStream bis = new BufferedInputStream(is);
//
////        List<String[]> dat = Helper.readXcelDocuments(is);
//        List<String[]> data = new ArrayList<>();
//        try {
//            //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": Attempting to read excel document to ==> ");
//            if (!file.isEmpty()) {
//                if ((fileName.substring(fileName.length() - 3).equalsIgnoreCase("xls")
//                        && contentType.equalsIgnoreCase("application/vnd.ms-excel"))
//                        || (fileName.substring(fileName.length() - 4).equalsIgnoreCase("xlsx")
//                        && contentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) {
//                    //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": Valid excel file");
//
//                    //start to read from file
//                    //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": start to read from excel file ==> ");
//
//                    data = Helper.readXcelDocuments(bis);
//
//                    //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": Size of data is ==> " + data.size());
//
//                    if (data.size() == 0) {
//                        return new ResponseEntity<>
//                                (new BaseResponse("204", "Excel file has no data", data), HttpStatus.NO_CONTENT);
//                    } else {
//                        //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": successfully read Excel file");
//                        //changed the method in uploadService to static
//                        UploadExcelResponse response = callLogService.uploadData(data);
//
//                        if (!response.getResponseCode().equals("000") || response.getResponseCode() != "000") {
//                            return new ResponseEntity<>
//                                    (new BaseResponse(response.getResponseCode(), response.getResponseMessage()), HttpStatus.PRECONDITION_REQUIRED);
//                        }
//
//                        return new ResponseEntity<>
//                                (new BaseResponse("000", "successfully Uploaded call log entry"), HttpStatus.OK);
//                    }
//                } else {
//                    //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": Not a valid excel or csv file");
//                    return new ResponseEntity<>
//                            (new RestResponse("415", "Not a valid excel or csv file"), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
//                }
//            } else {
//                //file is empty........
//                //LOGGER.info(MOBILE_SERVICES_CONTROLLER + ": File is empty");
//                return new ResponseEntity<>
//                        (new RestResponse("204", "Excel file is empty"), HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            //LOGGER.severe(MOBILE_SERVICES_CONTROLLER + ": Oops Something went wrong " + e);
//            return new ResponseEntity<>
//                    (new RestResponse("99", "Oops Something went wrong while reading document. " + "\n" + "Ensure that all fields in the file have been filled" + "\n" + e.toString()), HttpStatus.PRECONDITION_FAILED);
//        }
//    }
//
//    //get all entries
//    @RequestMapping(value = "/logs")
//    public List<CallLogEntity> getCallLogentries(){
//        return callLogService.getAllEntries();
//    }
//
//    //get by entry by id
//    @RequestMapping(value = "/logs/{id}")
//    public CallLogEntity getCallLogEntryById(@PathVariable("id") Long id){
//        return callLogService.getEntry(id);
//    }
//
////    //add new call log entry
////    @RequestMapping(method = RequestMethod.POST, value = "/logs")
////    public  RestResponse addTopic(@RequestBody CallLogEntity callLogEntity){
////        return callLogService.addEntry(callLogEntity);
////    }
//
//    //update record by id
//    @RequestMapping(value = "logs/{id}", method = RequestMethod.PUT)
//    public RestResponse updateCallLogEntry(@PathVariable("id") Long id, @RequestBody CallLogEntity callLogEntity ){
//        CallLogEntity updateEnt = callLogService.getEntry(id);
//        updateEnt.setName(callLogEntity.getName());
//        updateEnt.setPhone_number(callLogEntity.getPhone_number());
//        updateEnt.setDate_dialled(callLogEntity.getDate_dialled());
//        updateEnt.setDate_received(callLogEntity.getDate_received());
//        updateEnt.setTime_dialled(callLogEntity.getTime_dialled());
//        updateEnt.setTime_received(callLogEntity.getTime_received());
//        updateEnt.setSource(callLogEntity.getSource());
//        return callLogService.updateEntry(updateEnt);
//    }
//
//    //delete entry
//    @RequestMapping(method = RequestMethod.DELETE, value = "/logs/{id}")
//    public RestResponse deleteEntry(@PathVariable Long id){
//
//        return callLogService.deletEntry(id);
//    }

    //delete all
    @RequestMapping(method = RequestMethod.DELETE, value = "/logs")
    public RestResponse deleteAll(){
        callLogService.deleteAllEntries();
        RestResponse response = new RestResponse();
        response.setResponseMessage("ok");
        return response;
    }
}
