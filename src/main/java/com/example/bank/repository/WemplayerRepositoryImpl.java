package com.example.bank.repository;

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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bank.model.CmcProduct;
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.WemPlayer;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;

public class WemplayerRepositoryImpl implements  WemplayerCustomRepository{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public List<WemPlayer> getallplayer(Integer companyid, Date fromdate, Date todate, List<String> sources) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String ft = format.format(fromdate);
		String td = format.format(todate);
		try {
			Date   date       = format.parse (ft);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			Date   date       = format.parse (td);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String SQL_Query = "select * from WEM_PLAYER where Cast(ISSUEDDT as date) between :fromdate and :todate\r\n" + 
				"and Company_id = :companyid and Sources in(:sources)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", ft);
		query.setParameter("todate", td);
		query.setParameter("sources" , sources);
		
		 @SuppressWarnings("unchecked")
		    List<WemPlayer> list = (List<WemPlayer>) query.list();
		return list;
	/*	Date dt = todate;
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		Criteria cr = null;
		List<WemPlayer> wemplayer = new ArrayList<WemPlayer>();
		if(!sources.isEmpty()) {
		  cr =  entityManager.unwrap(Session.class).createCriteria(WemPlayer.class).add(
			        Restrictions.and
			        
			        (
			        		
			            Restrictions.eq("companyid.id", companyid),
			            Restrictions.in("sources", sources),
//			            Restrictions.between("date(issueddt)", fromdate, dt),
			            Restrictions.sqlRestriction("cast(issueddt as date) between ? and ? ",fromdate,dt)
			            Restrictions.eq("adjustmenttype", NULL),
			            Restrictions.eq("adjustmentcategory", null)
			           
			        )
			    );
		 
	       return cr.list();
		}else {
			return wemplayer;
		}*/
	}

	@Override
	public List<WemPlayer> getallplayebydate(Integer companyid, Date fromdate, Date todate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String ft = format.format(fromdate);
		String td = format.format(todate);
		try {
			Date   date       = format.parse (ft);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			Date   date       = format.parse (td);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String SQL_Query = "select * from WEM_PLAYER where Cast(ISSUEDDT as date) between :fromdate and :todate\r\n" + 
				"and Company_id = :companyid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", ft);
		query.setParameter("todate", td);
		
		 @SuppressWarnings("unchecked")
		    List<WemPlayer> list = (List<WemPlayer>) query.list();
		return list;
	
	}

	@Override
	public List<WemPlayer> getallplayerbycompanyid(Integer companyid) {
		Criteria cre = entityManager.unwrap(Session.class).createCriteria(WemPlayer.class);
		cre.add(Restrictions.eq("companyid.id", companyid));
		
		cre.setProjection(Projections.projectionList()
			      .add(Projections.property("id"), "id")
		.add(Projections.property("flname"), "flname")
.add(Projections.property("issueddt"), "issueddt"))
			    .setResultTransformer(Transformers.aliasToBean(WemPlayer.class));
	
		return (List<WemPlayer>) cre.list();
	}

	@Override
	public List<WemPlayer> getallplayerinfo(Integer companyid, Date fromdate, Date todate, List<String> sources) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String ft = format.format(fromdate);
		String td = format.format(todate);
		try {
			Date   date       = format.parse (ft);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			Date   date       = format.parse (td);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String SQL_Query = "select * from WEM_PLAYER where Cast(ISSUEDDT as date) between :fromdate and :todate\r\n" + 
				"and Company_id = :companyid and Sources in(:sources)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", ft);
		query.setParameter("todate", td);
		query.setParameter("sources" , sources);
		
		 @SuppressWarnings("unchecked")
		    List<WemPlayer> list = (List<WemPlayer>) query.list();
		return list;

	}

	@Override
	public List<WemPlayer> getallplayerinfo(Integer companyid, Date fromdate, Date todate, List<String> sources,
			String playername) {
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String ft = format.format(fromdate);
		String td = format.format(todate);
		try {
			Date   date       = format.parse (ft);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			Date   date       = format.parse (td);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String SQL_Query = "select * from WEM_PLAYER where Cast(ISSUEDDT as date) between :fromdate and :todate\r\n" + 
				"and Company_id = :companyid and Sources in(:sources) and FLNAME =:playername";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", ft);
		query.setParameter("todate", td);
		query.setParameter("sources" , sources);
		query.setParameter("playername" , playername);
		
		 @SuppressWarnings("unchecked")
		    List<WemPlayer> list = (List<WemPlayer>) query.list();
		return list;
	}

	@Override
	public List<WemPlayer> getallplayer_selectioncs(Integer companyid, Date fromdate, Date todate) {
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String ft = format.format(fromdate);
		String td = format.format(todate);
		try {
			Date   date       = format.parse (ft);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			Date   date       = format.parse (td);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String SQL_Query = "select * from WEM_PLAYER where Cast(ISSUEDDT as date) between :fromdate and :todate\r\n" + 
				"and Company_id = :companyid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", ft);
		query.setParameter("todate", td);
		
		 @SuppressWarnings("unchecked")
		    List<WemPlayer> list = (List<WemPlayer>) query.list();
		return list;
	}

	@Override
	public List<WemPlayer> homeactiveplayer(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		String SQL_Query = "select Count(Distinct(WP.ID)) as count from WEM_PLAYER as WP\r\n" + 
				"join TMP_PLAYER as TP on TP.WEMPLAYER_ID=WP.ID\r\n" + 
				"join WET_TOPUP as WT on WT.USER_ID=TP.USER_ID\r\n" + 
				"where WT.STATUS='Posted' and wt.adjustment_type is null and wt.adjustment_category = 'NULL' and cast(WT.CS_DONE_DATETIME as date) between\r\n" + 
				":fromdate and :todate\r\n" + 
				"and wt.company_id =:companyid and wt.product_id in (:productid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		
		 @SuppressWarnings("unchecked")
		    List<WemPlayer> list = (List<WemPlayer>) query.list();
		return list;
	}

	@Override
	public List<WemPlayer> homenonactiveplayer(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		String SQL_Query = "\r\n" + 
				"select count(wp.Id) from WEM_PLAYER as WP join \r\n" + 
				"TMP_PLAYER as TP  on Wp.ID=tp.WEMPLAYER_ID \r\n" + 
				"join WET_TOPUP as wt on  wt.company_id = tp.company_id\r\n" + 
				"WHERE\r\n" + 
				" wp.company_id =:companyid \r\n" + 
				"and wt.issueddt between :fromdate and :todate\r\n" + 
				"and wt.adjustment_type = 'User' and wt.adjustment_category Like '%Registered%'\r\n" + 
				"and amount =3  and wt.Product_id in (:productid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("productid", productid);
		
		 @SuppressWarnings("unchecked")
		    List<WemPlayer> list = (List<WemPlayer>) query.list();
		return list;
	}

	@Override
	public List<WemPlayer> birthdayplayer(Integer companyid, Date fromdate, Date todate) {
		String SQL_Query = "select wm.id,wm.FLNAME,wm.TELNO FROM wem_player as wm\r\n" + 
				"WHERE MONTH(DOB) =month(getdate()) and COMPANY_ID =:companyid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		
		query.setParameter("companyid", companyid);
		
		List<WemPlayer> list = query.list();
		return list;
	}
	@Override
	public List<WemPlayer> getallprofiledatarbycompanyid(Integer companyid) {
		String SQL_Query = "select ID,FLNAME,TELNO,BANKACC from WEM_PLAYER where COMPANY_ID  =:companyid and id > 25394 and id < 27083";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
	
		 @SuppressWarnings("unchecked")
		    List<WemPlayer> list = (List<WemPlayer>) query.list();
		return list;
	}

	

}
