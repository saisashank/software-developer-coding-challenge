package com.traderev.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.traderev.model.UserDetails;
import com.traderev.vo.UserDetailsVO;

@Repository("userDetailsUpdateRepositoryImpl")
public class UserDetailsUpdateRepositoryImpl implements UserDetailsUpdateRepository {
	
	private final Logger logger = LoggerFactory.getLogger(UserDetailsUpdateRepositoryImpl.class);
	
	@PersistenceUnit(unitName="tradeRevApp")
	@Qualifier("tradeRevEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory;

	@Override
	public String updateUserDetails(UserDetailsVO userDetailsVO) {
		EntityManager em = null;
		try {
			em = entityManagerFactory.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			CriteriaUpdate<UserDetails> updateQuery = cb.createCriteriaUpdate(UserDetails.class);
			Root<UserDetails> updateUserDetails = updateQuery.from(UserDetails.class);
			
			if(!StringUtils.isEmpty(userDetailsVO.getPhoneNumber())) {
				updateQuery.set(updateUserDetails.get("phoneNumber"), userDetailsVO.getPhoneNumber());
			}

			if(!StringUtils.isEmpty(userDetailsVO.getFirstName())) {
				updateQuery.set(updateUserDetails.get("firstName"), userDetailsVO.getFirstName());
			}

			if(!StringUtils.isEmpty(userDetailsVO.getLastName())) {
				updateQuery.set(updateUserDetails.get("lastName"), userDetailsVO.getLastName());
			}
			
			List<Predicate> predicates = new ArrayList<>();
			Predicate conditionOne = cb.equal(updateUserDetails.get("userId"), userDetailsVO.getUserId());
			Predicate conditionTwo = cb.equal(updateUserDetails.get("emailAddress"), userDetailsVO.getEmailAddress());
			Predicate combinedCondition = cb.and(conditionOne,conditionTwo);
			predicates.add(combinedCondition);
			Predicate combinedPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
			updateQuery.where(combinedPredicate);
			em.createQuery(updateQuery).executeUpdate();
			transaction.commit();
		}catch (IllegalStateException | IllegalArgumentException e) {
			logger.info("Exception in the updateUserDetails "+e);
		} finally {
			if (em != null) {
				em.close();
				logger.info("closing entity manager in updateUserDetails");
			}
		}
		return "Successfully updated User Details";
	}

	@Override
	public String updateUserActiveStatus(UserDetailsVO userDetailsVO) {
		EntityManager em = null;
		try {
			em = entityManagerFactory.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			CriteriaUpdate<UserDetails> updateQuery = cb.createCriteriaUpdate(UserDetails.class);
			Root<UserDetails> updateUserDetails = updateQuery.from(UserDetails.class);
			updateQuery.set(updateUserDetails.get("isActive"),"N");
			List<Predicate> predicates = new ArrayList<>();
			Predicate conditionOne = cb.equal(updateUserDetails.get("userId"), userDetailsVO.getUserId());
			Predicate conditionTwo = cb.equal(updateUserDetails.get("emailAddress"), userDetailsVO.getEmailAddress());
			Predicate combinedCondition = cb.and(conditionOne,conditionTwo);
			predicates.add(combinedCondition);
			Predicate combinedPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
			updateQuery.where(combinedPredicate);
			em.createQuery(updateQuery).executeUpdate();
			transaction.commit();
		}catch (IllegalStateException | IllegalArgumentException e) {
			logger.info("Exception in the updateUserActiveStatus "+e);
		} finally {
			if (em != null) {
				em.close();
				logger.info("closing entity manager in updateUserActiveStatus");
			}
		}
		return "Successfully updated User Active Status";
	}

	@Override
	public String deleteUserDetails(Long userDetailsId) {
		EntityManager em = null;
		try {
			em = entityManagerFactory.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			CriteriaDelete<UserDetails> deleteQuery = cb.createCriteriaDelete(UserDetails.class);
			Root<UserDetails> deleteUserDetails = deleteQuery.from(UserDetails.class);
			Expression<Integer> expression = deleteUserDetails.get("userDetailsId");
			Predicate predicate = expression.in(userDetailsId);
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(predicate);
			Predicate combinedPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
			deleteQuery.where(combinedPredicate);
			em.createQuery(deleteQuery).executeUpdate();
			transaction.commit();
		}catch (IllegalStateException | IllegalArgumentException e) {
			logger.info("Exception in the deleteUserDetails "+e);
		} finally {
			if (em != null) {
				em.close();
				logger.info("closing entity manager in deleteUserDetails");
			}
		}
		return "Successfully deleted user record's";
	}

}
