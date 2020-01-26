package com.example.bank.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.ReferalBonus;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;

public class ReferalbonusRepositoryImpl implements ReferalbonusCustomRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Double productdaily_referal(Date fromdate, Integer companyid, String product) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(ReferalBonus.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.eq("product", product)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_referal_total(Date fromdate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(ReferalBonus.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowproduct_referal(String userid, Integer companyid, String productid, Date fromdate,
			Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(ReferalBonus.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.eq("product", productid),
				            Restrictions.eq("userid", userid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowproduct_referal_total( Integer companyid, String productid, Date fromdate,
			Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(ReferalBonus.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.eq("product", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowproduct_referal_report_total(Integer companyid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(ReferalBonus.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<ReferalBonus> cashflowproduct_referal(Date fromdate, Date todate, Integer companyid, String productid) {
		String SQL_Query = "select DISTINCT(USER_ID),PRODUCT FROM REFERAL_BONUS WHERE issueddate between :fromdate and :todate and company_id = :companyid and status = 'Posted' and product =:productid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("productid", productid);
		
		List<ReferalBonus> list = query.list();
		return list;
	}

	@Override
	public List<ReferalBonus> outside_report_referal_pending(Integer companyid, List<String> productid, Date fromdate,
			Date todate) {
		String SQL_Query = "select sum(Amount) from Referal_Bonus where company_id =:companyid\r\n" + 
				"and issueddate between :fromdate and :todate\r\n" +
				"and status is NULL\r\n" +
				"and  (BANK_Done_By is null or CSAPPROVEBY is  null)"
				+ "and Product In (:productid)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		
		List<ReferalBonus> list = query.list();
		return list;
	}

	@Override
	public List<ReferalBonus> outside_report_referal_done(Integer companyid, List<String> productid, Date fromdate,
			Date todate) {
		String SQL_Query = "select sum(Amount) from Referal_Bonus where company_id =:companyid\r\n" + 
				"and issueddate between :fromdate and :todate\r\n" +
				"and  (status = 'Posted' or Status = 'Cancel')"
				+ "and Product In (:productid)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		
		List<ReferalBonus> list = query.list();
		return list;
	}
}
