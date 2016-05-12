
package com.pzy.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pzy.entity.SubmitWork;
import com.pzy.repository.SubmitWorkRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class SubmitWorkService {
     @Autowired
     private SubmitWorkRepository submitWorkRepository;

 	public List<SubmitWork> findTop3() {
 		return submitWorkRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
 	
 	
     public List<SubmitWork> findAll() {
         return (List<SubmitWork>) submitWorkRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<SubmitWork> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<SubmitWork> spec = new Specification<SubmitWork>() {
              public Predicate toPredicate(Root<SubmitWork> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<SubmitWork> result = (Page<SubmitWork>) submitWorkRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<SubmitWork> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<SubmitWork> spec = new Specification<SubmitWork>() {
              public Predicate toPredicate(Root<SubmitWork> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<SubmitWork> result = (Page<SubmitWork>) submitWorkRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			submitWorkRepository.delete(id);
		}
		public SubmitWork find(Long id){
			  return submitWorkRepository.findOne(id);
		}
		public void save(SubmitWork submitWork){
			submitWorkRepository.save(submitWork);
		}
}