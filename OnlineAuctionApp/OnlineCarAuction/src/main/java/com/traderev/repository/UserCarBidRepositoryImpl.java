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

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.traderev.model.UserCarBid;
import com.traderev.vo.UserCarBidVO;

@Repository("userCarBidRepositoryImpl")
public class UserCarBidRepositoryImpl implements CarBidAmountRepository {
	
	@PersistenceUnit(unitName="tradeRevApp")
	@Qualifier("tradeRevEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void updateUserCarBid(UserCarBidVO userCarBidVO) {
		EntityManager em = entityManagerFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		CriteriaUpdate<UserCarBid> updateQuery = cb.createCriteriaUpdate(UserCarBid.class);
		Root<UserCarBid> updateUserCarBid = updateQuery.from(UserCarBid.class);
		updateQuery.set(updateUserCarBid.get("bidAmount"), userCarBidVO.getBidAmount());
		List<Predicate> predicates = new ArrayList<>();
		Predicate conditionOne = cb.equal(updateUserCarBid.get("userId"),userCarBidVO.getUserId());
		Predicate conditionTwo = cb.equal(updateUserCarBid.get("carName"),userCarBidVO.getCar());
		Predicate combinedCondition = cb.and(conditionOne,conditionTwo);
		predicates.add(combinedCondition);
		Predicate combinedPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
		updateQuery.where(combinedPredicate);
		em.createQuery(updateQuery).executeUpdate();
		transaction.commit();
	}
}
