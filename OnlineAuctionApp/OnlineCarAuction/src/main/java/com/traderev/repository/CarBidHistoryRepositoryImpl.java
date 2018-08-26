package com.traderev.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.traderev.model.UserCarBid;

@Repository("carBidHistoryRepositoryImpl")
public class CarBidHistoryRepositoryImpl implements CarBidHistoryRepository {
	
	private final Logger logger = LoggerFactory.getLogger(CarBidHistoryRepositoryImpl.class);
	
	@PersistenceUnit(unitName="tradeRevApp")
	@Qualifier("tradeRevEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<UserCarBid> getCarHistoryBid(String carName) {
		List<UserCarBid> userCarBidList = new ArrayList<>();
		EntityManager em = null;
		try {
			em = entityManagerFactory.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<UserCarBid> createQuery = cb.createQuery(UserCarBid.class);
			Root<UserCarBid> userCarBid = createQuery.from(UserCarBid.class);
			createQuery.select(userCarBid);
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(cb.equal(userCarBid.get("carName"), carName));
			Predicate combinedPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
			createQuery.where(combinedPredicate);
			List<Order> orderList = new ArrayList<>();
			orderList.add(cb.desc(userCarBid.get("bidAmount")));
			createQuery.orderBy(orderList);
			TypedQuery<UserCarBid> typedQuery= em.createQuery(createQuery); 
			userCarBidList = typedQuery.getResultList();
		}catch (IllegalStateException | IllegalArgumentException e) {
			logger.info("Exception in the getCarHistoryBid "+e);
		} finally {
			if (em != null) {
				em.close();
				logger.info("closing entity manager in getCarHistoryBid");
			}
		}
		return userCarBidList;
	}

}
