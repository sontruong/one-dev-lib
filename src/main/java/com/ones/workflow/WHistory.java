/**
 * 
 */
package com.ones.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ones.utils.ApplicationMessage;

import io.swagger.annotations.ApiModel;

/**
 * @author son.truong
 * Jun 22, 2018, 2:11:58 PM
 */
@ApiModel
@Entity
@Table(name = "w_history")
@JsonInclude(Include.NON_EMPTY)
public class WHistory implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false)
   private Long id;

   @NotNull(message = ApplicationMessage.WHISTORY_ENTITYTYPE_NULL)
   @Column(name = "entity_type", nullable = false)
   private String entityType;

   @NotNull(message = ApplicationMessage.WHISTORY_ENTITYID_NULL)
   @Column(name = "entity_id", nullable = false)
   private Long entityId;

   @NotNull(message = ApplicationMessage.WHISTORY_ACTEDBY_NULL)
   @Column(name = "acted_by", nullable = false)
   private Long actedBy;

   @NotNull(message = ApplicationMessage.WHISTORY_ACTEDON_NULL)
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "acted_on", nullable = false)
   private Date actedOn;

   @NotNull(message = ApplicationMessage.WHISTORY_ENTITYSTATUS_NULL)
   @Column(name = "entity_status", nullable = false)
   private String entityStatus;

   @Size(max = 255, message = ApplicationMessage.WHISTORY_COMMENT_RANGE_0_255)
   private String comment;

   @Size(max = 65535, message = ApplicationMessage.WHISTORY_DIFF_RANGE_0_65535)
   private String diff;

   /** constructor of WHistory*/
   public WHistory() {
      super();
   }

   public WHistory(Long id) {
      this.id = id;
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

   public static Specification<WHistory> createSpecification(final WHistoryFilterDTO wHistory) {
      return new Specification<WHistory>() {
         @Override
         public Predicate toPredicate(Root<WHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            /* query for field EntityType */
            Predicate predicateEntityType;
            if (null != wHistory.getEntityType() && !wHistory.getEntityType().isEmpty()) {
               Expression<String> path = root.get("entityType");
               predicateEntityType = cb.like(cb.lower(path), "%" + wHistory.getEntityType().toLowerCase() + "%");
            } else {
               predicateEntityType = cb.conjunction();
            }

            /* query for field EntityId */
            Predicate predicateEntityId;
            if (null != wHistory.getEntityId()) {
               Expression<Long> path = root.get("entityId");
               predicateEntityId = cb.equal(path, wHistory.getEntityId());
            } else {
               predicateEntityId = cb.conjunction();
            }

            /* query for field ActedBy */
            Predicate predicateActedBy;
            if (null != wHistory.getActedBy()) {
               Expression<Long> path = root.get("actedBy");
               predicateActedBy = cb.equal(path, wHistory.getActedBy());
            } else {
               predicateActedBy = cb.conjunction();
            }

            /* query for field ActedOn */
            Predicate predicateActedOn;
            Predicate predicateActedOnFrom;
            if (null != wHistory.getActedOnFrom()) {
               Expression<Date> path = root.get("actedOn");
               predicateActedOnFrom = cb.greaterThanOrEqualTo(path, wHistory.getActedOnFrom());
            } else {
               predicateActedOnFrom = cb.conjunction();
            }

            Predicate predicateActedOnTo;
            if (null != wHistory.getActedOnTo()) {
               Expression<Date> path = root.get("actedOn");
               predicateActedOnTo = cb.lessThanOrEqualTo(path, wHistory.getActedOnTo());
            } else {
               predicateActedOnTo = cb.conjunction();
            }

            predicateActedOn = cb.and(predicateActedOnFrom, predicateActedOnTo);

            /* query for field EntityStatus */
            Predicate predicateEntityStatus;
            if (null != wHistory.getEntityStatus() && !wHistory.getEntityStatus().isEmpty()) {
               Expression<String> path = root.get("entityStatus");
               predicateEntityStatus = cb.like(cb.lower(path), "%" + wHistory.getEntityStatus().toLowerCase() + "%");
            } else {
               predicateEntityStatus = cb.conjunction();
            }

            /* query for field Comment */
            Predicate predicateComment;
            if (null != wHistory.getComment() && !wHistory.getComment().isEmpty()) {
               Expression<String> path = root.get("comment");
               predicateComment = cb.like(cb.lower(path), "%" + wHistory.getComment().toLowerCase() + "%");
            } else {
               predicateComment = cb.conjunction();
            }

            /* query for field Diff */
            Predicate predicateDiff;
            if (null != wHistory.getDiff() && !wHistory.getDiff().isEmpty()) {
               Expression<String> path = root.get("diff");
               predicateDiff = cb.like(cb.lower(path), "%" + wHistory.getDiff().toLowerCase() + "%");
            } else {
               predicateDiff = cb.conjunction();
            }

            return cb.and(predicateEntityType, predicateEntityId, predicateActedBy, predicateActedOn,
                  predicateEntityStatus, predicateComment, predicateDiff);
         }
      };
   }

   public static Specification<WHistory> createSpecificationOr(final WHistoryFilterDTO wHistory) {
      return new Specification<WHistory>() {
         @Override
         public Predicate toPredicate(Root<WHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            /* query for field EntityType */
            Predicate predicateEntityType;
            if (null != wHistory.getEntityType() && !wHistory.getEntityType().isEmpty()) {
               Expression<String> path = root.get("entityType");
               predicateEntityType = cb.like(cb.lower(path), "%" + wHistory.getEntityType().toLowerCase() + "%");
            } else {
               predicateEntityType = cb.conjunction();
            }

            /* query for field EntityId */
            Predicate predicateEntityId;
            if (null != wHistory.getEntityId()) {
               Expression<Long> path = root.get("entityId");
               predicateEntityId = cb.equal(path, wHistory.getEntityId());
            } else {
               predicateEntityId = cb.conjunction();
            }

            /* query for field ActedBy */
            Predicate predicateActedBy;
            if (null != wHistory.getActedBy()) {
               Expression<Long> path = root.get("actedBy");
               predicateActedBy = cb.equal(path, wHistory.getActedBy());
            } else {
               predicateActedBy = cb.conjunction();
            }

            /* query for field ActedOn */
            Predicate predicateActedOn;
            Predicate predicateActedOnFrom;
            if (null != wHistory.getActedOnFrom()) {
               Expression<Date> path = root.get("actedOn");
               predicateActedOnFrom = cb.greaterThanOrEqualTo(path, wHistory.getActedOnFrom());
            } else {
               predicateActedOnFrom = cb.conjunction();
            }

            Predicate predicateActedOnTo;
            if (null != wHistory.getActedOnTo()) {
               Expression<Date> path = root.get("actedOn");
               predicateActedOnTo = cb.lessThanOrEqualTo(path, wHistory.getActedOnTo());
            } else {
               predicateActedOnTo = cb.conjunction();
            }

            predicateActedOn = cb.and(predicateActedOnFrom, predicateActedOnTo);

            /* query for field EntityStatus */
            Predicate predicateEntityStatus;
            if (null != wHistory.getEntityStatus() && !wHistory.getEntityStatus().isEmpty()) {
               Expression<String> path = root.get("entityStatus");
               predicateEntityStatus = cb.like(cb.lower(path), "%" + wHistory.getEntityStatus().toLowerCase() + "%");
            } else {
               predicateEntityStatus = cb.conjunction();
            }

            /* query for field Comment */
            Predicate predicateComment;
            if (null != wHistory.getComment() && !wHistory.getComment().isEmpty()) {
               Expression<String> path = root.get("comment");
               predicateComment = cb.like(cb.lower(path), "%" + wHistory.getComment().toLowerCase() + "%");
            } else {
               predicateComment = cb.conjunction();
            }

            /* query for field Diff */
            Predicate predicateDiff;
            if (null != wHistory.getDiff() && !wHistory.getDiff().isEmpty()) {
               Expression<String> path = root.get("diff");
               predicateDiff = cb.like(cb.lower(path), "%" + wHistory.getDiff().toLowerCase() + "%");
            } else {
               predicateDiff = cb.conjunction();
            }

            return cb.or(predicateEntityType, predicateEntityId, predicateActedBy, predicateActedOn,
                  predicateEntityStatus, predicateComment, predicateDiff);
         }
      };
   }
}
