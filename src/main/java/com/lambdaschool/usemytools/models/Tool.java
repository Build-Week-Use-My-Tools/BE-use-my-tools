package com.lambdaschool.usemytools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Tool", description = "The Tool Entity")
@Entity
@Table(name = "tool")
public class Tool extends Auditable
{
    @ApiModelProperty(name = "toolid", value = "Primary key for the tool", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long toolid;

    @ApiModelProperty(name = "toolname", value = "Name Of Tool", required = true, example = "Hammer")
    private String toolname;

    @ApiModelProperty(name = "quantity", value = "The amounts of tools the owner is posting", required = false, example = "12202017")
    private int quantity;

    @ApiModelProperty(name = "price", value = "The price to borrow the tool", required = false, example = "12202017")
    private int price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "owns", joinColumns = {@JoinColumn(name = "toolid")},
               inverseJoinColumns = {@JoinColumn(name = "ownerid")})
    @JsonIgnoreProperties("tools")
    private List<Owners> owners = new ArrayList<>();

    public Tool()
    {
    }

    public Tool(String toolname)
    {
        this.toolname = toolname;
    }

    public Tool(String toolname, int quantity)
    {
        this.toolname = toolname;
        this.quantity = quantity;
    }

    public Tool(String toolname, int quantity, List<Owners> owners)
    {
        this.toolname = toolname;
        this.quantity = quantity;
        this.owners = owners;
    }

    public Tool(String toolname, int quantity, int price, List<Owners> owners)
    {
        this.toolname = toolname;
        this.quantity = quantity;
        this.price = price;
        this.owners = owners;
    }

    public long getToolid()
    {
        return toolid;
    }

    public void setToolid(long toolid)
    {
        this.toolid = toolid;
    }

    public String getToolname()
    {
        return toolname;
    }

    public void setToolname(String toolname)
    {
        this.toolname = toolname;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public List<Owners> getOwners()
    {
        return owners;
    }

    public void setOwners(List<Owners> owners)
    {
        this.owners = owners;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
}
