package com.lambdaschool.usemytools.services;

import com.lambdaschool.usemytools.models.Owners;
import com.lambdaschool.usemytools.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "ownerService")
public class OwnerServiceImpl implements OwnerService
{
    @Autowired
    private OwnerRepository ownrepos;

    @Override
    public List<Owners> findAll(Pageable pageable)
    {
        List<Owners> list = new ArrayList<>();
        ownrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

//    @Override
//    public Owners findOwnerById(long id)
//    {
//        return null;
//    }
//
//    @Override
//    public List<Owners> findByName(String name)
//    {
//        return null;
//    }
//
//    @Override
//    public void delete(long id)
//    {
//
//    }
//
//    @Override
//    public Owners save(Owners owner)
//    {
//        return null;
//    }
}
