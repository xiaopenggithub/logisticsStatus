package com.ruoyi.logistics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物流信息对象 ln_logistics
 * 
 * @author ruoyi
 * @date 2022-05-17
 */
public class LnLogistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private int couta;
    private int cout0;
    private int cout1;
    private int cout2;

    public int getCout2() {
        return cout2;
    }

    public void setCout2(int cout2) {
        this.cout2 = cout2;
    }

    //    app backend
    private int platform;

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getCouta() {
        return couta;
    }

    public void setCouta(int couta) {
        this.couta = couta;
    }

    public int getCout0() {
        return cout0;
    }

    public void setCout0(int cout0) {
        this.cout0 = cout0;
    }

    public int getCout1() {
        return cout1;
    }

    public void setCout1(int cout1) {
        this.cout1 = cout1;
    }

    /** ID */
    private Long id;

    /** 楼栋ID */
    @Excel(name = "楼栋ID")
    private String apartmentId;

    /** 单元 */
    @Excel(name = "单元")
    private String unitId;

    /** 房间ID */
    @Excel(name = "房间ID")
    private Long roomId;

    /** 门牌图片 */
    @Excel(name = "门牌图片")
    private String roomImg;

    /** 状态（0送达 1待送） */
    @Excel(name = "状态", readConverterExp = "0=送达,1=待送")
    private String status;

    /** 租户名 */
    @Excel(name = "租户名")
    private String userName;

    /** 租户手机 */
    @Excel(name = "租户手机")
    private String userPhone;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setRoomImg(String roomImg) 
    {
        this.roomImg = roomImg;
    }

    public String getRoomImg() 
    {
        return roomImg;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserPhone(String userPhone) 
    {
        this.userPhone = userPhone;
    }

    public String getUserPhone() 
    {
        return userPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("apartmentId", getApartmentId())
            .append("unitId", getUnitId())
            .append("roomId", getRoomId())
            .append("roomImg", getRoomImg())
            .append("status", getStatus())
            .append("userName", getUserName())
            .append("userPhone", getUserPhone())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
