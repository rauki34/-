package com.ruoyi.system.service.impl;
import com.github.pagehelper.PageInfo;

import com.ruoyi.common.core.domain.class224.CS42Record;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.system.mapper.CS42RecordMapper;
import com.ruoyi.system.service.CS42RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 压浆材料配合比设计试验检测记录 业务层处理
 *
 */
@Service
public class CS42RecordServiceImpl implements CS42RecordService {
    private static final Logger log = LoggerFactory.getLogger(CS42RecordServiceImpl.class);

    //@Autowired
    @Resource
    private CS42RecordMapper CS42Mapper;

    /**
     * 根据条件分页查询压浆材料配合比设计试验检测记录列表
     * 
     * @param CS42 压浆材料配合比设计试验检测记录信息
     * @return 压浆材料配合比设计试验检测记录信息集合信息
     */
    @Override
    public PageInfo<CS42Record> selectCS42RecordList(CS42Record CS42, Integer pageNum,
                                                     Integer pageSize){
        // 使用分页插件pagehelper 进行分页
        PageUtils.startPage();
        return new PageInfo<>(CS42Mapper.selectCS42RecordList(CS42));
    }
    
    /**
     * 通过压浆材料配合比设计试验检测记录ID查询压浆材料配合比设计试验检测记录
     * 
     * @param id 压浆材料配合比设计试验检测记录ID
     * @return 压浆材料配合比设计试验检测记录对象信息
     */
    @Override
    public CS42Record selectCS42RecordById(Long id) {
        return CS42Mapper.selectCS42RecordById(id);
    }
    
    /**
     * 新增保存压浆材料配合比设计试验检测记录信息
     * 
     * @param CS42 压浆材料配合比设计试验检测记录信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertCS42Record(CS42Record CS42){
        // 新增压浆材料配合比设计试验检测记录信息
        int rows = CS42Mapper.insertCS42Record(CS42);
        return rows;
    }
    
    /**
     * 修改保存压浆材料配合比设计试验检测记录信息
     * 
     * @param CS42 压浆材料配合比设计试验检测记录信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateCS42Record(CS42Record CS42) {
        int row = CS42Mapper.updateCS42Record(CS42);
        // 如果子表和主表
        // 修改的时候就值主表信息，子表是可以先删后增精选修改

        // 数据库里面查询出来子表，根前端修改子表进行比对
        return row;
    }
    
    /**
     * 通过压浆材料配合比设计试验检测记录ID删除压浆材料配合比设计试验检测记录
     * 
     * @param id 压浆材料配合比设计试验检测记录ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteCS42RecordById(Long id){
        return CS42Mapper.deleteCS42RecordById(id);
    }

    /**
     * 批量删除压浆材料配合比设计试验检测记录信息
     * 
     * @param ids 需要删除的压浆材料配合比设计试验检测记录ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteCS42RecordByIds(Long[] ids) {
        return CS42Mapper.deleteCS42RecordByIds(ids);
    }
    
}
