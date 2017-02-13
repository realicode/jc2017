package com.realaicy.prod.jc.modules.demo.model.mybatis;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * The type City.
 *
 * @author liuzh_3nofxnp
 * @since 2016 -01-22 22:16
 */
public class City {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

    private String name;

    private String state;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

}
