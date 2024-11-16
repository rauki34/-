package com.ruoyi.system.service;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.class224.CS42Record;


/**
 * 压浆材料配合比设计试验检测记录 业务层
 */
public interface CS42RecordService
{
    /**
     * 根据条件分页查询压浆材料配合比设计试验检测记录列表
     * 
     * @param CS42 压浆材料配合比设计试验检测记录信息
     * @return 压浆材料配合比设计试验检测记录信息集合信息
     */
    public PageInfo<CS42Record> selectCS42RecordList(CS42Record CS42, Integer pageNum,
                                                     Integer pageSize);
    /**
     * 通过压浆材料配合比设计试验检测记录ID查询压浆材料配合比设计试验检测记录
     * 
     * @param id 压浆材料配合比设计试验检测记录ID
     * @return 压浆材料配合比设计试验检测记录对象信息
     */
    public CS42Record selectCS42RecordById(Long id);
    /**
     * 新增压浆材料配合比设计试验检测记录信息
     * 
     * @param CS42 压浆材料配合比设计试验检测记录信息
     * @return 结果
     */
    public int insertCS42Record(CS42Record CS42);
    /**
     * 修改压浆材料配合比设计试验检测记录信息
     * 
     * @param CS42 压浆材料配合比设计试验检测记录信息
     * @return 结果
     */
    public int updateCS42Record(CS42Record CS42);
    /**
     * 通过压浆材料配合比设计试验检测记录ID删除压浆材料配合比设计试验检测记录
     * 
     * @param id 压浆材料配合比设计试验检测记录ID
     * @return 结果
     */
    public int deleteCS42RecordById(Long id);
    /**
     * 批量删除压浆材料配合比设计试验检测记录信息
     * 
     * @param ids 需要删除的压浆材料配合比设计试验检测记录ID
     * @return 结果
     */
    public int deleteCS42RecordByIds(Long[] ids);
}
