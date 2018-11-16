package com.esc.calllog.service;

import com.esc.calllog.dao.CallLogRepository;
import com.esc.calllog.entity.CallLogEntity;
import com.esc.calllog.response.RestResponse;
import com.esc.calllog.response.UploadExcelResponse;
import com.esc.calllog.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CallLogService {
    @Autowired
    CallLogRepository repository;


    @Transactional
    public UploadExcelResponse uploadData(List<String[]> data) throws ParseException {
        UploadExcelResponse  response = new UploadExcelResponse();
        List<CallLogEntity> callLogEntities = new ArrayList<>();
        //System.out.println("The size of our array is::" + data.size());
        for (int i = 0; i < data.size(); i++) {
            //check if the number of columns
            if (data.get(i).length != 7) {
                response.setResponseCode("999");
                response.setResponseMessage("Excel data not properly formatted, column is greater than three(7)");

                return response;
            }
            CallLogEntity callLogEntity = new CallLogEntity();

            callLogEntity.setName(data.get(i)[0]); //name
            callLogEntity.setPhone_number(data.get(i)[1]);   // Phone Number
            callLogEntity.setDate_dialled(data.get(i)[2]); //	Date dialled
            callLogEntity.setDate_received(data.get(i)[3]); //Date received
            callLogEntity.setTime_dialled(Helper.timeFormatter(data.get(i)[4]));   // Time dialled
            callLogEntity.setTime_received(Helper.timeFormatter(data.get(i)[5]));// Time received
            callLogEntity.setSource(data.get(i)[6]); //Source
            callLogEntities.add(callLogEntity);

        }
        // [ Saving to Database ]
        for (int i = 0; i < callLogEntities.size(); i++) {
              repository.save(callLogEntities.get(i));
        }
        response.setResponseCode("000");
        response.setResponseMessage("Successfully saved call log entries");
        return response;
    }


    //get all
    @Transactional
    public List<CallLogEntity> getAllEntries(){
//        List<CallLogEntity> entries = new ArrayList<>();
//        repository.findAll()
//                .forEach(entries:: add);
        return repository.findAll();
    }

    //get entry by id
    @Transactional
    public CallLogEntity getEntry(Long id){
        return repository.findById(id).orElse(null);
    }

    //add new entry
    @Transactional
    public CallLogEntity addEntry(CallLogEntity callLogEntity){
        //RestResponse response = new RestResponse();
        repository.save(callLogEntity);
//        response.setResponseCode("200");
//        response.setResponseMessage("Successfully saved call log entry");
        return  repository.save(callLogEntity);
    }

    //update entry
    @Transactional
    public CallLogEntity updateEntry( CallLogEntity callLogEntity ) {
//        RestResponse response = new RestResponse();
//        response.setResponseCode("200");
//        response.setResponseMessage("Successfully updated call log entry");
        return repository.save(callLogEntity);
    }

    //delete entry
    @Transactional
    public void deletEntry(Long id) {
        //RestResponse response = new RestResponse();
        repository.deleteById(id);
//        response.setResponseCode("200");
//        response.setResponseMessage("Successfully deleted call log entry");
       // return  response;
    }

    //delete all
    @Transactional
    public void deleteAllEntries() {
        //RestResponse response = new RestResponse();
        repository.deleteAll();
//        response.setResponseCode("200");
//        response.setResponseMessage("Successfully deleted call log entry");
       // return  response;
    }


}
