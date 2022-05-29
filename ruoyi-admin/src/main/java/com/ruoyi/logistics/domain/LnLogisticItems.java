package com.ruoyi.logistics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 快件信息对象 ln_logistic_items
 * 
 * @author ruoyi
 * @date 2022-05-17
 */
public class LnLogisticItems extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    //    app backend
    private int platform;

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    /** ID */
    private Long id;

    /** 收货快递ID */
    @Excel(name = "收货快递ID")
    private Long logisticsId;

    /** 楼栋ID */
    @Excel(name = "楼栋ID")
    private String apartmentId;

    /** 单元 */
    @Excel(name = "单元")
    private String unitId;

    /** 房间ID */
    @Excel(name = "房间ID")
    private Long roomId;

    /** 物品面单图片 */
    @Excel(name = "物品面单图片")
    private String img;

    /** 状态（0送达 1待送） */
    @Excel(name = "状态", readConverterExp = "0=送达,1=待送")
    private String status;

    /** 快递公司 */
    @Excel(name = "快递公司")
    private String courierCompany;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String courierNumber;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLogisticsId(Long logisticsId) 
    {
        this.logisticsId = logisticsId;
    }

    public Long getLogisticsId() 
    {
        return logisticsId;
    }
    public void setApartmentId(String apartmentId) 
    {
        this.apartmentId = apartmentId;
    }

    public String getApartmentId() 
    {
        return apartmentId;
    }
    public void setUnitId(String unitId) 
    {
        this.unitId = unitId;
    }

    public String getUnitId() 
    {
        return unitId;
    }
    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCourierCompany(String courierCompany) 
    {
        this.courierCompany = courierCompany;
    }

    public String getCourierCompany() 
    {
        return courierCompany;
    }
    public void setCourierNumber(String courierNumber) 
    {
        this.courierNumber = courierNumber;
    }

    public String getCourierNumber() 
    {
        return courierNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("logisticsId", getLogisticsId())
            .append("apartmentId", getApartmentId())
            .append("unitId", getUnitId())
            .append("roomId", getRoomId())
            .append("img", getImg())
            .append("status", getStatus())
            .append("courierCompany", getCourierCompany())
            .append("courierNumber", getCourierNumber())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
