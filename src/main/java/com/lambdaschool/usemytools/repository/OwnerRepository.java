package com.lambdaschool.usemytools.repository;

import com.lambdaschool.usemytools.models.Owners;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface OwnerRepository extends PagingAndSortingRepository<Owners, Long>
{
    ArrayList<Owners> findAuthorsByLastnameAndFirstname(String lastname, String firstname);

}
