package com.example.bank.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.CmcProduct;
import com.example.bank.model.Expensestype;
import com.example.bank.model.OthIncome;
import com.example.bank.model.ReferalBonus;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;

public class ExpensestypeRepositoryImpl implements ExpensestypeCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Double bankcashflow_expenses(Date date, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Expensestype.class).add(
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
	public List bankcashflow_totol_expenses(String month, String year, Integer companyid) {
		String SQL_Query = "select Sum(Amount) from EXPENSES_TYPE where COMPANY_ID =:companyid and MONTH(issueddt) =:month AND YEAR(ISSUEDDT) =:year and"
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
	public List<Expensestype> distinctexpenses(Date fromdate, Date todate, List<String> expenses,
			List<String> expensesdet, List<String> type,Integer companyid) {
		
		Criteria cr = null;
		List<Expensestype> expensetype = new ArrayList<Expensestype>();
		
		if(type.size() == 2) {
		String SQL_Query = "select DISTINCT(EXPENSES_NAME),EXPENSESDT_NAME FROM Expenses_type WHERE issueddt between :fromdate and :todate and company_id = :companyid and EXPENSES_NAME IN (:expensename) and EXPENSESDT_NAME In (:expensedtname)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("expensename", expenses);
		query.setParameter("expensedtname", expensesdet);
		query.setParameter("companyid", companyid);
		
		expensetype = query.list();
		
		}else if(type.get(0).equals("Bank")) {
			
			String SQL_Query = "select DISTINCT(EXPENSES_NAME),EXPENSESDT_NAME FROM Expenses_type WHERE issueddt between :fromdate and :todate and company_id = :companyid and EXPENSES_NAME IN (:expensename) and EXPENSESDT_NAME In (:expensedtname) and EXPENSES_NAME !='Petty Cash' and EXPENSESDT_NAME !='Petty Cash' ";
			Query query = entityManager.unwrap(Session.class).createSQLQuery(
					SQL_Query);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("expensename", expenses);
			query.setParameter("expensedtname", expensesdet);
			query.setParameter("companyid", companyid);
			
			expensetype = query.list();
			
		}else if(type.get(0).equals("Petty")) {
			
			String SQL_Query = "select DISTINCT(EXPENSES_NAME),EXPENSESDT_NAME FROM Expenses_type WHERE issueddt between :fromdate and :todate and company_id = :companyid and EXPENSES_NAME IN (:expensename) and EXPENSESDT_NAME In (:expensedtname) and EXPENSES_NAME ='Petty Cash' and EXPENSESDT_NAME ='Petty Cash'";
			Query query = entityManager.unwrap(Session.class).createSQLQuery(
					SQL_Query);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("expensename", expenses);
			query.setParameter("expensedtname", expensesdet);
			query.setParameter("companyid", companyid);
			
			expensetype = query.list();
			
			
		}
		return expensetype;
		
	}

	@Override
	public List<Expensestype> expensereport(Date fromdate, Date todate, String expenses, String expensesdet,
			Integer companyid) {
		Criteria cre = entityManager.unwrap(Session.class).createCriteria(Expensestype.class);
		cre.add(Restrictions.eq("companyid.id", companyid));
		cre.add(Restrictions.between("issueddate", fromdate, todate));
		cre.add(Restrictions.eq("expensename", expenses));
		cre.add(Restrictions.eq("expensedtname", expensesdet));
	
		return (List<Expensestype>) cre.list();
	}

	@Override
	public Double expensereportamount(Date fromdate, Date todate, String expenses, String expensesdet,
			Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Expensestype.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("expensename", expenses),
		    		Restrictions.eq("expensedtname", expensesdet)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double expensereporttaxamount(Date fromdate, Date todate, String expenses, String expensesdet,
			Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Expensestype.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.between("issueddate", fromdate, todate),
		            Restrictions.eq("expensename", expenses),
		    		Restrictions.eq("expensedtname", expensesdet)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("gstamount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double expensereportreportamount(Date fromdate, Date todate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Expensestype.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.between("issueddate", fromdate, todate)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("amount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public Double expensereportreportamount_tax(Date fromdate, Date todate, Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Expensestype.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.between("issueddate", fromdate, todate)
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("gstamount"));
		return (Double) cr.uniqueResult();
	}

	@Override
	public List<Expensestype> outside_report_expense_amount(List<String> bank, Integer companyid,
			List<String> expensetype, List<String> expensedet, Date fromdate, Date todate) {
		String SQL_Query = "select Sum(Amount) from Expenses_Type where COMPANY_ID =:companyid and issueddt between :fromdate and :todate and"
				+ "          BANK_NAME In (:bank) AND Expenses_NAME In (:expensestype) AND Expensesdt_NAME IN (:expensesdet) AND Expenses_NAME != 'Petty Cash' and  Expensesdt_NAME != 'Petty Cash' ";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("bank", bank);
		query.setParameter("expensestype", expensetype);
		query.setParameter("expensesdet", expensedet);
		
		List<Expensestype> list = query.list();

       return list;
	}

	@Override
	public List<Expensestype> outside_report_expense_gst(List<String> bank, Integer companyid, List<String> expensetype,
			List<String> expensedet, Date fromdate, Date todate) {
		String SQL_Query = "select Sum(GST_Amount) from Expenses_Type where COMPANY_ID =:companyid and issueddt between :fromdate and :todate and"
				+ "          BANK_NAME In (:bank) AND Expenses_NAME In (:expensestype) AND Expensesdt_NAME IN (:expensesdet) AND Expenses_NAME != 'Petty Cash' and  Expensesdt_NAME != 'Petty Cash' ";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("bank", bank);
		query.setParameter("expensestype", expensetype);
		query.setParameter("expensesdet", expensedet);
		
		List<Expensestype> list = query.list();

       return list;
	}

	@Override
	public List<Expensestype> outside_report_petty_amount(List<String> bank, Integer companyid,
			List<String> expensetype, List<String> expensedet, Date fromdate, Date todate) {
		String SQL_Query = "select Sum(Amount) from Expenses_Type where COMPANY_ID =:companyid and issueddt between :fromdate and :todate and"
				+ "          BANK_NAME In (:bank) AND Expenses_NAME = 'Petty Cash' and  Expensesdt_NAME = 'Petty Cash' ";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("bank", bank);
		
		List<Expensestype> list = query.list();

       return list;
	}

	@Override
	public List<Expensestype> outside_report_petty_gst(List<String> bank, Integer companyid, List<String> expensetype,
			List<String> expensedet, Date fromdate, Date todate) {
		String SQL_Query = "select Sum(GST_Amount) from Expenses_Type where COMPANY_ID =:companyid and issueddt between :fromdate and :todate and"
				+ "          BANK_NAME In (:bank) AND Expenses_NAME = 'Petty Cash' and  Expensesdt_NAME = 'Petty Cash' ";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		query.setParameter("companyid", companyid);
		query.setParameter("bank", bank);
		
		List<Expensestype> list = query.list();

       return list;
	}

}
