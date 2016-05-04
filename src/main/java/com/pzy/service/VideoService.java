
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

import com.pzy.entity.Category;
import com.pzy.entity.Video;
import com.pzy.repository.VideoRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class VideoService {
     @Autowired
     private VideoRepository videoRepository;

 	public List<Video> findTop3() {
 		return videoRepository.findAll(
 				new PageRequest(0, 15, new Sort(Direction.DESC, "createDate")))
 				.getContent();
 	}
     public List<Video> findAll() {
         return (List<Video>) videoRepository.findAll(new Sort(Direction.DESC, "id"));
     }
     public Page<Video> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Video> spec = new Specification<Video>() {
              public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (name != null) {
                   predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
              }
              return predicate;
              }
         };
         Page<Video> result = (Page<Video>) videoRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Video> findAll(final int pageNumber, final int pageSize,final String key,final Category category){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Video> spec = new Specification<Video>() {
              public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (key != null) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+key+"%"));
              }
              if (category != null) {
                  predicate.getExpressions().add(cb.equal(root.get("category").as(Category.class), category));
              }
              return predicate;
              }
         };
         Page<Video> result = (Page<Video>) videoRepository.findAll(spec, pageRequest);
         return result;
     	}
     
     public Page<Video> findAll(final int pageNumber, final int pageSize,final Integer type ){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Video> spec = new Specification<Video>() {
              public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (type != null) {
                  predicate.getExpressions().add(cb.equal(root.get("type").as(Integer.class),type));
               }
              return predicate;
              }
         };
         Page<Video> result = (Page<Video>) videoRepository.findAll(spec, pageRequest);
         return result;
     	}
		public void delete(Long id){
			videoRepository.delete(id);
		}
		public Video find(Long id){
			  return videoRepository.findOne(id);
		}
		public void save(Video video){
			videoRepository.save(video);
		}
		public List<Video> findBycategory(final Long id){
	         Specification<Video> spec = new Specification<Video>() {
	              public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	              Predicate predicate = cb.conjunction();
	              if (id != null) {
	                   predicate.getExpressions().add(cb.equal(root.get("category").get("id").as(Long.class), id));
	              }
	              return predicate;
	              }
	         };
	         return  videoRepository.findAll(spec);
	    }
}