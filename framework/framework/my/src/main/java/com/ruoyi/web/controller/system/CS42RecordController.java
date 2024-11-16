package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;

import com.ruoyi.common.core.domain.class224.CS42Record;
import com.ruoyi.system.service.CS42RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层
 */
@RestController
public class CS42RecordController
{
    @Autowired
    private CS42RecordService recordService;

    /**
     *  分页查询
     */
    @GetMapping("/getCS42Records")
    public AjaxResult getCS42Records(CS42Record record, Integer pageNum,
                                     Integer pageSize)
    {

        // AjaxResult 诺依包装返回指定数据格式给前端的处理类
        AjaxResult ajax = AjaxResult.success(
                recordService.selectCS42RecordList(record, pageNum, pageSize));
        System.out.println("****************");
        System.out.println(record);
        System.out.println(pageNum);
        System.out.println(pageSize);
        ajax.put("code", 0);
        ajax.put("msg", "查询成功");
        return ajax;
    }

    /**
     *  单个查询
     */
    @GetMapping("/getCS42Record/{id}")
    public AjaxResult getCS42Record(@PathVariable("id") Long id)
    {   // AjaxResult 诺依包装返回指定数据格式给前端的处理类
        AjaxResult ajax = AjaxResult.success(
                recordService.selectCS42RecordById(id));
        ajax.put("code", 0);
        ajax.put("msg", "查询成功");
        return ajax;
    }

    /**
     *  添加
     */
    @PostMapping("/addCS42Record")
    public AjaxResult addCS42Record(@RequestBody CS42Record record)
    {
        System.out.println(record);
        // AjaxResult 诺依包装返回指定数据格式给前端的处理类
        AjaxResult ajax = AjaxResult.success(recordService.insertCS42Record(record));
        ajax.put("code", 0);
        ajax.put("msg", "插入成功");
        return ajax;
    }

    /**
     *  修改
     */
    @PutMapping("/editCS42Record")
    public AjaxResult editCS42Record(@RequestBody CS42Record record)
    {   // AjaxResult 诺依包装返回指定数据格式给前端的处理类
        AjaxResult ajax = AjaxResult.success(recordService.updateCS42Record(record));
        ajax.put("code", 0);
        ajax.put("msg", "修改成功");
        return ajax;
    }

    /**
     *  删除
     */
    @DeleteMapping("/deleteCS42Record/{id}")
    public AjaxResult deleteCS42Record(@PathVariable("id") Long id)
    {   // AjaxResult 诺依包装返回指定数据格式给前端的处理类
        AjaxResult ajax = AjaxResult.success(recordService.deleteCS42RecordById(id));
        ajax.put("code", 0);
        ajax.put("msg", "删除成功");
        return ajax;
    }


}
