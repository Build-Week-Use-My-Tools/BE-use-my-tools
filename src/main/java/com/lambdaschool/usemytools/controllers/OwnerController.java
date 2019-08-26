package com.lambdaschool.usemytools.controllers;

import com.lambdaschool.usemytools.models.Owners;
import com.lambdaschool.usemytools.services.OwnerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OwnerController
{
    @Autowired
    OwnerService ownerService;

    @ApiOperation(value = "Return all owners and their tools", response = Owners.class, responseContainer = "List")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                                                 value = "Results page you want to retrieve (0..N)"),
                               @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                                                 value = "Number of records per page."),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                                 value = "Sorting criteria in the format: property(,asc|desc). " +
                                                         "Default sort order is ascending. " +
                                                         "Multiple sort criteria are supported.")})
    @GetMapping(value = "/owners", produces = {"application/json"})
    public ResponseEntity<?> listOwners(@PageableDefault(page = 0,
                                                          size = 5)
                                                 Pageable pageable)
    {
        List<Owners> allOwners = ownerService.findAll(pageable);
        return new ResponseEntity<>(allOwners, HttpStatus.OK);
    }
}
