package com.example.bank.repository;

import java.util.ArrayList;
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

import com.example.bank.model.OthIncome;
import com.example.bank.model.Wettopup;

public class OthIncomeRepositoryImpl implements OthIncomeCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Double bankcashflow_othincome(Date date, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(OthIncome.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("issueddate", date),
		            Restrictions.isNotNull("bankname")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List bankcashflow_totol_othincome(String month, String year, Integer companyid) {
		String SQL_Query = "select Sum(Amount) from OTH_INCOME where COMPANY_ID =:companyid and MONTH(issueddt) =:month AND YEAR(ISSUEDDT) =:year and"
				+ "          BANK_NAME is not null";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("companyid", companyid);
		
		List list = query.list();
		return list;
	}

	@Override
	public List<OthIncome> outside_report_othincome(List<String> bank, Integer companyid, List<String> incometype,
			List<String> incomedet, Date fromdate, Date todate) {
		List list = new ArrayList<>();
		
		if(incometype.size() == 1 && incomedet.size() == 1) {
			
			String SQL_Query = "select Sum(Amount) from OTH_INCOME where COMPANY_ID =:companyid and issueddt between :fromdate and :todate and"
					+ " BANK_NAME In (:bank) and INCMDTL_NAME IN (:incomedet) and INCOME_NAME In (:incometype)";
			Query query = entityManager.unwrap(Session.class).createSQLQuery(
					SQL_Query);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("companyid", companyid);
			query.setParameter("bank", bank);
			query.setParameter("incometype", incometype);
			query.setParameter("incomedet", incomedet);
			
			 list = query.list();
			
		}else if(incometype.size() == 1 && incomedet.size() != 1) {
			
			String SQL_Query = "select Sum(Amount) from OTH_INCOME where COMPANY_ID =:companyid and issueddt between :fromdate and :todate and"
					+ "          BANK_NAME In (:bank) and INCOME_NAME In (:incometype) AND (INCMDTL_NAME IN (:incomedet) OR INCMDTL_NAME is null)";
			Query query = entityManager.unwrap(Session.class).createSQLQuery(
					SQL_Query);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("companyid", companyid);
			query.setParameter("bank", bank);
			query.setParameter("incometype", incometype);
			query.setParameter("incomedet", incomedet);
			
			 list = query.list();
			 
		}else if(incometype.size() != 1 && incomedet.size() != 1) {
			String SQL_Query = "select Sum(Amount) from OTH_INCOME where COMPANY_ID =:companyid and issueddt between :fromdate and :todate and"
					+ "          BANK_NAME In (:bank) AND (INCOME_NAME In (:incometype) OR INCMDTL_NAME IN (:incomedet) OR INCOME_NAME IS NULL OR INCMDTL_NAME IS NULL)";
			Query query = entityManager.unwrap(Session.class).createSQLQuery(
					SQL_Query);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("companyid", companyid);
			query.setParameter("bank", bank);
			query.setParameter("incometype", incometype);
			query.setParameter("incomedet", incomedet);
			
			 list = query.list();
		
			
		}else if(incometype.size() != 1 && incomedet.size() == 1) {
			String SQL_Query = "select Sum(Amount) from OTH_INCOME where COMPANY_ID =:companyid and issueddt between :fromdate and :todate and"
					+ "          BANK_NAME In (:bank) AND (INCOME_NAME In (:incometype) OR  INCOME_NAME IS NULL ) AND INCMDTL_NAME IN (:incomedet)";
			Query query = entityManager.unwrap(Session.class).createSQLQuery(
					SQL_Query);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("companyid", companyid);
			query.setParameter("bank", bank);
			query.setParameter("incometype", incometype);
			query.setParameter("incomedet", incomedet);
			
			 list = query.list();
		
			
		}
		
		return list;
	
	}

	@Override
	public List<OthIncome> home_othincome(Integer companyid, Date fromdate, Date todate) {
		String SQL_Query = "select Sum(Amount) from OTH_INCOME where COMPANY_ID =:companyid and issueddt between :fromdate and :todate";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		
		List<OthIncome> list = query.list();
		
		return list;
	}

}
