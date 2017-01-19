package com.ray.demo.admin.model.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysRoleExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public SysRoleExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andRolenameIsNull() {
			addCriterion("rolename is null");
			return (Criteria) this;
		}

		public Criteria andRolenameIsNotNull() {
			addCriterion("rolename is not null");
			return (Criteria) this;
		}

		public Criteria andRolenameEqualTo(String value) {
			addCriterion("rolename =", value, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameNotEqualTo(String value) {
			addCriterion("rolename <>", value, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameGreaterThan(String value) {
			addCriterion("rolename >", value, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameGreaterThanOrEqualTo(String value) {
			addCriterion("rolename >=", value, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameLessThan(String value) {
			addCriterion("rolename <", value, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameLessThanOrEqualTo(String value) {
			addCriterion("rolename <=", value, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameLike(String value) {
			addCriterion("rolename like", value, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameNotLike(String value) {
			addCriterion("rolename not like", value, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameIn(List<String> values) {
			addCriterion("rolename in", values, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameNotIn(List<String> values) {
			addCriterion("rolename not in", values, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameBetween(String value1, String value2) {
			addCriterion("rolename between", value1, value2, "rolename");
			return (Criteria) this;
		}

		public Criteria andRolenameNotBetween(String value1, String value2) {
			addCriterion("rolename not between", value1, value2, "rolename");
			return (Criteria) this;
		}

		public Criteria andCreatetimeIsNull() {
			addCriterion("createtime is null");
			return (Criteria) this;
		}

		public Criteria andCreatetimeIsNotNull() {
			addCriterion("createtime is not null");
			return (Criteria) this;
		}

		public Criteria andCreatetimeEqualTo(Date value) {
			addCriterion("createtime =", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeNotEqualTo(Date value) {
			addCriterion("createtime <>", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeGreaterThan(Date value) {
			addCriterion("createtime >", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
			addCriterion("createtime >=", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeLessThan(Date value) {
			addCriterion("createtime <", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
			addCriterion("createtime <=", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeIn(List<Date> values) {
			addCriterion("createtime in", values, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeNotIn(List<Date> values) {
			addCriterion("createtime not in", values, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeBetween(Date value1, Date value2) {
			addCriterion("createtime between", value1, value2, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
			addCriterion("createtime not between", value1, value2, "createtime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeIsNull() {
			addCriterion("updatetime is null");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeIsNotNull() {
			addCriterion("updatetime is not null");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeEqualTo(Date value) {
			addCriterion("updatetime =", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeNotEqualTo(Date value) {
			addCriterion("updatetime <>", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeGreaterThan(Date value) {
			addCriterion("updatetime >", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
			addCriterion("updatetime >=", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeLessThan(Date value) {
			addCriterion("updatetime <", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
			addCriterion("updatetime <=", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeIn(List<Date> values) {
			addCriterion("updatetime in", values, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeNotIn(List<Date> values) {
			addCriterion("updatetime not in", values, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeBetween(Date value1, Date value2) {
			addCriterion("updatetime between", value1, value2, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
			addCriterion("updatetime not between", value1, value2, "updatetime");
			return (Criteria) this;
		}

		public Criteria andCreatorIsNull() {
			addCriterion("creator is null");
			return (Criteria) this;
		}

		public Criteria andCreatorIsNotNull() {
			addCriterion("creator is not null");
			return (Criteria) this;
		}

		public Criteria andCreatorEqualTo(Long value) {
			addCriterion("creator =", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorNotEqualTo(Long value) {
			addCriterion("creator <>", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorGreaterThan(Long value) {
			addCriterion("creator >", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorGreaterThanOrEqualTo(Long value) {
			addCriterion("creator >=", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorLessThan(Long value) {
			addCriterion("creator <", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorLessThanOrEqualTo(Long value) {
			addCriterion("creator <=", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorIn(List<Long> values) {
			addCriterion("creator in", values, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorNotIn(List<Long> values) {
			addCriterion("creator not in", values, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorBetween(Long value1, Long value2) {
			addCriterion("creator between", value1, value2, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorNotBetween(Long value1, Long value2) {
			addCriterion("creator not between", value1, value2, "creator");
			return (Criteria) this;
		}

		public Criteria andUpdaterIsNull() {
			addCriterion("updater is null");
			return (Criteria) this;
		}

		public Criteria andUpdaterIsNotNull() {
			addCriterion("updater is not null");
			return (Criteria) this;
		}

		public Criteria andUpdaterEqualTo(Long value) {
			addCriterion("updater =", value, "updater");
			return (Criteria) this;
		}

		public Criteria andUpdaterNotEqualTo(Long value) {
			addCriterion("updater <>", value, "updater");
			return (Criteria) this;
		}

		public Criteria andUpdaterGreaterThan(Long value) {
			addCriterion("updater >", value, "updater");
			return (Criteria) this;
		}

		public Criteria andUpdaterGreaterThanOrEqualTo(Long value) {
			addCriterion("updater >=", value, "updater");
			return (Criteria) this;
		}

		public Criteria andUpdaterLessThan(Long value) {
			addCriterion("updater <", value, "updater");
			return (Criteria) this;
		}

		public Criteria andUpdaterLessThanOrEqualTo(Long value) {
			addCriterion("updater <=", value, "updater");
			return (Criteria) this;
		}

		public Criteria andUpdaterIn(List<Long> values) {
			addCriterion("updater in", values, "updater");
			return (Criteria) this;
		}

		public Criteria andUpdaterNotIn(List<Long> values) {
			addCriterion("updater not in", values, "updater");
			return (Criteria) this;
		}

		public Criteria andUpdaterBetween(Long value1, Long value2) {
			addCriterion("updater between", value1, value2, "updater");
			return (Criteria) this;
		}

		public Criteria andUpdaterNotBetween(Long value1, Long value2) {
			addCriterion("updater not between", value1, value2, "updater");
			return (Criteria) this;
		}

		public Criteria andRolecodeIsNull() {
			addCriterion("rolecode is null");
			return (Criteria) this;
		}

		public Criteria andRolecodeIsNotNull() {
			addCriterion("rolecode is not null");
			return (Criteria) this;
		}

		public Criteria andRolecodeEqualTo(String value) {
			addCriterion("rolecode =", value, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeNotEqualTo(String value) {
			addCriterion("rolecode <>", value, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeGreaterThan(String value) {
			addCriterion("rolecode >", value, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeGreaterThanOrEqualTo(String value) {
			addCriterion("rolecode >=", value, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeLessThan(String value) {
			addCriterion("rolecode <", value, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeLessThanOrEqualTo(String value) {
			addCriterion("rolecode <=", value, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeLike(String value) {
			addCriterion("rolecode like", value, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeNotLike(String value) {
			addCriterion("rolecode not like", value, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeIn(List<String> values) {
			addCriterion("rolecode in", values, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeNotIn(List<String> values) {
			addCriterion("rolecode not in", values, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeBetween(String value1, String value2) {
			addCriterion("rolecode between", value1, value2, "rolecode");
			return (Criteria) this;
		}

		public Criteria andRolecodeNotBetween(String value1, String value2) {
			addCriterion("rolecode not between", value1, value2, "rolecode");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table sys_role
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_role
     *
     * @mbggenerated do_not_delete_during_merge Mon Aug 08 16:58:03 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}