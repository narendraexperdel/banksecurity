package com.example.bank.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Expensestype;
import com.example.bank.model.TelePending;
import com.example.bank.model.Wettopup;

@Component
public class TelePendingRepositoryImpl implements TelePendingCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	TelePendingRepository telependingRepository;

	@Override
	public List<TelePending> checktelepending(String userid, String generatedfrom, Integer companyid) {
		Criteria cre = entityManager.unwrap(Session.class).createCriteria(TelePending.class);
		cre.add(Restrictions.eq("companyid.id", companyid));
		cre.add(Restrictions.eq("userid", userid));
		cre.add(Restrictions.eq("gentype", generatedfrom));
	
		return (List<TelePending>) cre.list();
	}

	@Transactional
	@Override
	public void savetelepending(TelePending telepending) {
		String SQL_Query = "\r\n" + 
				"Insert into TELE_PEnding( ISSUEDBY, TELNO, USER_ID, ISSUEDDT, GEN_TYPE, RESOLUTIONDT , COMPANY_ID , NICKNAME, \r\n" + 
				"WCWS, SOURCE_ID, PRODUCT,ISSUED_DATE )\r\n" + 
				"VALUES(:issuedby,:telno,:userid,:issueddt, :gentype, :resolutiondt, :companyid,\r\n" + 
				":nickname, :wcws, :sourceid, :product, :issueddate)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("issuedby", telepending.getIssuedby());
		query.setParameter("telno", telepending.getTelno());
		query.setParameter("userid", telepending.getUserid());
		query.setParameter("issueddt", telepending.getIssueddt());
		query.setParameter("gentype", telepending.getGentype());
		query.setParameter("resolutiondt", telepending.getResolutiondt());
		query.setParameter("companyid", telepending.getCompanyid());
		query.setParameter("nickname", telepending.getNickname());
		query.setParameter("wcws", telepending.getWcws());
		query.setParameter("sourceid", telepending.getSources());
		query.setParameter("product", telepending.getProduct());
		query.setParameter("issueddate", telepending.getIssued_date());
		
		int result =  query.executeUpdate();
		
	}

	@Override
	public List<TelePending> countofteleremark(Date fromdate, Date todate, String remark, String assignto,Integer companyid) {
		
		String SQL_Query = "\r\n" + 
				"select Count(PENDING_ID) from TELE_PENDING where \r\n" + 
				"COMPANY_ID =:companyid and (REMARK1 =:remark OR REMARK2 =:remark OR REMARK3 =:remark OR REMARK4 =:remark) and cast(ISSUEDDT as date) between :fromdate and :todate and ASSIGN_TO =:assignto";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("remark", remark);
		query.setParameter("assignto", assignto);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		
		List<TelePending> list = query.list();
		return list;
	}

	@Override
	public List<TelePending> countofteleremark_total(Date fromdate, Date todate, String remark, Integer companyid) {
		String SQL_Query = "\r\n" + 
				"select Count(PENDING_ID) from TELE_PENDING where \r\n" + 
				"COMPANY_ID =:companyid and (REMARK1 =:remark OR REMARK2 =:remark OR REMARK3 =:remark OR REMARK4 =:remark) and cast(ISSUEDDT as date) between :fromdate and :todate";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("remark", remark);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		
		List<TelePending> list = query.list();
		return list;
	}

	@Override
	public List<TelePending> distinctuserid(Date fromdate, Date todate, String assignto, Integer companyid) {
		String SQL_Query = "\r\n" + 
				"select DISTINCT(USER_ID),ISSUEDDT,RESOLUTIONDT from TELE_PENDING where \r\n" + 
				"COMPANY_ID =:companyid and cast(ISSUEDDT as date) between :fromdate and :todate and ASSIGN_TO =:assignto";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("assignto", assignto);
		
		List<TelePending> list = query.list();
		return list;
	}

	@Override
	public List<String> distinctassign(Date fromdate, Date todate, String assignto, Integer companyid) {
		String SQL_Query = "\r\n" + 
				"select DISTINCT(GEN_TYPE) from TELE_PENDING where \r\n" + 
				"COMPANY_ID =:companyid and cast(ISSUEDDT as date) between :fromdate and :todate and ASSIGN_TO =:assignto";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("assignto", assignto);
		
		List<String> list = query.list();
		return list;
	}

	@Override
	public List<String> distinctuserid_closesum(Date fromdate, Date todate, String assignto, String gentype,
			Integer companyid) {
		String SQL_Query = "\r\n" + 
				"select DISTINCT(USER_ID) from TELE_PENDING where \r\n" + 
				"COMPANY_ID =:companyid and cast(ISSUEDDT as date) between :fromdate and :todate  and ASSIGN_TO =:assignto and GEN_TYPE =:gentype";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("assignto", assignto);
		query.setParameter("gentype", gentype);
		
		List<String> list = query.list();
		return list;
	}

}
