package com.lambdaschool.usemytools.repository;

import com.lambdaschool.usemytools.models.Tool;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ToolRepository extends PagingAndSortingRepository<Tool, Long>
{
    List<Tool> findByToolnameContainingIgnoreCase(String toolname, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO owns(toolid, ownerid) values (:toolid, :ownerid)", nativeQuery = true)
    void savetoOwner(long toolid, long ownerid);


}
