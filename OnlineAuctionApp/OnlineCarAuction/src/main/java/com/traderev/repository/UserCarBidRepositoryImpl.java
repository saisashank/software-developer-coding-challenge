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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.traderev.model.UserCarBid;
import com.traderev.vo.UserCarBidVO;

@Repository("userCarBidRepositoryImpl")
public class UserCarBidRepositoryImpl implements CarBidAmountRepository {
	
	@PersistenceUnit(unitName="tradeRevApp")
	@Qualifier("tradeRevEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void updateUserCarBid(UserCarBidVO userCarBidVO) {
		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			ObjectMapper objectMapper = new ObjectMapper();
			String carBidAmount = objectMapper.writeValueAsString(userCarBidVO.getCarBidAmountVO());
			CriteriaUpdate<UserCarBid> updateQuery = cb.createCriteriaUpdate(UserCarBid.class);
			Root<UserCarBid> updateUserCarBid = updateQuery.from(UserCarBid.class);
			updateQuery.set(updateUserCarBid.get("carBidAmount"), carBidAmount);
			List<Predicate> predicates = new ArrayList<>();
			Predicate conditionOne = cb.equal(updateUserCarBid.get("userId"),userCarBidVO.getUserId());
			predicates.add(conditionOne);
			Predicate combinedPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
			updateQuery.where(combinedPredicate);
			em.createQuery(updateQuery).executeUpdate();
			transaction.commit();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
