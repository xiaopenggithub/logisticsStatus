package com.ruoyi.logistics.mapper;

import java.util.List;
import com.ruoyi.logistics.domain.LnLogisticItems;

/**
 * 快件信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-17
 */
public interface LnLogisticItemsMapper 
{
    /**
     * 查询快件信息
     * 
     * @param id 快件信息主键
     * @return 快件信息
     */
    public LnLogisticItems selectLnLogisticItemsById(Long id);

    /**
     * 查询快件信息列表
     * 
     * @param lnLogisticItems 快件信息
     * @return 快件信息集合
     */
    public List<LnLogisticItems> selectLnLogisticItemsList(LnLogisticItems lnLogisticItems);
    public List<LnLogisticItems> selectLnLogisticItemsList2(LnLogisticItems lnLogisticItems);

    /**
     * 新增快件信息
     * 
     * @param lnLogisticItems 快件信息
     * @return 结果
     */
    public int insertLnLogisticItems(LnLogisticItems lnLogisticItems);

    /**
     * 修改快件信息
     * 
     * @param lnLogisticItems 快件信息
     * @return 结果
     */
    public int updateLnLogisticItems(LnLogisticItems lnLogisticItems);

    /**
     * 删除快件信息
     * 
     * @param id 快件信息主键
     * @return 结果
     */
    public int deleteLnLogisticItemsById(Long id);

    /**
     * 批量删除快件信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLnLogisticItemsByIds(Long[] ids);
}
