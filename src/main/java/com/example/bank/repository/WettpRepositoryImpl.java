package com.example.bank.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bank.model.Wettopup;
import com.example.bank.model.Wettp;
import com.example.bank.model.Wetwithdraw;

public class WettpRepositoryImpl implements WettpCustomRepository{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Double player_transferin_amount(Date fromdate, Date todate, Integer companyid, String touserid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.eq("touser", "touserid")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double player_transferout_amount(Date fromdate, Date todate, Integer companyid, String touserid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.eq("frmuser", "touserid")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double player_transferin__report_amount(Date fromdate, Date todate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("touser")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double player_transferout_report_amount(Date fromdate, Date todate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("frmuser")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_in(Date fromdate, Integer companyid, String product) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.eq("toproduct", product)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_out(Date fromdate, Integer companyid, String product) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.eq("frmproduct", product)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_in_total(Date fromdate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("toproduct")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_out_total(Date fromdate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("frmproduct")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowproduct_transferout(String userid, Integer companyid, String productid, Date fromdate,
			Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("frmuser", userid),
				            Restrictions.eq("frmproduct", productid),
				            Restrictions.eq("status", "Posted")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowproduct_transferin(String userid, Integer companyid, String productid, Date fromdate,
			Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("touser", userid),
				            Restrictions.eq("toproduct", productid),
				            Restrictions.eq("status", "Posted")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowproduct_transferout_total(Integer companyid, String productid, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("frmproduct", productid),
				            Restrictions.eq("status", "Posted")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowproduct_transferin_total(Integer companyid, String productid, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("toproduct", productid),
				            Restrictions.eq("status", "Posted")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wettp> daily_mix_wettp(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		           /* Restrictions.in("promotioncd", userid),*/
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.in("frmproduct", productid)
		       
		           
		        )
		        
		    );
	 
	 
	   
       return cr.list();
	}

	@Override
	public Double daily_mix_wettp_amount(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.in("frmproduct", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double daily_mix_wettp_bonus(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long daily_mix_wettp_trancid(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.in("frmproduct", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.count("trancid"));
		return (Long) cr.uniqueResult();
	}

	

	@Override
	public List<Wettp> outside_report_unclaim(Integer companyid, List<String> frmproductid, List<String> toproductid,
			Date fromdate, Date todate) {
		String SQL_Query = "select sum(Amount) from WET_TP where company_id =:companyid\r\n" + 
				"and FRM_PRODUCT In (:frmproductid)\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and To_PRODUCT In (:toproductid) \r\n"+
				"and (Bank_APPROVEBY is null or CS_APPROVEBY is null)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("frmproductid", frmproductid);
		query.setParameter("toproductid", toproductid);
		query.setParameter("companyid", companyid);
		
		List<Wettp> list = query.list();
		return list;
	}

	@Override
	public List<Wettp> outside_report_claim(Integer companyid, List<String> frmproductid, List<String> toproductid,
			Date fromdate, Date todate, List<String> status) {
		String SQL_Query = "select sum(Amount) from WET_TP where company_id =:companyid\r\n" + 
				"and FRM_PRODUCT In (:frmproductid)\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and To_PRODUCT In (:toproductid) \r\n"+
				"and (Bank_APPROVEBY is not null and CS_APPROVEBY is not null) and type In (:status) " ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("frmproductid", frmproductid);
		query.setParameter("toproductid", toproductid);
		query.setParameter("companyid", companyid);
		query.setParameter("status", status);
		
		List<Wettp> list = query.list();
		return list;
	}

	@Override
	public List<Wettp> cashflowproduct_tp(Date fromdate, Date todate, Integer companyid, String productid) {
		String SQL_Query = "select DISTINCT(FRM_USER),FRM_PRODUCT FROM wet_tp WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted' and FRM_Product =:frmproduct";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("frmproduct", productid);
		
		List<Wettp> list = query.list();
		return list;
	}

	@Override
	public List<Wettp> cashflowproduct_tp_toproduct(Date fromdate, Date todate, Integer companyid, String productid) {
		String SQL_Query = "select DISTINCT(TO_USER),TO_PRODUCT FROM wet_tp WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted' and TO_Product =:toproductid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("toproductid", productid);
		
		List<Wettp> list = query.list();
		return list;
	}

	@Override
	public Double daily_mix_wettp_transferin_amount(Integer companyid, Date fromdate, Date todate,
			List<String> productid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettp.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.in("toproduct", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

}
