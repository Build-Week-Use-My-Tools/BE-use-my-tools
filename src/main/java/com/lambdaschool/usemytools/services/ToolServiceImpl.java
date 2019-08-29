package com.lambdaschool.usemytools.services;

import com.lambdaschool.usemytools.models.Owners;
import com.lambdaschool.usemytools.models.Tool;
import com.lambdaschool.usemytools.models.User;
import com.lambdaschool.usemytools.repository.OwnerRepository;
import com.lambdaschool.usemytools.repository.ToolRepository;
import com.lambdaschool.usemytools.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "toolService")
public class ToolServiceImpl implements ToolService
{
    @Autowired
    ToolRepository toolrepos;

    @Autowired
    OwnerRepository ownrepos;

    @Autowired
    private UserRepository userrepos;

    @Override
    public ArrayList<Tool> findAll(Pageable pageable)
    {
        ArrayList<Tool> list = new ArrayList<>();
        toolrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public ArrayList<Tool> findAll()
    {
        ArrayList<Tool> list = new ArrayList<>();
        toolrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Tool findToolById(long id) throws EntityNotFoundException
    {
        return toolrepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Tool> findToolByNameLike(String name, Pageable pageable)
    {
        List<Tool> list = new ArrayList<>();
        toolrepos.findByToolnameContainingIgnoreCase(name, pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {

        if(toolrepos.findById(id).isPresent())
        {
            toolrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Tool save(Tool tool)
    {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = userrepos.findByUsername(authentication.getName());
//        currentUser.getOwners().contains(ownrepos.findOwnersByLastnameAndFirstname(currentUser.getLastname(), currentUser.getFirstname()));


        Tool newTool = new Tool();

        newTool.setToolname(tool.getToolname());
        newTool.setQuantity(tool.getQuantity());
        newTool.setPrice(tool.getPrice());
        newTool.setImage(tool.getImage());
        newTool.setBorrowed(tool.isBorrowed());

//        for(Owners o: tool.getOwner())
//        {
//            newTool.getOwner().add(new Owners(o.getLastname(), o.getFirstname()));
//        }

        return toolrepos.save(newTool);
    }

//    @Override
//    public void savetoOwner(long tool, long ownerid)
//    {
//        toolrepos.savetoOwner(tool, ownerid);
//    }


    @Override
    public Tool update(Tool tool, long id)
    {
        Tool currentTool = toolrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (tool.getToolname() != null)
        {
            currentTool.setToolname(tool.getToolname());
        }
        return toolrepos.save(currentTool);
    }
}
