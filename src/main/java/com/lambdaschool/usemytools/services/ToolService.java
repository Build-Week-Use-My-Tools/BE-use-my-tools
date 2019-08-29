package com.lambdaschool.usemytools.services;

import com.lambdaschool.usemytools.models.Tool;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface ToolService
{
    ArrayList<Tool> findAll(Pageable pageable);
    ArrayList<Tool> findAll();

    Tool findToolById(long id);

    List<Tool> findToolByNameLike(String name, Pageable pageable);

//    ArrayList<CountToolsFromOwners> getCountToolsFromOwners();

    void delete(long id);

    Tool save(Tool tool);

    void savetoOwner(long toolid, long ownerid);

    Tool update(Tool tool, long id);
}
