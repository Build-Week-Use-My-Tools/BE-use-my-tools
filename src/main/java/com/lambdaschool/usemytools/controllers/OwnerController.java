package com.lambdaschool.usemytools.controllers;

import com.lambdaschool.usemytools.models.ErrorDetail;
import com.lambdaschool.usemytools.models.Owners;
import com.lambdaschool.usemytools.services.OwnerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

    @ApiOperation(value = "Creates a new owner and assigns it to logged in user",
                  response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Owner name added Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error adding in Owner", response = ErrorDetail.class)
    } )
    @PostMapping(value = "/newowner", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createExperience(@Valid
                                              @RequestBody
                                                      Owners newowners) throws URISyntaxException
    {
        newowners =  ownerService.save(newowners);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newExpURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{ownerid}")
                .buildAndExpand(newowners.getOwnerid())
                .toUri();
        responseHeaders.setLocation(newExpURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
