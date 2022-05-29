package com.ruoyi.logistics.mapper;

import java.util.List;
import com.ruoyi.logistics.domain.LnLogistics;

/**
 * 物流信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-17
 */
public interface LnLogisticsMapper 
{
    /**
     * 查询物流信息
     * 
     * @param id 物流信息主键
     * @return 物流信息
     */
    public LnLogistics selectLnLogisticsById(Long id);
    public LnLogistics selectOneLnLogistics(LnLogistics lnLogistics);

    /**
     * 查询物流信息列表
     * 
     * @param lnLogistics 物流信息
     * @return 物流信息集合
     */
    public List<LnLogistics> selectLnLogisticsList(LnLogistics lnLogistics);
    public List<LnLogistics> selectLnLogisticsList2(LnLogistics lnLogistics);

    /**
     * 新增物流信息
     * 
     * @param lnLogistics 物流信息
     * @return 结果
     */
    public int insertLnLogistics(LnLogistics lnLogistics);

    /**
     * 修改物流信息
     * 
     * @param lnLogistics 物流信息
     * @return 结果
     */
    public int updateLnLogistics(LnLogistics lnLogistics);

    /**
     * 删除物流信息
     * 
     * @param id 物流信息主键
     * @return 结果
     */
    public int deleteLnLogisticsById(Long id);

    /**
     * 批量删除物流信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLnLogisticsByIds(Long[] ids);
}
