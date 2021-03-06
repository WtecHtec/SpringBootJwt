package com.example.demo.contronller;

import com.example.demo.dao.mapper.RecordMapper;
import com.example.demo.dao.pojo.Record;
import com.example.demo.dao.pojo.User;
import com.example.demo.jwt.JwtIgnore;
import com.example.demo.request.RecordRequest;
import com.example.demo.serviceImp.RecordServiceImp;
import com.example.demo.uitl.BaseResponse;
import com.example.demo.uitl.Result;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class RecordController {

     @Autowired
     private RecordServiceImp recordServiceImp;



    //分页获取
    @RequestMapping(value = "/getRecordRecord",method =  RequestMethod.GET)
    public BaseResponse<PageInfo<Record>> getRecordDatas(
            Integer pageNum,Integer pageSize
    ) {
        log.info("新增记录");
        BaseResponse<PageInfo<Record>> result = new BaseResponse<>();
        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setPageNum(pageNum);
        recordRequest.setPageSize(pageSize);
        result = recordServiceImp.getRecordDatas(recordRequest);
        return result;
    }

    //报表
    @RequestMapping(value = "/getRecordReports",method =  RequestMethod.GET)
    public BaseResponse<List<Record>> getRecordReports() {
        log.info("新增记录");
        BaseResponse<List<Record>> result = new BaseResponse<>();
        result = recordServiceImp.getRecordReports();
        return result;
    }

    //新增
    @RequestMapping(value = "/createRecord",method =  RequestMethod.POST)
    public BaseResponse<String> registered(Record record) {
        log.info("新增记录");
        BaseResponse<String> result = new BaseResponse<>();
        result = recordServiceImp.createRecordInfo(record);
        return result;
    }

    // 更新
    @RequestMapping(value = "/updateRecord",method =  RequestMethod.POST)
    public BaseResponse<String> updateRecord(Record record) {
        log.info("提交记录");
        BaseResponse<String> result = new BaseResponse<>();
        result = recordServiceImp.updateRecordInfo(record);
        return result;
    }

    // 更新
    @RequestMapping(value = "/delRecord",method =  RequestMethod.POST)
    public BaseResponse<String> delRecord(Record record) {
        log.info("删除记录");
        BaseResponse<String> result = new BaseResponse<>();
        record.setDel(1);
        result = recordServiceImp.updateRecordInfo(record);
        return result;
    }


}
