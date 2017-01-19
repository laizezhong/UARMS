package com.ray.framework.dao.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wb-guoshengwang on 2015/6/8.
 */
public class BaseMixUserObject  implements Serializable {
	private static final long serialVersionUID = 3104947810803601916L;
	public static final String STATUS_CREATOR_TYPE_MEMBER = "memeber";  //状态创建人类型：会员
    public static final String STATUS_CREATOR_TYPE_SERVANT = "servant"; //状态创建人类型：管理员
    public static final String STATUS_CREATOR_TYPE_SHOP = "shop"; //状态创建人类型：店铺
    //主键 id
    private Long id;
    //是否逻辑删除
    private boolean isDeleted = false;
    //创建者类型
    private String creatorType;
    //记录业务创建人
    private Long creatorID;
    //修改者类型
    private String modifierType;
    //修改者
    private Long modifierID;
    /**
     * 这个统一为业务创建时间，也就是业务无需再添加时间字段，比如：成功支付时间。
     * 使用数据库服务器时间。如果有NTP保障，那么也可以使用应用服务器时间。
     */
    private Date createDatetime = new Date();
    /**
     * 一般指记录的数据库修改时间，除非特别在乎时间（数据库延迟排错时），否则也可以当做对象修改时间直接用。
     */
    private Date modifyDatetime = new Date();
    /**
     * 管理员备注字段
     */
    private String adminMemo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    public Long getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Long creatorID) {
        this.creatorID = creatorID;
    }

    public String getModifierType() {
        return modifierType;
    }

    public void setModifierType(String modifierType) {
        this.modifierType = modifierType;
    }

    public Long getModifierID() {
        return modifierID;
    }

    public void setModifierID(Long modifierID) {
        this.modifierID = modifierID;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getModifyDatetime() {
        return modifyDatetime;
    }

    public void setModifyDatetime(Date modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

    public String getAdminMemo() {
        return adminMemo;
    }

    public void setAdminMemo(String adminMemo) {
        this.adminMemo = adminMemo;
    }
}
