/**
 * 
 */
package com.ones.workflow;

import java.util.Date;

import com.ones.dto.BaseFilter;

/**
 * @author son.truong
 * Jun 22, 2018, 2:11:58 PM
 */
public class WHistoryFilterDTO extends BaseFilter {

   private Long id;
   private String entityType;
   private Long entityId;
   private Long actedBy;
   private Date actedOnFrom;
   private Date actedOnTo;
   private String entityStatus;
   private String comment;
   private String diff;

   /** constructor of WHistoryFilterDTO*/
   public WHistoryFilterDTO() {
      super();
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getEntityType() {
      return this.entityType;
   }

   public void setEntityType(String entityType) {
      this.entityType = entityType;
   }

   public Long getEntityId() {
      return this.entityId;
   }

   public void setEntityId(Long entityId) {
      this.entityId = entityId;
   }

   public Long getActedBy() {
      return this.actedBy;
   }

   public void setActedBy(Long actedBy) {
      this.actedBy = actedBy;
   }

   public String getEntityStatus() {
      return this.entityStatus;
   }

   public void setEntityStatus(String entityStatus) {
      this.entityStatus = entityStatus;
   }

   public String getComment() {
      return this.comment;
   }

   public void setComment(String comment) {
      this.comment = comment;
   }

   public String getDiff() {
      return this.diff;
   }

   public void setDiff(String diff) {
      this.diff = diff;
   }

   public Date getActedOnFrom() {
      return this.actedOnFrom;
   }

   public void setActedOnFrom(Date actedOnFrom) {
      this.actedOnFrom = actedOnFrom;
   }

   public Date getActedOnTo() {
      return this.actedOnTo;
   }

   public void setActedOnTo(Date actedOnTo) {
      this.actedOnTo = actedOnTo;
   }

}
