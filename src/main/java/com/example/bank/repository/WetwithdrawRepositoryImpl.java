package com.example.bank.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;

public class WetwithdrawRepositoryImpl implements WetwithdrawCutomRepository{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Double getamountbyuseridinwithdraw(Date fromdate, Date todate, String userid, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
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
	public List<Wetwithdraw> monthlysalestopup(String month, String year, Integer companyid) {
		String SQL_Query = "\r\n" + 
				"select SUM(AMOUNT) from WET_WITHDRAW Where Company_id = :companyid and Month(ISSUEDDT) = :month and Year(ISSUEDDT) = :year and Status = 'Posted'";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("month", month);
		query.setParameter("year", year);
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public Double withdrawamount_newplayerreg(List<String> userid, Integer companyid, List<String> productid) {
Double amount = 0.0;
if(!userid.isEmpty()) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.in("userid", userid),
		            Restrictions.in("productid", productid),
		            Restrictions.eq("status", "Posted")
		           
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
	public Double bankcashflow(Date date, Integer companyid) {
		
				Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
				        Restrictions.and
				        
				        (
				        		
				            Restrictions.eq("companyid.id", companyid),
				            Restrictions.eq("issueddate", date),
				            Restrictions.eq("status", "Posted")
				           
				        )
				    );
				
				ProjectionList projectionList = Projections.projectionList();
			    
			    cr.setProjection(Projections.sum("amount"));
				return (Double) cr.uniqueResult();
		
	}

	@Override
	public List bankcashflow_totolamount(String month, String year, Integer companyid) {
		String SQL_Query = "select Sum(Amount) from WET_WITHDRAW where COMPANY_ID =:companyid and MONTH(issueddt) =:month AND YEAR(ISSUEDDT) =:year\r\n" + 
				"and status = 'Posted'";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("companyid", companyid);
		
		List list = query.list();
		return list;
	}

	@Override
	public Double memberaccount_withdraw(String userid, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("userid", userid),
		            Restrictions.eq("status", "Posted")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double playercashflow_withdraw(Date fromdate, Date todate, Integer companyid, String userid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("userid", userid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double playercashflow_report_withdraw(Date fromdate, Date todate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
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
	public Double productdaily_withdraw(Date fromdate, Integer companyid, String product) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("issueddate", fromdate),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.eq("productid", product)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double productdaily_withdraw_total(Date fromdate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
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
	public Double topwlplayer_withdraw(String userid, Integer companyid, String productid, Date fromdate, Date todate) {
		
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
		
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("productid", productid),
		            Restrictions.eq("userid", userid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	
	@Override
	public List<Wetwithdraw> latewithdraw(Date fromdate, Date todate, Integer companyid) {
		String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fromdate_format = simpleDateFormat.format(fromdate);
		
		String todate_format = simpleDateFormat.format(todate);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromdate); 
		c.add(Calendar.DATE, -1);
		fromdate = c.getTime();
		 Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class);
			
		 cr.add(Restrictions.eq("status", "Posted")); 
		  cr.add(Restrictions.between("issueddate", fromdate, todate));
		 cr.add(Restrictions.eq("companyid.id", companyid)); 
		 
		return cr.list();
	}

	@Override
	public Double cashflowproduct_withdraw_total(Integer companyid, String productid, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("productid", productid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowbank_withdraw(String userid, Integer companyid, String bank, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("companybank", bank),
		            Restrictions.eq("userid", userid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowbank_withdraw_total(Integer companyid, String bank, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("companybank", bank),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double cashflowbank_withdraw_report_total(Integer companyid, Date fromdate, Date todate) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
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
	public List<Wetwithdraw> cashflowproduct_withdraw(Date fromdate, Date todate, Integer companyid, String productid) {
		String SQL_Query = "select DISTINCT(USER_ID),COMPANY_BANK,PRODUCT_ID FROM wet_withdraw WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted' and product_id =:productid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("productid", productid);
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public List<Wetwithdraw> cashflowbank_withdraw(Date fromdate, Date todate, Integer companyid, String bank) {
		String SQL_Query = "select DISTINCT(USER_ID),COMPANY_BANK,PRODUCT_ID FROM wet_withdraw WHERE issueddt between :fromdate and :todate and company_id = :companyid and status = 'Posted' and COMPANY_BANK =:bank";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("bank", bank);
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public Double getsumamountbyuserid_withdraw(String userid, String productid, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("productid", productid),
		            Restrictions.eq("userid", userid),
		            Restrictions.eq("status", "Posted")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Wetwithdraw> lasttopupandamount(Integer companyid, String product, String userid) {
		String SQL_Query = "select top(1)issueddt,amount from WET_WITHDRAW where company_id =:companyid\r\n" + 
				"and user_id =:userid\r\n" + 
				"and PRODUCT_ID =:productid\r\n" + 
				"and status = 'Posted'\r\n" +
				"order by issueddt desc";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		
		query.setParameter("userid", userid);
		query.setParameter("productid", product);
		query.setParameter("companyid", companyid);
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public Double topvswithdraw_withdraw(Date fromdate, Integer companyid, List<String> product) {
	
				Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
				        Restrictions.and
				        
				        (
				        		
				            Restrictions.eq("companyid.id", companyid),
				            Restrictions.in("productid", product),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.eq("issueddate", fromdate)
				           
				        )
				    );
				
				ProjectionList projectionList = Projections.projectionList();
			    
			    cr.setProjection(Projections.sum("amount"));
				return (Double) cr.uniqueResult();
		}

	@Override
	public List<Wetwithdraw> topvswithdraw_topuprange_hundred(String month, String year, Integer companyid,
			List<String> product) {
		String SQL_Query = "select count(*) from wet_withdraw\r\n" + 
				"where company_id =:companyid\r\n" + 
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
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public List<Wetwithdraw> topvswithdraw_topuprange_hundredone(String month, String year, Integer companyid,
			List<String> product) {
		String SQL_Query = "select count(*) from wet_withdraw\r\n" + 
				"where company_id =:companyid\r\n" + 
				"and status = 'Posted'\r\n" + 
				"and Month(Issueddt) =:month\r\n" + 
				"and Year(Issueddt) =:year\r\n" + 
				"and amount > 100 and amount <= 500 and Product_id in (:productid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("productid", product);
		query.setParameter("companyid", companyid);
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public List<Wetwithdraw> topvswithdraw_topuprange_fivehundred(String month, String year, Integer companyid,
			List<String> product) {
		String SQL_Query = "select count(*) from wet_withdraw\r\n" + 
				"where company_id =:companyid\r\n" + 
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
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public List<Wetwithdraw> topvswithdraw_withdraw_transac(Date date, Integer companyid, List<String> product) {
		String SQL_Query = "select count(*) from wet_withdraw\r\n" + 
				"where company_id =:companyid\r\n" + 
				"and status = 'Posted'\r\n" + 
				"and Issueddt =:issueddt\r\n" + 
				"and Product_id in (:productid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		
		query.setParameter("issueddt", date);
		query.setParameter("productid", product);
		query.setParameter("companyid", companyid);
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public List<Wetwithdraw> daily_mix_wetwithdraw(Integer companyid, Date fromdate, Date todate,
			List<String> productid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		           /* Restrictions.in("promotioncd", userid),*/
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("status", "Posted"),
		            Restrictions.in("productid", productid)
		       
		           
		        )
		    );
	 
	 
	   
       return cr.list();
	}

	@Override
	public Double daily_mix_withdraw_amount(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.in("productid", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double daily_mix_withdraw_bonus(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long daily_mix_withdraw_trancid(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.in("productid", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.count("trancid"));
		return (Long) cr.uniqueResult();
	}

	@Override
	public List<Wetwithdraw> outside_report_unclaim(Integer companyid, List<String> productid,
			Date fromdate, Date todate) {
		String SQL_Query = "select sum(Amount) from WET_WITHDRAW where company_id =:companyid\r\n" + 
				"and PRODUCT_ID In 	(:productid)\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and (Bank_APPROVE is null or CS_APPROVE is null or Cashout_STATUS is null) " ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public List<Wetwithdraw> outside_report_claim( Integer companyid, List<String> productid,
			Date fromdate, Date todate, List<String> status) {
		String SQL_Query = "select sum(Amount) from WET_WITHDRAW where company_id =:companyid\r\n" + 
				"and PRODUCT_ID In 	(:productid)\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and (Bank_APPROVE is not null and CS_APPROVE is not null and Cashout_STATUS is not null) and status In (:status) " ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("status", status);
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public List<Wetwithdraw> outside_report_unclaim_gst(Integer companyid, List<String> productid, Date fromdate,
			Date todate) {
		String SQL_Query = "select sum(TAXAMT) from WET_WITHDRAW where company_id =:companyid\r\n" + 
				"and PRODUCT_ID In 	(:productid)\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and (Bank_APPROVE is null or CS_APPROVE is null or Cashout_STATUS is null) " ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public List<Wetwithdraw> outside_report_claim_gst(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> status) {
		String SQL_Query = "select sum(TAXAMT) from WET_WITHDRAW where company_id =:companyid\r\n" + 
				"and PRODUCT_ID In 	(:productid)\r\n" + 
				"and issueddt between :fromdate and :todate\r\n" +
				"and (Bank_APPROVE is not null and CS_APPROVE is not null and Cashout_STATUS is not null) and status In (:status) " ;
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		query.setParameter("companyid", companyid);
		query.setParameter("status", status);
		
		List<Wetwithdraw> list = query.list();
		return list;
	}

	@Override
	public Double daily_mix_withdraw_taxamount(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Wetwithdraw.class).add(
		        Restrictions.and
		        (
		        		  Restrictions.eq("companyid.id", companyid),
		        		  Restrictions.between("issueddate", fromdate, todate),
				            Restrictions.eq("status", "Posted"),
				            Restrictions.in("productid", productid)
				           
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("taxamt"));
		return (Double) cr.uniqueResult();
	}

}
