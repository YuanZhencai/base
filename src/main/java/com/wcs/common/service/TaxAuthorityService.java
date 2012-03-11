package com.wcs.common.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wcs.common.controller.vo.TaxAuthorityVO;
import com.wcs.common.model.Taxauthority;

@Stateless
public class TaxAuthorityService implements Serializable {

	private static final long serialVersionUID = -7707410191691768066L;
	@PersistenceContext
	public EntityManager em;

	// 查询方法
	// @SuppressWarnings({ "unchecked" })
	// public List<TaxAuthorityVO> queryDataPlan(String taxName,
	// String taxAddress, String taxState) {
	//
	// StringBuilder strSql = new StringBuilder(
	// "select t.id,t.name,t.address,t.zipcode,t.telphone,t.defunctInd,"
	// + "t.leaderName,t.leaderPosition,t.leaderTelphone, "
	// + "t.contacterName,t.contacterPosition,t.contacterTelphone "
	// + " from Taxauthority t where 1=1 ");
	// if (taxName != null && taxName != "") {
	// strSql.append(" AND t.name LIKE '%" + taxName + "%' ");
	// }
	// if (taxAddress != null && taxAddress != "") {
	// strSql.append(" AND t.address LIKE '%" + taxAddress + "%' ");
	// }
	// if (taxState != null && taxState != "") {
	// strSql.append(" AND t.defunctInd = '" + taxState + "'");
	// }
	//
	// Query query = em.createQuery(strSql.toString());
	// List<Object[]> result = query.getResultList();
	// List<TaxAuthorityVO> list = new ArrayList<TaxAuthorityVO>();
	// for (int i = 0; i < result.size(); i++) {
	// Object[] b = result.get(i);
	// list.add(new TaxAuthorityVO(
	// Integer.valueOf(b[0].toString()) ,//ID
	// (b[1]).toString(), // 姓名
	// (b[2]).toString(), // 地址
	// b[3]==null?"":(b[4]).toString(), // 邮编
	// b[4]==null?"":(b[3]).toString(), // 电话
	// b[5]==null?"":(b[5]).toString(), // 状态
	// b[6]==null?"":(b[6]).toString(), // 领导3个,姓名,职位,电话
	// b[7]==null?"":(b[7]).toString(),
	// b[8]==null?"":(b[8]).toString(),
	// b[9]==null?"":(b[9]).toString(), // 联系人3个
	// b[10]==null?"":(b[10]).toString(),
	// b[11]==null?"":(b[11]).toString()
	// )
	// );
	// }
	// return list;
	// }
	public List<TaxAuthorityVO> queryDataPlan(String taxName,
			String taxAddress, String taxState) {

		StringBuilder strSql = new StringBuilder(
				"select t from Taxauthority t where 1=1 ");
		if (taxName != null && taxName != "") {
			strSql.append(" AND t.name LIKE '%" + taxName + "%' ");
		}
		if (taxAddress != null && taxAddress != "") {
			strSql.append(" AND t.address LIKE '%" + taxAddress + "%' ");
		}
		if (taxState != null && taxState != "") {
			strSql.append(" AND t.defunctInd = '" + taxState + "'");
		}

		Query query = em.createQuery(strSql.toString());
		List<Taxauthority> result = query.getResultList();
		List<TaxAuthorityVO> list = new ArrayList<TaxAuthorityVO>();
		for (Taxauthority t : result) {
			list.add(new TaxAuthorityVO(t.getId(), t.getName(), t.getAddress(),
					t.getZipcode(), t.getTelphone(), t.getDefunctInd(), t
							.getLeaderName(), t.getLeaderPosition(), t
							.getLeaderTelphone(), t.getContacterName(), t
							.getContacterPosition(), t.getContacterTelphone()));
		}
		return list;
	}

	// 添加方法
	public void addTaxManage(String addName, String addAddress,
			String addZipCode, String addTelephone, String addLeaderName,
			String addLeaderPosition, String addLeaderPhone,
			String addContacterName, String addContacterPosition,
			String addContacterPhone, String addState, String createUser) {

		Taxauthority taxauthority = new Taxauthority();
		taxauthority.setName(addName);
		taxauthority.setAddress(addAddress);
		taxauthority.setZipcode(addZipCode);
		taxauthority.setTelphone(addTelephone);
		taxauthority.setDefunctInd(addState);
		taxauthority.setLeaderName(addLeaderName);
		taxauthority.setLeaderPosition(addLeaderPosition);
		taxauthority.setLeaderTelphone(addLeaderPhone);
		taxauthority.setContacterName(addContacterName);
		taxauthority.setContacterPosition(addContacterPosition);
		taxauthority.setContacterTelphone(addContacterPhone);
		taxauthority.setCreatedBy(createUser);
		taxauthority.setCreatedDatetime(new Date());
		// 下面两个字段在有些表中是不能为空的,所以为了统一,第一次添加,统一添加创建信息的用户名和当前时间.
		taxauthority.setUpdatedBy(createUser);
		taxauthority.setUpdatedDatetime(new Date());
		this.em.persist(taxauthority);

	}

	// 更新方法
	public void modifyData(TaxAuthorityVO selectData, String updateUser) {
		StringBuilder strSql = new StringBuilder("UPDATE Taxauthority t ");
		strSql.append("SET t.name= '" + selectData.getTaxName() + "',");
		strSql.append("t.address= '" + selectData.getTaxAddress() + "',");
		strSql.append("t.zipcode= '" + selectData.getTaxZipCode() + "',");
		strSql.append("t.telphone= '" + selectData.getTaxPhone() + "',");
		strSql.append("t.leaderName= '" + selectData.getLeaderName() + "',");
		strSql.append("t.leaderPosition= '" + selectData.getLeaderPosition()
				+ "',");
		strSql.append("t.leaderTelphone= '" + selectData.getLeaderPhone()
				+ "',");
		strSql.append("t.contacterName= '" + selectData.getContacterName()
				+ "',");
		strSql.append("t.contacterPosition= '"
				+ selectData.getContacterPosition() + "',");
		strSql.append("t.contacterTelphone= '" + selectData.getContacterPhone()
				+ "',");
		strSql.append("t.defunctInd= '" + selectData.getTaxState() + "',");
		strSql.append("t.updatedBy='" + updateUser + "',");
		strSql.append("t.updatedDatetime= '"
				+ new SimpleDateFormat("yyyy-MM-dd HH:ss:mm")
						.format(new Date()) + "'");
		strSql.append(" where t.id=" + Long.valueOf(selectData.getId()) + "");
		this.em.createQuery(strSql.toString()).executeUpdate();
		// this.em.merge(selectData);

	}
}
