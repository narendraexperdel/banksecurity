package com.example.bank.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bank.model.Wettopup;

public class WettopupRepositoryImpl implements WettopupCustomRepository{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Wettopup> getallUnclaimlist() {
		 Criteria cr = entityManager.unwrap(Session.class).createCriteria(Wettopup.class);
		   cr.add(Restrictions.eq("claim", "N"));
	       return cr.list();
	}

	@Override
	public List<Wettopup> getpromoredem(Date fromdate, Date todate, List<String> promoname, List<String> userid, Integer companyid) {
		Criteria cr = null;
		List<Wettopup> wettopup = new ArrayList<Wettopup>();
		if(!userid.isEmpty()) {
		  cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
			        Restrictions.and
			        
			        (
			        		
			            Restrictions.eq("companyid.id", companyid),
			            Restrictions.in("promotioncd", promoname),
			            Restrictions.in("userid", userid),
			            Restrictions.between("issueddate", fromdate, todate),
			            Restrictions.eq("status", "Posted")
			           /* Restrictions.eq("adjustmenttype", NULL),
			            Restrictions.eq("adjustmentcategory", null)*/
			           
			        )
			    );
		 
		 /*ProjectionList projectionList = Projections.projectionList();
		    projectionList.add(Projections.groupProperty("promotioncd"));
		    cr.setProjection(projectionList);*/
		 
		 
		 
		  /* cr.add(Restrictions.between("issueddate", fromdate, todate));
		   cr.add(Restrictions.lt("issueddate", todate));
	   cr.add(Restrictions.in("promotioncd", promoname));
		   cr.add(Restrictions.in("userid", userid));
	   cr.add(Restrictions.eq("companyid.id", companyid));
		 cr.add(Restrictions.eq("status", "Posted"));*/
		   
	       return cr.list();
		}else {
			return wettopup;
		}
	}

	@Override
	public List<Wettopup> gettopupbypromo(Date fromdate, Date todate, String promoname, Integer companyid) {
		 Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class);
		 cr.add(Restrictions.between("issueddate", fromdate, todate));
	   cr.add(Restrictions.eq("userid", promoname));  
	   cr.add(Restrictions.eq("companyid.id", companyid));
		 cr.add(Restrictions.eq("status", "Posted"));
		/* cr.add(Restrictions.eq("adjustmenttype", null));
		 cr.add(Restrictions.eq("adjustmentcategory", "NULL"));*/
		 
		return cr.list();
	}

	@Override
	public List<Wettopup> getpromobyuserid(Date fromdate, Date todate, String userid, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		           /* Restrictions.in("promotioncd", userid),*/
		            Restrictions.in("userid", userid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted")
		           /* Restrictions.eq("adjustmenttype", NULL),
		            Restrictions.eq("adjustmentcategory", null)*/
		           
		        )
		    );
	 
	 /*ProjectionList projectionList = Projections.projectionList();
	    projectionList.add(Projections.groupProperty("promotioncd"));
	    cr.setProjection(projectionList);*/
	 
	 
	 
	  /* cr.add(Restrictions.between("issueddate", fromdate, todate));
	   cr.add(Restrictions.lt("issueddate", todate));
   cr.add(Restrictions.in("promotioncd", promoname));
	   cr.add(Restrictions.in("userid", userid));
   cr.add(Restrictions.eq("companyid.id", companyid));
	 cr.add(Restrictions.eq("status", "Posted"));*/
	   
       return cr.list();
	}

	@Override
	public Double getamountbypromo(Date fromdate, Date todate, String userid, String promoname, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("promotioncd", promoname),
		            Restrictions.eq("userid", userid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted")
		           /* Restrictions.eq("adjustmenttype", NULL),
		            Restrictions.eq("adjustmentcategory", null)*/
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double getamountbyuserid(Date fromdate, Date todate, String userid, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("userid", userid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted")
		           /* Restrictions.eq("adjustmenttype", NULL),
		            Restrictions.eq("adjustmentcategory", null)*/
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<String> getuseridbytopuo(Date fromdate, Date todate, Integer companyid) {
		 Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class);
		 cr.add(Restrictions.between("issueddate", fromdate, todate)); 
	   cr.add(Restrictions.eq("companyid.id", companyid));
		 cr.add(Restrictions.eq("status", "Posted"));
		/* cr.add(Restrictions.eq("adjustmenttype", null));
		 cr.add(Restrictions.eq("adjustmentcategory", "NULL"));*/
		 
		 cr.setProjection( Projections.distinct( Projections.property( "userid" ) ) );
		return cr.list();
	}

	@Override
	public Double getsumamountbyuserid(String userid,String productid, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("userid", userid),
		            Restrictions.eq("productid", productid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNull("adjustmenttype"),
			   		 Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wettopup> topupfornonactive(String userid, String productid, Integer companyid) {
		 Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class);
		
	     cr.add(Restrictions.eq("userid", userid));  
	     cr.add(Restrictions.eq("productid", productid));
	     cr.add(Restrictions.isNotNull("adjustmenttype"));
		 cr.add(Restrictions.ne("adjustmenttype", "Master"));
		 cr.add(Restrictions.eq("status", "Posted"));	
		 cr.add(Restrictions.eq("companyid.id", companyid)); 
		 
		return cr.list();
	}

	@Override
	public List<Wettopup> topupfornonactiveexcludefree(String userid, String productid, Integer companyid, Date fromdate,Date todate ) {
		 Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class);
			
	     cr.add(Restrictions.eq("userid", userid));  
	     cr.add(Restrictions.eq("productid", productid));
		 cr.add(Restrictions.eq("status", "Posted")); 
		  cr.add(Restrictions.between("issueddate", fromdate, todate));
		 cr.add(Restrictions.isNull("adjustmenttype"));
		 cr.add(Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE));
		 cr.add(Restrictions.eq("companyid.id", companyid)); 
		 
		return cr.list();
	}

	@Override
	public Double vipamount(List<String> userid, Integer companyid) {
		Double amount = 0.0;
		if(! userid.isEmpty()) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.in("userid", userid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNull("adjustmenttype"),
		   		 Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
		}else {
			return amount;
		}
	}

	@Override
	public List<Wettopup> distinctmonthandyear(Integer companyid) {
		
		String SQL_Query = "select DISTINCT MM=Month(ISSUEDDT),Year(ISSUEDDT) from WET_TOPUP where Company_id = :companyid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> monthlysalestopup(String month, String year,Integer companyid) {
		String SQL_Query = "\r\n" + 
				"select SUM(AMOUNT) from WET_TOPUP Where Company_id = :companyid and Month(ISSUEDDT) = :month and Year(ISSUEDDT) = :year and Status = 'Posted' and adjustment_type is null and adjustment_category = 'NULL'";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("month", month);
		query.setParameter("year", year);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> monthlysalesbonus(String month, String year, Integer companyid) {
		String SQL_Query = "\r\n" + 
				"select SUM(BONUS) from WET_TOPUP Where Company_id = :companyid and Month(ISSUEDDT) = :month and Year(ISSUEDDT) = :year and Status = 'Posted' and adjustment_type is null and adjustment_category = 'NULL'";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("month", month);
		query.setParameter("year", year);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> monthlysalesadjustment(String month, String year, Integer companyid) {
		String SQL_Query = "\r\n" + 
				"select SUM(Amount) from WET_TOPUP Where Company_id = :companyid and Month(ISSUEDDT) = :month and Year(ISSUEDDT) = :year and Status = 'Posted' and adjustment_type is not null and adjustment_category != 'NULL'";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("month", month);
		query.setParameter("year", year);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> claimviadate(Date fromdate, Date todate, Integer companyid, String depositvia) {
		/*Date dt = fromdate;
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, -1);
		dt = c.getTime();*/
		String SQL_Query = "\r\n" + 
				"select Count(TRANC_ID) from WET_TOPUP where Deposit_via =:depositvia and Status = 'Posted' \r\n" + 
				"and COMPANY_ID =:companyid and adjustment_type is null and adjustment_category = 'NULL' and ISSUEDDT between :fromdate and :todate";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("depositvia", depositvia);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> totalclaimviadate(Date fromdate, Date todate, Integer companyid) {
		/*Date dt = fromdate;
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, -1);
		dt = c.getTime();*/
		String SQL_Query = "\r\n" + 
				"select Count(TRANC_ID) from WET_TOPUP where Deposit_via is not null and Status = 'Posted' \r\n" + 
				"and COMPANY_ID =:companyid and adjustment_type is null and adjustment_category = 'NULL' and ISSUEDDT between :fromdate and :todate";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public Double topupamount_newplayerreg(List<String> userid, Integer companyid, List<String> productid) {
		Double amount = 0.0;
		
		if(!userid.isEmpty()) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.in("userid", userid),
		            Restrictions.in("productid", productid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNull("adjustmenttype"),
		   		 Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
		}else {
			return amount;
		}
	}

	@Override
	public Long retopup_newplayerreg(List<String> userid, Integer companyid, List<String> productid) {
		Long amount = (long) 0;
		if(!userid.isEmpty()) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.in("userid", userid),
		            Restrictions.in("productid", productid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNull("adjustmenttype"),
		   		 Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.count("trancid"));
		return (Long) cr.uniqueResult();
	}else {
		return amount;
	}
	}

	@Override
	public Double bankcashflow(Date date, Integer companyid) {
	
		
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("issueddate", date),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNull("adjustmenttype"),
		   		 Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
		
	}

	@Override
	public List<Wettopup> distinct(String month, String year, Integer companyid) {
	
		String SQL_Query = "select distinct(issueddt) FROM wet_topup WHERE MONTH(issueddt) =:month AND YEAR(ISSUEDDT) =:year and company_id = :companyid \r\n" + 
				"order by issueddt";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List bankcashflow_totolamount(String month, String year, Integer companyid) {
		String SQL_Query = "select Sum(Amount) from WET_TOPUP where COMPANY_ID =:companyid and MONTH(issueddt) =:month AND YEAR(ISSUEDDT) =:year\r\n" + 
				"and status = 'Posted' and adjustment_type is null and adjustment_category = 'NULL'";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("companyid", companyid);
		
		List list = query.list();
		return list;
	}

	@Override
	public Double memberaccount_topup(String userid, Integer companyid) {
    Double amount = 0.0;
		
		if(userid != null) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.in("userid", userid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNull("adjustmenttype"),
		   		 Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
		}else {
			return amount;
		}
	}

	@Override
	public Double memberaccount_bonus(String userid, Integer companyid) {
	Double amount = 0.0;
		if(!userid.isEmpty()) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.in("userid", userid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNull("adjustmenttype"),
		   		 Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("bonus"));
		return (Double) cr.uniqueResult();
		}else {
			return amount;
		}
	}

	@Override
	public Double memberaccount_adjustment(String userid, Integer companyid) {
		Double amount = 0.0;
		if(!userid.isEmpty()) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.in("userid", userid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNotNull("adjustmenttype")
//		   		 Restrictions.like("adjustmentcategory", "NULL", MatchMode.)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
		}else {
			return amount;
		}
	}

	@Override
	public List<Wettopup> distinctadjcategory(Date fromdate, Date todate, Integer companyid) {
		String SQL_Query = "select distinct(adjustment_category) FROM wet_topup WHERE issueddt between :fromdate and :todate and company_id = :companyid and adjustment_type is not null and adjustment_type  != 'Master' and adjustment_category !='NULL' and status = 'Posted'";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public Long adjustment_totaltransaction(Date fromdate, Date todate, Integer companyid, String adjustment) {
		
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("adjustmentcategory", adjustment),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNotNull("adjustmenttype"),
		            Restrictions.ne("adjustmenttype", "Master")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.count("trancid"));
		return (Long) cr.uniqueResult();
	
	}

	@Override
	public Double adjustment_totalamount(Date fromdate, Date todate, Integer companyid, String adjustment) {
		
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("adjustmentcategory", adjustment),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("adjustmenttype"),
				            Restrictions.ne("adjustmenttype", "Master")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
		
	}

	@Override
	public Long adjustment__report_totaltransaction(Date fromdate, Date todate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNotNull("adjustmenttype"),
		            Restrictions.ne("adjustmenttype", "Master")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.count("trancid"));
		return (Long) cr.uniqueResult();
	}

	@Override
	public Double adjustment_report_totalamount(Date fromdate, Date todate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("adjustmenttype"),
				            Restrictions.ne("adjustmenttype", "Master")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wettopup> distinctuserid(Date fromdate, Date todate, Integer companyid) {
		String SQL_Query = "select distinct(user_id) FROM wet_topup WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted'";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public Double playercashflow_topup(Date fromdate, Date todate, Integer companyid, String userid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.eq("userid", userid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double playercashflow_bonus(Date fromdate, Date todate, Integer companyid, String userid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.eq("userid", userid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("bonus"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double playercashflow_adjustment(Date fromdate, Date todate, Integer companyid, String userid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.eq("userid", userid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("adjustmenttype")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double playercashflow__report_topup(Date fromdate, Date todate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double playercashflow__report_bonus(Date fromdate, Date todate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("bonus"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double playercashflow__report_adjustment(Date fromdate, Date todate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("adjustmenttype")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wettopup> freebonus(Date fromdate, Date todate, Integer companyid) {
		
		String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		
		String todate_format = simpleDateFormat.format(todate);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();
		
		String SQL_Query = "select distinct(user_id),product_id,issueddt,Amount FROM wet_topup WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted'and adjustment_type is not null  and adjustment_type  != 'Master' and adjustment_category !='NULL' order by issueddt";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public Double productdaily_bonus(Date fromdate, Integer companyid, String product) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE),
				            Restrictions.eq("productid", product)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("bonus"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_topup(Date fromdate, Integer companyid, String product) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE),
				            Restrictions.eq("productid", product)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_adjustment(Date fromdate, Integer companyid, String product) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("adjustmenttype"),
				            Restrictions.ne("adjustmenttype", "Master"),
				            Restrictions.eq("productid", product)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_master(Date fromdate, Integer companyid, String product) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.eq("adjustmenttype", "Master"),
				            Restrictions.eq("productid", product)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_bonus_total(Date fromdate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("bonus"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_topup_total(Date fromdate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_adjustment_total(Date fromdate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("adjustmenttype"),
				            Restrictions.ne("adjustmenttype", "Master")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_master_total(Date fromdate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.eq("adjustmenttype", "Master")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wettopup> topwlplayer(Date fromdate, Date todate, Integer companyid, List<String> productid) {
		
		/*String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		
		String todate_format = simpleDateFormat.format(todate);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();*/
		
		String SQL_Query = "select DISTINCT(USER_ID),PRODUCT_ID FROM wet_topup WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted'and adjustment_type is  null  and  adjustment_category ='NULL' and product_id in (:productid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("productid", productid);
		
		List<Wettopup> list = query.list();
		return list;
		
		
		
		
		/*String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		Date date1 = null;
		Date date2 = null;
		
		String todate_format = simpleDateFormat.format(todate);
		
		DateFormat formatter = new SimpleDateFormat(pattern);
		
		System.out.println((Date)(fromdate.getYear()+"-"+fromdate.getMonth()+"-"+fromdate.getDate()));
		System.out.println(todate.getYear()+"-"+todate.getMonth()+"-"+todate.getDate());
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();
		
		try {
			 date1=simpleDateFormat.parse(fromdate_format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		try {
			 date2=simpleDateFormat.parse(todate_format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		 Criteria  cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
			        Restrictions.and
			        
			        (
			        		
			            Restrictions.eq("companyid.id", companyid),
			            Restrictions.in("productid", productid),
			            Restrictions.between("issueddate", fromdate.getDate(), todate.getDate()),
			            Restrictions.eq("status", "Posted"),
			            Restrictions.isNull("adjustmenttype"),
			            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
			           
			        )
			    );
		 
	       return cr.list();*/
	}

	@Override
	public Double topwlplayer_topup(String userid, Integer companyid, String productid, Date fromdate, Date todate) {
		/*String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		Date date1 = null;
		Date date2 = null;
		
		String todate_format = simpleDateFormat.format(todate);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();
		
		try {
			 date1=simpleDateFormat.parse(fromdate_format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		try {
			 date2=simpleDateFormat.parse(todate_format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  */
		
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("userid", userid),
				            Restrictions.eq("productid", productid),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wettopup> latetopupwithdraw(Date fromdate, Date todate, Integer companyid) {
		String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		
		String todate_format = simpleDateFormat.format(todate);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();
		
		 Criteria  cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
			        Restrictions.and
			        
			        (
			        		
			            Restrictions.eq("companyid.id", companyid),
			            Restrictions.between("issueddate", fromdate, todate),
			            Restrictions.eq("status", "Posted"),
			            Restrictions.isNull("adjustmenttype"),
			            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
			           
			        )
			    );
		 
	       return cr.list();
	}

	@Override
	public List<Wettopup> cashflowproduct(Date fromdate, Date todate, Integer companyid, String productid) {
		
		String SQL_Query = "select DISTINCT(USER_ID),PRODUCT_ID FROM wet_topup WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted' and product_id =:productid and (adjustment_type = 'User' or adjustment_type is null)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("productid", productid);
		
		List<Wettopup> list = query.list();
		return list;
		
	}

	@Override
	public Double topwlplayer_adjustment(String userid, Integer companyid, String productid, Date fromdate,
			Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("userid", userid),
				            Restrictions.eq("productid", productid),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("adjustmenttype"),
				            Restrictions.ne("adjustmenttype", "Master")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double topwlplayer_bonus(String userid, Integer companyid, String productid, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("userid", userid),
				            Restrictions.eq("productid", productid),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("bonus"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowproduct_topup_total(Integer companyid, String productid, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("productid", productid),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowproduct_adjustment_total(Integer companyid, String productid, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("productid", productid),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("adjustmenttype"),
				            Restrictions.ne("adjustmenttype", "Master")
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowproduct_bonus_total(Integer companyid, String productid, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("productid", productid),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("bonus"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wettopup> cashflowbank(Date fromdate, Date todate, Integer companyid, String bank) {
		String SQL_Query = "select DISTINCT(USER_ID),BANK,PRODUCT_ID FROM wet_topup WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted'and adjustment_type is  null  and  adjustment_category ='NULL' and bank =:bank";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("bank", bank);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public Double cashflowbank_topup(String userid, Integer companyid, String bank, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("userid", userid),
				            Restrictions.eq("bank", bank),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowbank_topup_total(Integer companyid, String bank, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("bank", bank),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowbank_topup__report_total(Integer companyid, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wettopup> monthlyactiveplayer(Integer companyid, Date fromdate, String productid) {
		String SQL_Query = "select Count(Distinct(WP.ID)) as count from WEM_PLAYER as WP\r\n" + 
				"join TMP_PLAYER as TP on TP.WEMPLAYER_ID=WP.ID\r\n" + 
				"join WET_TOPUP as WT on WT.USER_ID=TP.USER_ID\r\n" + 
				"where WT.STATUS='Posted' and wt.adjustment_type is null and wt.adjustment_category = 'NULL' and wt.ISSUEDDT =:issueddt and wt.PRODUCT_ID =:productid\r\n" + 
				"and wt.company_id =:companyid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("issueddt", fromdate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> monthlyactiveplayer_total(Integer companyid, Date fromdate, String productid) {
		String SQL_Query = "select Count(Distinct(WP.ID)) as count from WEM_PLAYER as WP\r\n" + 
				"join TMP_PLAYER as TP on TP.WEMPLAYER_ID=WP.ID\r\n" + 
				"join WET_TOPUP as WT on WT.USER_ID=TP.USER_ID\r\n" + 
				"where WT.STATUS='Posted' and wt.adjustment_type is null and wt.adjustment_category = 'NULL' and Month(wt.ISSUEDDT) =:month and Year(wt.ISSUEDDT) =:year and wt.PRODUCT_ID =:productid\r\n" + 
				"and wt.company_id =:companyid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromdate); 
		
		String month = Integer.toString(cal.get(Calendar.MONTH)+1); 
		String year = Integer.toString(cal.get(Calendar.YEAR)); 
		
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		System.out.println(month);
		System.out.println(year);
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> monthlyactiveplayer_chart(Integer companyid, Date fromdate, List<String> productid) {
		String SQL_Query = "select Count(Distinct(WP.ID)) as count from WEM_PLAYER as WP\r\n" + 
				"join TMP_PLAYER as TP on TP.WEMPLAYER_ID=WP.ID\r\n" + 
				"join WET_TOPUP as WT on WT.USER_ID=TP.USER_ID\r\n" + 
				"where WT.STATUS='Posted' and wt.adjustment_type is null and wt.adjustment_category = 'NULL' and wt.ISSUEDDT =:issueddt and wt.PRODUCT_ID In (:productid)\r\n" + 
				"and wt.company_id =:companyid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("issueddt", fromdate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> monthlyactiveplayer_average(Integer companyid, Date fromdate, List<String> productid) {
		String SQL_Query = "select Count(Distinct(WP.ID)) as count from WEM_PLAYER as WP\r\n" + 
				"join TMP_PLAYER as TP on TP.WEMPLAYER_ID=WP.ID\r\n" + 
				"join WET_TOPUP as WT on WT.USER_ID=TP.USER_ID\r\n" + 
				"where WT.STATUS='Posted' and wt.adjustment_type is null and wt.adjustment_category = 'NULL' and Month(wt.ISSUEDDT) =:month and Year(wt.ISSUEDDT) =:year and wt.PRODUCT_ID In (:productid)\r\n" + 
				"and wt.company_id =:companyid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromdate); 
		
		String month = Integer.toString(cal.get(Calendar.MONTH)+1); 
		String year = Integer.toString(cal.get(Calendar.YEAR)); 
		
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		System.out.println(month);
		System.out.println(year);
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> monthlyactiveplayer_total(Integer companyid, Date fromdate) {
		String SQL_Query = "select Count(Distinct(WP.ID)) as count from WEM_PLAYER as WP\r\n" + 
				"join TMP_PLAYER as TP on TP.WEMPLAYER_ID=WP.ID\r\n" + 
				"join WET_TOPUP as WT on WT.USER_ID=TP.USER_ID\r\n" + 
				"where WT.STATUS='Posted' and wt.adjustment_type is null and wt.adjustment_category = 'NULL' and Month(wt.ISSUEDDT) =:month and Year(wt.ISSUEDDT) =:year\r\n" + 
				"and wt.company_id =:companyid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromdate); 
		
		String month = Integer.toString(cal.get(Calendar.MONTH)+1); 
		String year = Integer.toString(cal.get(Calendar.YEAR)); 
		
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("companyid", companyid);
		System.out.println(month);
		System.out.println(year);
		List<Wettopup> list = query.list();
		return list;
	};

	@Override
	public List<Wettopup> lasttopupandamount(Integer companyid, String product, String userid) {
		String SQL_Query = "select top(1)issueddt,amount from WET_TOPUP where company_id =:companyid\r\n" + 
				"and user_id =:userid\r\n" + 
				"and PRODUCT_ID =:productid\r\n" + 
				"and status = 'Posted'\r\n" + 
				"and adjustment_type is null\r\n" + 
				"and adjustment_category = 'NULL'\r\n" + 
				"order by issueddt desc";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		
		query.setParameter("userid", userid);
		query.setParameter("productid", product);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public Double getsumbonusbyuserid(String userid, String productid, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("userid", userid),
		            Restrictions.eq("productid", productid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNull("adjustmenttype"),
			   		 Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("bonus"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double getadjustmentbonusbyuserid(String userid, String productid, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("userid", userid),
		            Restrictions.eq("productid", productid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNotNull("adjustmenttype"),
		            Restrictions.ne("adjustmenttype", "Master")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double bonus_newplayerreg(List<String> userid, Integer companyid, List<String> productid) {
       Double amount = 0.0;
		
		if(!userid.isEmpty()) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.in("userid", userid),
		            Restrictions.in("productid", productid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNull("adjustmenttype"),
		   		 Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("bonus"));
		return (Double) cr.uniqueResult();
		}else {
			return amount;
		}
	}

	@Override
	public Double adjustment_newplayerreg(List<String> userid, Integer companyid, List<String> productid) {
Double amount = 0.0;
		
		if(!userid.isEmpty()) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.in("userid", userid),
		            Restrictions.in("productid", productid),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNotNull("adjustmenttype"),
		            Restrictions.ne("adjustmenttype", "Master")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
		}else {
			return amount;
		}
	}

	@Override
	public Double topvswithdraw_topup(Date fromdate, Integer companyid, List<String> product) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", fromdate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE),
				            Restrictions.in("productid", product)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wettopup> topvswithdraw_topuprange_hundred(String month, String year, Integer companyid, List<String> product) {
		String SQL_Query = "select count(*) from wet_topup\r\n" + 
				"where adjustment_type is null\r\n" + 
				"and company_id =:companyid\r\n" + 
				"and adjustment_category = 'NULL'\r\n" + 
				"and status = 'Posted'\r\n" + 
				"and Month(Issueddt) =:month\r\n" + 
				"and Year(Issueddt) =:year\r\n" + 
				"and amount <= 100 and Product_id in (:productid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("productid", product);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup>  topvswithdraw_topuprange_hundredone(String month, String year, Integer companyid,
			List<String> product) {
		String SQL_Query = "select count(*) from wet_topup\r\n" + 
				"where adjustment_type is null\r\n" + 
				"and company_id =:companyid\r\n" + 
				"and adjustment_category = 'NULL'\r\n" + 
				"and status = 'Posted'\r\n" + 
				"and Month(Issueddt) =:month\r\n" + 
				"and Year(Issueddt) =:year\r\n" + 
				"and amount > 100 and amount <=500 and Product_id in (:productid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("productid", product);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> topvswithdraw_topuprange_fivehundred(String month, String year, Integer companyid,
			List<String> product) {
		String SQL_Query = "select count(*) from wet_topup\r\n" + 
				"where adjustment_type is null\r\n" + 
				"and company_id =:companyid\r\n" + 
				"and adjustment_category = 'NULL'\r\n" + 
				"and status = 'Posted'\r\n" + 
				"and Month(Issueddt) =:month\r\n" + 
				"and Year(Issueddt) =:year\r\n" + 
				"and amount > 500 and Product_id in (:productid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("productid", product);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> topvswithdraw_topup_transac(Date date, Integer companyid, List<String> product) {
		String SQL_Query = "select count(*) from wet_topup\r\n" + 
				"where adjustment_type is null\r\n" + 
				"and company_id =:companyid\r\n" + 
				"and adjustment_category = 'NULL'\r\n" + 
				"and status = 'Posted'\r\n" + 
				"and Issueddt =:issueddt\r\n" + 
				"and Product_id in (:productid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		
		query.setParameter("issueddt", date);
		query.setParameter("productid", product);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> freebonus_userid(Date fromdate, Date todate, Integer companyid, String userid,
			String product) {
		String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		
		String todate_format = simpleDateFormat.format(todate);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();
		
		String SQL_Query = "select * FROM wet_topup WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted'and adjustment_type is not null  and adjustment_type  != 'Master' and adjustment_category !='NULL' and USER_ID =:userid and Product_id =:productid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("userid", userid);
		query.setParameter("productid", product);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> lasttopupandamount_customerlist(Integer companyid, String product, String userid,
			Date fromdate,Date todate) {
		String SQL_Query = "select top(1)issueddt,amount from WET_TOPUP where company_id =:companyid\r\n" + 
				"and user_id =:userid\r\n" + 
				"and PRODUCT_ID =:productid\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status = 'Posted'\r\n" + 
				"and adjustment_type is null\r\n" + 
				"and adjustment_category = 'NULL'\r\n" + 
				"order by issueddt desc";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("userid", userid);
		query.setParameter("productid", product);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> daily_mix_wettopup(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		
		/*String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		
		String todate_format = simpleDateFormat.format(todate);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();*/
		
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		           /* Restrictions.in("promotioncd", userid),*/
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.isNull("adjustmenttype"),
		            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE),
		            Restrictions.in("productid", productid)
		       
		           
		        )
		    );
	 
	 
	   
       return cr.list();
	}

	@Override
	public Double daily_mix_topup_amount(Integer companyid, Date fromdate, Date todate,
			List<String> productid) {
		
		/*String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		
		String todate_format = simpleDateFormat.format(todate);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();*/
		
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE),
				            Restrictions.in("productid", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double daily_mix_topup_bonus(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		
		/*String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		
		String todate_format = simpleDateFormat.format(todate);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();*/
		
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE),
				            Restrictions.in("productid", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("bonus"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Long daily_mix_topup_trancid(Integer companyid, Date fromdate, Date todate,
			List<String> productid) {
		
		/*String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		
		String todate_format = simpleDateFormat.format(todate);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();*/
		
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNull("adjustmenttype"),
				            Restrictions.like("adjustmentcategory", "NULL", MatchMode.ANYWHERE),
				            Restrictions.in("productid", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.count("trancid"));
		return (Long) cr.uniqueResult();
	}

	@Override
	public List<Wettopup> outside_report_unclaim(List<String> bank, Integer companyid, List<String> productid, Date fromdate,
			Date todate) {
		
		if(productid.size() == 1) {
			
			String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
					"and issueddt between :fromdate and :todate\r\n" +
					"and status = 'No'\r\n" + 
					"and adjustment_type is null\r\n" + 
					"and adjustment_category = 'NULL' and BANK In (:bank) and (Kiosk_Done_By is null or CS_Approveby is null)  AND Product_ID In (:productid)" ;
			Query query = entityManager.unwrap(Session.class).createSQLQuery(
					SQL_Query);
			
			
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("bank", bank);
			query.setParameter("productid", productid);
			query.setParameter("companyid", companyid);
			
			List<Wettopup> list = query.list();
			return list;
			
		}else {
			
			String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
					"and issueddt between :fromdate and :todate\r\n" +
					"and status = 'No'\r\n" + 
					"and adjustment_type is null\r\n" + 
					"and adjustment_category = 'NULL' and BANK In (:bank) and (Kiosk_Done_By is null or CS_Approveby is null OR Product_ID In (:productid))" ;
			Query query = entityManager.unwrap(Session.class).createSQLQuery(
					SQL_Query);
			
			
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("bank", bank);
			query.setParameter("productid", productid);
			query.setParameter("companyid", companyid);
			
			List<Wettopup> list = query.list();
			return list;
			
		}
		
		
	}

	@Override
	public List<Wettopup> outside_report_claim(List<String> bank, Integer companyid, List<String> productid,
			Date fromdate, Date todate,List<String> status) {
		String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
				"and PRODUCT_ID In (:productid)\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status In (:status)\r\n" + 
				"and adjustment_type is null\r\n" + 
				"and adjustment_category = 'NULL' and BANK In (:bank) and Claim = 'Yes' and Kiosk_Status = 'Done' and CS_CLAIM = 'Yes'" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("bank", bank);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("status", status);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> outside_report_madj_main_pending(Integer companyid, List<String> productid,
			Date fromdate, Date todate,List<String> adjustmenttype, List<String> adjustmentcategory) {
		String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status = 'T'\r\n" +
				"and adjustment_category != 'NULL' and  (Kiosk_Done_By is null or CS_Approveby is null ) and Product_id In (:productid) and Adjustment_type In (:adjustmenttype) and Adjustment_Category In (:adjustmentcategory)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("adjustmenttype", adjustmenttype);
		query.setParameter("adjustmentcategory", adjustmentcategory);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> outside_report_madj_main_done(Integer companyid, List<String> productid,
			Date fromdate, Date todate, List<String> status,List<String> adjustmenttype, List<String> adjustmentcategory) {
		String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status In (:status)\r\n" +
				"and adjustment_category != 'NULL' and  (Kiosk_Done_By is null or Kiosk_Done_By is not null or CS_Approveby is null or CS_Approveby is not null ) "
				+ "and Product_id In (:productid) and Adjustment_type In (:adjustmenttype) and Adjustment_Category In (:adjustmentcategory)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("adjustmenttype", adjustmenttype);
		query.setParameter("adjustmentcategory", adjustmentcategory);
		query.setParameter("status", status);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> outside_report_madj_birth_pending(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> adjustmenttype, List<String> adjustmentcategory) {
		String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status = 'T'\r\n" +
				"and adjustment_category != 'NULL' and  (Kiosk_Done_By is null or CS_Approveby is null ) and Product_id In (:productid) and Adjustment_type In (:adjustmenttype) and Adjustment_Category In (:adjustmentcategory)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("adjustmenttype", adjustmenttype);
		query.setParameter("adjustmentcategory", adjustmentcategory);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> outside_report_madj_birth_done(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> status, List<String> adjustmenttype, List<String> adjustmentcategory) {
		String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status In (:status)\r\n" +
				"and adjustment_category != 'NULL' and  (Kiosk_Done_By is null or Kiosk_Done_By is not null or CS_Approveby is null or CS_Approveby is not null ) "
				+ "and Product_id In (:productid) and Adjustment_type In (:adjustmenttype) and Adjustment_Category In (:adjustmentcategory)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("adjustmenttype", adjustmenttype);
		query.setParameter("adjustmentcategory", adjustmentcategory);
		query.setParameter("status", status);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> outside_report_madj_late_pending(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> adjustmenttype, List<String> adjustmentcategory) {
		String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status = 'T'\r\n" +
				"and adjustment_category != 'NULL' and  (Kiosk_Done_By is null or CS_Approveby is null ) and Product_id In (:productid) and Adjustment_type In (:adjustmenttype) and Adjustment_Category In (:adjustmentcategory)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("adjustmenttype", adjustmenttype);
		query.setParameter("adjustmentcategory", adjustmentcategory);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> outside_report_madj_late_done(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> status, List<String> adjustmenttype, List<String> adjustmentcategory) {
		String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status In (:status)\r\n" +
				"and adjustment_category != 'NULL' and  (Kiosk_Done_By is null or Kiosk_Done_By is not null or CS_Approveby is null or CS_Approveby is not null ) "
				+ "and Product_id In (:productid) and Adjustment_type In (:adjustmenttype) and Adjustment_Category In (:adjustmentcategory)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("adjustmenttype", adjustmenttype);
		query.setParameter("adjustmentcategory", adjustmentcategory);
		query.setParameter("status", status);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> outside_report_madj_day_pending(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> adjustmenttype, List<String> adjustmentcategory) {
		String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status = 'T'\r\n" +
				"and adjustment_category != 'NULL' and  (Kiosk_Done_By is null or CS_Approveby is null ) and Product_id In (:productid) and Adjustment_type In (:adjustmenttype) and Adjustment_Category In (:adjustmentcategory)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("adjustmenttype", adjustmenttype);
		query.setParameter("adjustmentcategory", adjustmentcategory);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> outside_report_day_main_done(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> status, List<String> adjustmenttype, List<String> adjustmentcategory) {
		String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status In (:status)\r\n" +
				"and adjustment_category != 'NULL' and  (Kiosk_Done_By is null or Kiosk_Done_By is not null or CS_Approveby is null or CS_Approveby is not null ) "
				+ "and Product_id In (:productid) and Adjustment_type In (:adjustmenttype) and Adjustment_Category In (:adjustmentcategory)" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("adjustmenttype", adjustmenttype);
		query.setParameter("adjustmentcategory", adjustmentcategory);
		query.setParameter("status", status);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public Double daily_mix_topup_madj_amount(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wettopup.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.isNotNull("adjustmenttype"),
				            Restrictions.ne("adjustmenttype", "Master"),
				            Restrictions.in("productid", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wettopup> homependingtopup(Integer companyid, List<String> productid, Date fromdate, Date todate) {
            if(productid.size() == 1) {
			
			String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
					"and issueddt between :fromdate and :todate\r\n" +
					"and status = 'No'\r\n" + 
					"and adjustment_type is null\r\n" + 
					"and adjustment_category = 'NULL' and (Kiosk_Done_By is null or CS_Approveby is null)  AND Product_ID In (:productid)" ;
			Query query = entityManager.unwrap(Session.class).createSQLQuery(
					SQL_Query);
			
			
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("productid", productid);
			query.setParameter("companyid", companyid);
			
			List<Wettopup> list = query.list();
			return list;
			
		}else {
			
			String SQL_Query = "select sum(Amount) from WET_TOPUP where company_id =:companyid\r\n" + 
					"and issueddt between :fromdate and :todate\r\n" +
					"and status = 'No'\r\n" + 
					"and adjustment_type is null\r\n" + 
					"and adjustment_category = 'NULL' and (Kiosk_Done_By is null or CS_Approveby is null OR Product_ID In (:productid))" ;
			Query query = entityManager.unwrap(Session.class).createSQLQuery(
					SQL_Query);
			
			
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("productid", productid);
			query.setParameter("companyid", companyid);
			
			List<Wettopup> list = query.list();
			return list;
			
		}
		
	}

	@Override
	public List<Wettopup> freebonus_home(Date fromdate, Date todate, Integer companyid) {
		String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		
		String todate_format = simpleDateFormat.format(todate);
		
		/*Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();*/
		
		String SQL_Query = "select distinct(user_id),product_id,issueddt,Amount FROM wet_topup WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted'and adjustment_type is not null  and adjustment_type  != 'Master' and adjustment_category !='NULL' order by issueddt";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		
		List<Wettopup> list = query.list();
		return list;
	}

	@Override
	public List<Wettopup> outside_report_claim_bonus(List<String> bank, Integer companyid, List<String> productid,
			Date fromdate, Date todate, List<String> status) {
		String SQL_Query = "select sum(bonus) from WET_TOPUP where company_id =:companyid\r\n" + 
				"and PRODUCT_ID In (:productid)\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and status In (:status)\r\n" + 
				"and adjustment_type is null\r\n" + 
				"and adjustment_category = 'NULL' and BANK In (:bank) and Claim = 'Yes' and Kiosk_Status = 'Done' and CS_CLAIM = 'Yes'" ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("bank", bank);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("status", status);
		
		List<Wettopup> list = query.list();
		return list;
	}
	

}
