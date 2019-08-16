/**
 * 
 */
package com.ones.workflow;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ones.utils.ApplicationMessage;

/**
 * @author son.truong
 * Jun 22, 2018, 2:11:58 PM
 */
public class WHistoryDTO {

   private Long id;
   @NotNull(message = ApplicationMessage.WHISTORY_ENTITYTYPE_NULL)
   private String entityType;
   @NotNull(message = ApplicationMessage.WHISTORY_ENTITYID_NULL)
   private Long entityId;
   @NotNull(message = ApplicationMessage.WHISTORY_ACTEDBY_NULL)
   private Long actedBy;
   @NotNull(message = ApplicationMessage.WHISTORY_ACTEDON_NULL)
   private Date actedOn;
   @NotNull(message = ApplicationMessage.WHISTORY_ENTITYSTATUS_NULL)
   private String entityStatus;
   @Size(max = 255, message = ApplicationMessage.WHISTORY_COMMENT_RANGE_0_255)
   private String comment;
   @Size(max = 65535, message = ApplicationMessage.WHISTORY_DIFF_RANGE_0_65535)
   private String diff;

   /** constructor of WHistoryDTO*/
   public WHistoryDTO() {
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

   public Date getActedOn() {
      return this.actedOn;
   }

   public void setActedOn(Date actedOn) {
      this.actedOn = actedOn;
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

}
