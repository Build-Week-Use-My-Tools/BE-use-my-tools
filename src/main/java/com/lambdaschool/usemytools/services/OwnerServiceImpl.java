package com.lambdaschool.usemytools.services;

import com.lambdaschool.usemytools.exceptions.ResourceNotFoundException;
import com.lambdaschool.usemytools.models.Owners;
import com.lambdaschool.usemytools.models.Tool;
import com.lambdaschool.usemytools.models.User;
import com.lambdaschool.usemytools.repository.OwnerRepository;
import com.lambdaschool.usemytools.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "ownerService")
public class OwnerServiceImpl implements OwnerService
{
    @Autowired
    private OwnerRepository ownrepos;

    @Autowired
    private UserRepository userrepos;

    @Override
    public List<Owners> findAll(Pageable pageable)
    {
        List<Owners> list = new ArrayList<>();
        ownrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Owners> findAll()
    {
        List<Owners> list = new ArrayList<>();
        ownrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Owners findOwnerById(long id)
    {
        return ownrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Owners> findByName(String name)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());
        if (currentUser.getOwners().contains(ownrepos.findById(id)))
        {
            if (ownrepos.findById(id).isPresent())
            {
                ownrepos.deleteById(id);
            } else
            {
                throw new EntityNotFoundException(Long.toString(id));
            }
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id) + " Not the logged in user");
        }
    }

    @Override
    public Owners save(Owners owner)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        Owners newOwner = new Owners();
        newOwner.setFirstname(owner.getFirstname());
        newOwner.setLastname(owner.getLastname());

        for(Tool t: owner.getTools())
        {
            newOwner.getTools().add(new Tool(t.getToolname(), t.getQuantity(), t.getPrice(), t.getImage(), t.isBorrowed(), t.getOwner()));
        }

//        for(User a: owner.getUsers())
//        {
//            newOwner.getUsers().add(new User(a.getUsername(), a.getPassword(), a.getUserRoles()));
//        }

        List<Owners> newOwn = currentUser.getOwners();
        newOwn.add(newOwner);
        currentUser.setOwners((newOwn));

        return ownrepos.save(newOwner);
    }
}
