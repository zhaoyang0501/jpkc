package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.SubmitWork;
public interface SubmitWorkRepository extends PagingAndSortingRepository<SubmitWork, Long>,JpaSpecificationExecutor<SubmitWork>{
}

