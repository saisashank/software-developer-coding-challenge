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
import org.springframework.util.StringUtils;

import com.traderev.model.CarDetails;
import com.traderev.vo.CarDetailsVO;

@Repository("carUpdateDetailsRepositoryImpl")
public class CarUpdateDetailsRepositoryImpl implements CarUpdateDetailsRepository{
	
	private final Logger logger = LoggerFactory.getLogger(CarUpdateDetailsRepositoryImpl.class);
	
	@PersistenceUnit(unitName="tradeRevApp")
	@Qualifier("tradeRevEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory;

	@Override
	public String updateCarAvailability(String carName) {
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
			Predicate conditionOne = cb.equal(updateCarDetails.get("carCompany"),carName);
			Predicate conditionTwo = cb.equal(updateCarDetails.get("carAvailability"),"Y");
			Predicate combinedCondition = cb.and(conditionOne,conditionTwo);
			predicates.add(combinedCondition);
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
		return "Successful Updation of Car's Status";
	}

	
	@Override
	public String updateCarDetails(CarDetailsVO carDetailsVO) {
		EntityManager em = null;
		try {
			if(carDetailsVO.getBasePrice() != null || !StringUtils.isEmpty(carDetailsVO.getCarModel())) {
				em = entityManagerFactory.createEntityManager();
				CriteriaBuilder cb = em.getCriteriaBuilder();
				EntityTransaction transaction = em.getTransaction();
				transaction.begin();
				CriteriaUpdate<CarDetails> updateQuery = cb.createCriteriaUpdate(CarDetails.class);
				Root<CarDetails> updateCarDetails = updateQuery.from(CarDetails.class);
				
				if(!StringUtils.isEmpty(carDetailsVO.getCarModel())) {
					updateQuery.set(updateCarDetails.get("carModel"), carDetailsVO.getCarModel());
				}
				
				if(carDetailsVO.getBasePrice() != null) {
					updateQuery.set(updateCarDetails.get("basePrice"), carDetailsVO.getBasePrice());
				}
				List<Predicate> predicates = new ArrayList<>();
				Predicate conditionOne = cb.equal(updateCarDetails.get("carCompany"),carDetailsVO.getCarCompany());
				predicates.add(conditionOne);
				Predicate combinedPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
				updateQuery.where(combinedPredicate);
				em.createQuery(updateQuery).executeUpdate();
				transaction.commit();
			}
		}catch (IllegalStateException | IllegalArgumentException e) {
			logger.info("Exception in the updateCarDetails "+e);
		} finally {
			if (em != null) {
				em.close();
				logger.info("closing entity manager in updateCarDetails");
			}
		}
		return "Successful Updation of Car's Details";
	}
	

}
