package com.ruoyi.logistics.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.logistics.mapper.LnLogisticsMapper;
import com.ruoyi.logistics.domain.LnLogistics;
import com.ruoyi.logistics.service.ILnLogisticsService;

/**
 * 物流信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-17
 */
@Service
public class LnLogisticsServiceImpl implements ILnLogisticsService 
{
    @Autowired
    private LnLogisticsMapper lnLogisticsMapper;

    /**
     * 查询物流信息
     * 
     * @param id 物流信息主键
     * @return 物流信息
     */
    @Override
    public LnLogistics selectLnLogisticsById(Long id)
    {
        return lnLogisticsMapper.selectLnLogisticsById(id);
    }
    public LnLogistics selectOneLnLogistics(LnLogistics lnLogistics)
    {
        return lnLogisticsMapper.selectOneLnLogistics(lnLogistics);
    }


    /**
     * 查询物流信息列表
     * 
     * @param lnLogistics 物流信息
     * @return 物流信息
     */
    @Override
    public List<LnLogistics> selectLnLogisticsList(LnLogistics lnLogistics)
    {
        return lnLogisticsMapper.selectLnLogisticsList(lnLogistics);
    }

    /**
     * 新增物流信息
     * 
     * @param lnLogistics 物流信息
     * @return 结果
     */
    @Override
    public int insertLnLogistics(LnLogistics lnLogistics)
    {
        lnLogistics.setCreateTime(DateUtils.getNowDate());
        return lnLogisticsMapper.insertLnLogistics(lnLogistics);
    }

    /**
     * 修改物流信息
     * 
     * @param lnLogistics 物流信息
     * @return 结果
     */
    @Override
    public int updateLnLogistics(LnLogistics lnLogistics)
    {
        lnLogistics.setUpdateTime(DateUtils.getNowDate());
        return lnLogisticsMapper.updateLnLogistics(lnLogistics);
    }

    /**
     * 批量删除物流信息
     * 
     * @param ids 需要删除的物流信息主键
     * @return 结果
     */
    @Override
    public int deleteLnLogisticsByIds(Long[] ids)
    {
        return lnLogisticsMapper.deleteLnLogisticsByIds(ids);
    }

    /**
     * 删除物流信息信息
     * 
     * @param id 物流信息主键
     * @return 结果
     */
    @Override
    public int deleteLnLogisticsById(Long id)
    {
        return lnLogisticsMapper.deleteLnLogisticsById(id);
    }
}
