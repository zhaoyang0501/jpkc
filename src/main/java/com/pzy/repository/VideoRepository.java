package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Video;
public interface VideoRepository extends PagingAndSortingRepository<Video, Long>,JpaSpecificationExecutor<Video>{
}

