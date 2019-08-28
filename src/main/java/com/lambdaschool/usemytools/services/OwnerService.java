package com.lambdaschool.usemytools.services;

import com.lambdaschool.usemytools.models.Owners;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerService
{
    List<Owners> findAll(Pageable pageable);

    Owners findOwnerById(long id);

    List<Owners> findByName(String name);

    void delete(long id);

    Owners save(Owners owner);

}
