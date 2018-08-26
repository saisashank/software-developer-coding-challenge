package com.traderev.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.traderev.model.CarDetails;
import com.traderev.vo.UserCarBidVO;

@Repository("carUpdateDetailsRepositoryImpl")
public class CarUpdateDetailsRepositoryImpl implements CarUpdateDetailsRepository{
	
	private final Logger logger = LoggerFactory.getLogger(CarUpdateDetailsRepositoryImpl.class);
	
	@PersistenceUnit(unitName="tradeRevApp")
	@Qualifier("tradeRevEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void updateCarAvailability(UserCarBidVO userCarBidVO) {
		EntityManager em = null;
		try {
			em = entityManagerFactory.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			CriteriaUpdate<CarDetails> updateQuery = cb.createCriteriaUpdate(CarDetails.class);
			Root<CarDetails> updateCarDetails = updateQuery.from(CarDetails.class);
			updateQuery.set(updateCarDetails.get("carAvailability"), "N");
			List<Predicate> predicates = new ArrayList<>();
			Predicate conditionOne = cb.equal(updateCarDetails.get("carCompany"),userCarBidVO.getCar());
			predicates.add(conditionOne);
			Predicate combinedPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
			updateQuery.where(combinedPredicate);
			em.createQuery(updateQuery).executeUpdate();
			transaction.commit();
		}catch (IllegalStateException | IllegalArgumentException e) {
			logger.info("Exception in the getCarHistoryBid "+e);
		} finally {
			if (em != null) {
				em.close();
				logger.info("closing entity manager in getCarHistoryBid");
			}
		}
	}

}
