package com.lambdaschool.usemytools.controllers;

import com.lambdaschool.usemytools.models.Owners;
import com.lambdaschool.usemytools.models.Tool;
import com.lambdaschool.usemytools.models.ErrorDetail;
import com.lambdaschool.usemytools.services.OwnerService;
import com.lambdaschool.usemytools.services.ToolService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ToolController
{
    @Autowired
    ToolService toolService;

    @Autowired
    OwnerService ownerService;

    @ApiOperation(value = "Return all tools and their owners", response = Tool.class, responseContainer = "List")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                                                 value = "Results page you want to retrieve (0..N)"),
                               @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                                                 value = "Number of records per page."),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                                 value = "Sorting criteria in the format: property(,asc|desc). " +
                                                         "Default sort order is ascending. " +
                                                         "Multiple sort criteria are supported.")})
    @GetMapping(value = "/tools", produces = {"application/json"})
    public ResponseEntity<?> listRoles(@PageableDefault(page = 0,
                                                        size = 25)
                                               Pageable pageable)
    {
        List<Tool> allTools = toolService.findAll(pageable);
        return new ResponseEntity<>(allTools, HttpStatus.OK);
    }

    @ApiOperation(value = "Updates a Tool",
                  response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tool Updated Successfully", response = void.class),
            @ApiResponse(code = 404, message = "Error updating Tool", response = ErrorDetail.class)
    } )
    @PutMapping(value = "/data/tools/{id}")
    public ResponseEntity<?> updateTool(@RequestBody
                                                Tool updateTool,
                                        @PathVariable
                                                long id)
    {
        toolService.update(updateTool, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Adds a new tool",
                  response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Tool added Successfully", response = void.class),
            @ApiResponse(code = 404, message = "Error adding tool", response = ErrorDetail.class)
    } )
    @PostMapping(value = "/newtool")
    public ResponseEntity<?> addNewTool(HttpServletRequest request, @Valid
    @RequestBody
            Tool newTool) throws URISyntaxException
    {

        newTool = toolService.save(newTool);
        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{toolid}").buildAndExpand(newTool.getToolid()).toUri();
        responseHeaders.setLocation(newQuoteURI);

        return new ResponseEntity<>(toolService.findAll(), responseHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Adds a tool to an owner",
                  response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Tool added to owner Successfully", response = void.class),
            @ApiResponse(code = 404, message = "Error adding tool to owner", response = ErrorDetail.class)
    } )
    @PostMapping(value = "/data/{toolid}/owners/{ownerid}")
    public ResponseEntity<?> addToolOwners(@PathVariable long toolid, @PathVariable long ownerid)
    {
        toolService.savetoOwner(toolid, ownerid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a Tool", notes = "The tool will be deleted from the database.",
                  response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tool Deleted Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error Deleting Tool", response = ErrorDetail.class)
    } )
    @DeleteMapping("/data/tools/{id}")
    public ResponseEntity<?> deleteToolById(
            @PathVariable
                    long id)
    {
        toolService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}