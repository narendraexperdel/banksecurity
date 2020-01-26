package com.example.bank.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.example.bank.model.TmpPlayer;
import com.example.bank.model.WemPlayer;
import com.example.bank.model.Wettopup;

public class TmpPlayerRepositoryImpl implements TmpPlayerCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<TmpPlayer> getalluserid(String productid, String userid, Integer companyid) {
		
		 char as ='0';
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(TmpPlayer.class);
		cre.add(Restrictions.eq("productid", productid));
		cre.add(Restrictions.ne("userid", userid));
		cre.add(Restrictions.eq("companyid.id", companyid));
		cre.add(Restrictions.eq("allocationstatus", as));
	
		return (List<TmpPlayer>) cre.list();
	}

	@Override
	public List<TmpPlayer> getuseridbycomapnyid(Integer companyid) {
		 char as ='1';
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(TmpPlayer.class);
			cre.add(Restrictions.eq("companyid.id", companyid));
			cre.add(Restrictions.eq("allocationstatus", as));
		
			return (List<TmpPlayer>) cre.list();
	}

	@Override
	public List<TmpPlayer> getuseridbyplayer(Integer companyid, Integer wemplayerid,List<String> productid) {
		
		Criteria cre = null;
		 char as ='1';
		List<TmpPlayer> tmpplayer = new ArrayList<TmpPlayer>();
		if(!productid.isEmpty()) {
		  cre =  entityManager.unwrap(Session.class).createCriteria(TmpPlayer.class).add(
			        Restrictions.and
			        
			        (
			        		
			        		Restrictions.eq("companyid.id", companyid),
			    			Restrictions.eq("wemplayerid.id", wemplayerid),
			    			Restrictions.eq("allocationstatus", as),
			            Restrictions.in("productid", productid)
			       
			           /* Restrictions.eq("adjustmenttype", NULL),
			            Restrictions.eq("adjustmentcategory", null)*/
			           
			        )
			    );
		 
	
	       return cre.list();
		}else {
			return tmpplayer;
		}
		
		
	}

	@Override
	public List<TmpPlayer> getuseridbyplayer(Integer companyid, Integer wemplayerid) {
		 char as ='1';
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(TmpPlayer.class);
		 cre.add(Restrictions.eq("wemplayerid.id", wemplayerid));
		cre.add(Restrictions.eq("companyid.id", companyid));
		cre.add(Restrictions.eq("allocationstatus", as));
	
		return (List<TmpPlayer>) cre.list();
	}

	@Override
	public List<TmpPlayer> getuserid(Integer companyid, Integer wemplayerid) {
		Criteria cre = entityManager.unwrap(Session.class).createCriteria(TmpPlayer.class);
		cre.add(Restrictions.eq("companyid.id", companyid));
		cre.add(Restrictions.eq("wemplayerid.id", wemplayerid));
		
		cre.setProjection(Projections.projectionList()
			      .add(Projections.property("userid"), "userid"))
			    .setResultTransformer(Transformers.aliasToBean(TmpPlayer.class));
	
		return (List<TmpPlayer>) cre.list();
	}

	@Override
	public List<TmpPlayer> getuseridfornewplayerreg(Integer companyid,List<String> productid, List<Integer> wemplayerid) {
		
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
		if(!wemplayerid.isEmpty()) {
		String SQL_Query = "select *  from TMP_player where Company_Id =:companyid\r\n" + 
				"and PRODUCT_ID In (:productid) and wemplayer_id in (:wemplayerid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("productid", productid);
		query.setParameter("wemplayerid", wemplayerid);
		
		List<TmpPlayer> list = query.list();
		return list;
		}else {
			return tmpplayer;
		}
	}

	@Override
	public List<TmpPlayer> gettopupplayer(Integer companyid, List<String> productid, Integer wemplayerid) {
		String SQL_Query = "select *  from TMP_player where Company_Id =:companyid\r\n" + 
				"and PRODUCT_ID In (:productid) and wemplayer_id in (:wemplayerid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("productid", productid);
		query.setParameter("wemplayerid", wemplayerid);
		
		List<TmpPlayer> list = query.list();
		return list;
	}

	@Override
	public List<TmpPlayer> tmpplayer_list(Integer companyid, Date fromdate, Date todate) {
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
		
		String SQL_Query = "select * from Tmp_Player where Cast(ISSUEDDT as date) between :fromdate and :todate\r\n" + 
				"and Company_id = :companyid and wemplayer_id is not null and allocation_status = 1";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", ft);
		query.setParameter("todate", td);
		
		 @SuppressWarnings("unchecked")
		    List<TmpPlayer> list = (List<TmpPlayer>) query.list();
		return list;
	}

	@Override
	public List<TmpPlayer> tmpplayer_list(Integer companyid, Date fromdate, Date todate, String userid) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        char as = '1';
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
		
		String SQL_Query = "select * from Tmp_Player where Cast(ISSUEDDT as date) between :fromdate and :todate\r\n" + 
				"and Company_id = :companyid and USER_ID =:userid and wemplayer_id is not null and allocation_status = 1";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("fromdate", ft);
		query.setParameter("todate", td);
		query.setParameter("userid", userid);
		
		 @SuppressWarnings("unchecked")
		    List<TmpPlayer> list = (List<TmpPlayer>) query.list();
		return list;
	}

	@Override
	public List<String> playername(String userid, String productid, Integer companyid) {
		String SQL_Query = " select FLNAME,TELNO,CONMETHOD,SOURCES,WCWS From WEM_PLAYER as wm \r\n" + 
				"   join Tmp_player as tm on wm.id = tm.wemplayer_id\r\n" + 
				"   where tm.product_id =:productid and tm.user_id =:userid and		\r\n" + 
				"   tm.company_id =:companyid and wm.company_id =:companyid and tm.wemplayer_id is not null and\r\n" + 
				"   tm.allocation_status = 1";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("productid", productid);
		query.setParameter("userid", userid);
		
		List<String> list = query.list();
		return list;
	}

	@Override
	public List<String> productname(String userid, Integer companyid) {
			String SQL_Query = "select PRODUCT_ID from Tmp_Player where \r\n" + 
				"Company_id = :companyid and USER_ID =:userid and wemplayer_id is not null and allocation_status = 1";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("userid", userid);
		
		 @SuppressWarnings("unchecked")
		    List<String> list =  query.list();
		return list;
	}

	@Override
	public List<Integer> playercount_closesum(List<String> userid, Integer companyid) {
		
		List<Integer> list = new ArrayList<>();
		if(! userid.isEmpty()) {
		String SQL_Query = "select count(ID) from WEM_PLAYER where ID in( \r\n" + 
				"select distinct(WEMPLAYER_ID) from TMP_PLAYER where USER_ID in (:userid) and COMPANY_ID =:companyid";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("companyid", companyid);
		query.setParameter("userid", userid);
		
		 
		     list =  query.list();
		return list;
		}else {
			return list;
		}
	}

	@Override
	public List<TmpPlayer> checkuserid(String productid, String userid, Integer companyid) {
		 char as ='1';
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(TmpPlayer.class);
		cre.add(Restrictions.eq("productid", productid));
		cre.add(Restrictions.eq("userid", userid));
		cre.add(Restrictions.eq("companyid.id", companyid));
		cre.add(Restrictions.eq("allocationstatus", as));
		cre.add(Restrictions.isNotNull("wemplayerid"));
	
		return (List<TmpPlayer>) cre.list();
	}

}
