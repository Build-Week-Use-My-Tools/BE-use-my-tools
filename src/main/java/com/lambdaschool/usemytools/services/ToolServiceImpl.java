package com.lambdaschool.usemytools.services;

import com.lambdaschool.usemytools.models.Owners;
import com.lambdaschool.usemytools.models.Tool;
import com.lambdaschool.usemytools.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "toolService")
public class ToolServiceImpl implements ToolService
{
    @Autowired
    ToolRepository toolrepos;

    @Override
    public ArrayList<Tool> findAll(Pageable pageable)
    {
        ArrayList<Tool> list = new ArrayList<>();
        toolrepos.findAll(pageable).iterator().forEachRemaining(list::add);
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
        Tool newTool = new Tool();

        newTool.setToolname(tool.getToolname());

        for(Owners a: tool.getOwners())
        {
            newTool.getOwners().add(new Owners(a.getLastname(), a.getFirstname()));
        }

        return toolrepos.save(newTool);
    }

    @Override
    public void savetoOwner(long tool, long ownerid)
    {
        toolrepos.savetoOwner(tool, ownerid);
    }

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
