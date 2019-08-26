package com.lambdaschool.usemytools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Owner", description = "The Owner Entity")
@Entity
@Table(name = "owner")
public class Owners extends Auditable
{
    @ApiModelProperty(name = "ownerid", value = "Primary key for Owner", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ownerid;

    @ApiModelProperty(name = "firstname", value = "Owner's First Name", required = true, example = "Ayn")
    private String firstname;

    @ApiModelProperty(name = "lastname", value = "Owner's Last Name", required = true, example = "Rand")
    private String lastname;


    @ManyToMany(mappedBy = "owners")
    @JsonIgnoreProperties("owners")
    private List<Tool> tools = new ArrayList<>();

    public Owners()
    {
    }

    public Owners(String firstname, String lastname)
    {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Owners(String lastname, String firstname, List<Tool> tools)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.tools = tools;
    }

    public long getOwnerid()
    {
        return ownerid;
    }

    public void setOwnerid(long ownerid)
    {
        this.ownerid = ownerid;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public List<Tool> getTools()
    {
        return tools;
    }

    public void setTools(List<Tool> tools)
    {
        this.tools = tools;
    }
}
