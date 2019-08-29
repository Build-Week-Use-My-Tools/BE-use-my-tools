package com.lambdaschool.usemytools.repository;

import com.lambdaschool.usemytools.models.Owners;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface OwnerRepository extends PagingAndSortingRepository<Owners, Long>
{
    ArrayList<Owners> findOwnersByLastnameAndFirstname(String lastname, String firstname);

    Owners findByOwnerid(long id);

//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO owner(ownerid, toolid) values (:ownerid, :toolid)", nativeQuery = true)
//    void save(long ownerid, long toolid);

}
