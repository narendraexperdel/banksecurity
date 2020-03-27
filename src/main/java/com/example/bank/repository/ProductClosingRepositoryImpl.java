package com.example.bank.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.CmcProduct;
import com.example.bank.model.ProductClosing;
import com.example.bank.model.Wettopup;

public class ProductClosingRepositoryImpl implements ProductClosingCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void saveproductclosing(ProductClosing productclosing) {
		String SQL_Query = "\r\n" + 
				"Insert into PRODUCT_CLOSING( Date, COMPANY_ID, PRODUCT_ID, CLOSING)\r\n" + 
				"VALUES(:date,:companyid,:productid,:closing)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("date", productclosing.getDate());
		query.setParameter("companyid", productclosing.getCompanyid());
		query.setParameter("productid", productclosing.getProductid());
		query.setParameter("closing", productclosing.getClosing());
		
		int result =  query.executeUpdate();
		
	}

	@Override
	public List<ProductClosing> getclosing(Integer companyid, String product, Date date) {
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(ProductClosing.class);
			cre.add(Restrictions.eq("companyid.id", companyid));
			cre.add(Restrictions.eq("productid", product));
			cre.add(Restrictions.eq("date", date));
		
			return (List<ProductClosing>) cre.list();
	}

	@Override
	public Double getclosingsumreport(Integer companyid, Date date) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(ProductClosing.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("date", date)
		       
		           /* Restrictions.eq("adjustmenttype", NULL),
		            Restrictions.eq("adjustmentcategory", null)*/
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("closing"));
		return (Double) cr.uniqueResult();
	}

}
