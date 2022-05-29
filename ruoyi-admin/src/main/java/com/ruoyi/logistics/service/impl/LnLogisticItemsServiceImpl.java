package com.ruoyi.logistics.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.logistics.mapper.LnLogisticItemsMapper;
import com.ruoyi.logistics.domain.LnLogisticItems;
import com.ruoyi.logistics.service.ILnLogisticItemsService;

/**
 * 快件信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-17
 */
@Service
public class LnLogisticItemsServiceImpl implements ILnLogisticItemsService 
{
    @Autowired
    private LnLogisticItemsMapper lnLogisticItemsMapper;

    /**
     * 查询快件信息
     * 
     * @param id 快件信息主键
     * @return 快件信息
     */
    @Override
    public LnLogisticItems selectLnLogisticItemsById(Long id)
    {
        return lnLogisticItemsMapper.selectLnLogisticItemsById(id);
    }

    /**
     * 查询快件信息列表
     * 
     * @param lnLogisticItems 快件信息
     * @return 快件信息
     */
    @Override
    public List<LnLogisticItems> selectLnLogisticItemsList(LnLogisticItems lnLogisticItems)
    {
        return lnLogisticItemsMapper.selectLnLogisticItemsList(lnLogisticItems);
    }
    @Override
    public List<LnLogisticItems> selectLnLogisticItemsList2(LnLogisticItems lnLogisticItems)
    {
        return lnLogisticItemsMapper.selectLnLogisticItemsList2(lnLogisticItems);
    }

    /**
     * 新增快件信息
     * 
     * @param lnLogisticItems 快件信息
     * @return 结果
     */
    @Override
    public int insertLnLogisticItems(LnLogisticItems lnLogisticItems)
    {
        lnLogisticItems.setCreateTime(DateUtils.getNowDate());
        return lnLogisticItemsMapper.insertLnLogisticItems(lnLogisticItems);
    }

    /**
     * 修改快件信息
     * 
     * @param lnLogisticItems 快件信息
     * @return 结果
     */
    @Override
    public int updateLnLogisticItems(LnLogisticItems lnLogisticItems)
    {
        lnLogisticItems.setUpdateTime(DateUtils.getNowDate());
        return lnLogisticItemsMapper.updateLnLogisticItems(lnLogisticItems);
    }

    /**
     * 批量删除快件信息
     * 
     * @param ids 需要删除的快件信息主键
     * @return 结果
     */
    @Override
    public int deleteLnLogisticItemsByIds(Long[] ids)
    {
        return lnLogisticItemsMapper.deleteLnLogisticItemsByIds(ids);
    }

    /**
     * 删除快件信息信息
     * 
     * @param id 快件信息主键
     * @return 结果
     */
    @Override
    public int deleteLnLogisticItemsById(Long id)
    {
        return lnLogisticItemsMapper.deleteLnLogisticItemsById(id);
    }
}
