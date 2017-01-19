package com.ray.demo.admin.model.master;

import java.io.Serializable;
import java.util.Date;

public class SellerAttachment implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seller_attachment.seller_id
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    private Long sellerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seller_attachment.attachment_url
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    private String attachmentUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seller_attachment.attachment_name
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    private String attachmentName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seller_attachment.delete_flag
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    private Integer deleteFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seller_attachment.creator
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    private Long creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seller_attachment.create_time
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seller_attachment.modifier
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    private Long modifier;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seller_attachment.modify_time
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table seller_attachment
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seller_attachment.seller_id
     *
     * @return the value of seller_attachment.seller_id
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public Long getSellerId() {
        return sellerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seller_attachment.seller_id
     *
     * @param sellerId the value for seller_attachment.seller_id
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seller_attachment.attachment_url
     *
     * @return the value of seller_attachment.attachment_url
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seller_attachment.attachment_url
     *
     * @param attachmentUrl the value for seller_attachment.attachment_url
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl == null ? null : attachmentUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seller_attachment.attachment_name
     *
     * @return the value of seller_attachment.attachment_name
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public String getAttachmentName() {
        return attachmentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seller_attachment.attachment_name
     *
     * @param attachmentName the value for seller_attachment.attachment_name
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName == null ? null : attachmentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seller_attachment.delete_flag
     *
     * @return the value of seller_attachment.delete_flag
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seller_attachment.delete_flag
     *
     * @param deleteFlag the value for seller_attachment.delete_flag
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seller_attachment.creator
     *
     * @return the value of seller_attachment.creator
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seller_attachment.creator
     *
     * @param creator the value for seller_attachment.creator
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seller_attachment.create_time
     *
     * @return the value of seller_attachment.create_time
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seller_attachment.create_time
     *
     * @param createTime the value for seller_attachment.create_time
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seller_attachment.modifier
     *
     * @return the value of seller_attachment.modifier
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public Long getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seller_attachment.modifier
     *
     * @param modifier the value for seller_attachment.modifier
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seller_attachment.modify_time
     *
     * @return the value of seller_attachment.modify_time
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seller_attachment.modify_time
     *
     * @param modifyTime the value for seller_attachment.modify_time
     *
     * @mbggenerated Tue Aug 02 15:32:54 CST 2016
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}