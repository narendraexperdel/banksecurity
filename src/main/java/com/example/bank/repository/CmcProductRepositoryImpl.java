package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.CmcProduct;
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.Wettopup;

public class CmcProductRepositoryImpl implements CmcProductCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CmcProduct> getallproductbycompany(Integer companyid) {
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(CmcProduct.class);
		cre.add(Restrictions.eq("companyid.id", companyid));
		cre.add(Restrictions.eq("close", "No"));
	
		return (List<CmcProduct>) cre.list();
	}

	@Override
	public List<CmcProduct> getclosing(Integer companyid, String product) {
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(CmcProduct.class);
			cre.add(Restrictions.eq("companyid.id", companyid));
			cre.add(Restrictions.eq("close", "No"));
			cre.add(Restrictions.eq("fldesc", product));
		
			return (List<CmcProduct>) cre.list();
	}

	@Override
	public Double getreportclosing(Integer companyid) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(CmcProduct.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.eq("close", "No")
		           /* Restrictions.eq("adjustmenttype", NULL),
		            Restrictions.eq("adjustmentcategory", null)*/
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.sum("closing"));
		return (Double) cr.uniqueResult();
	}

}
