/** * Game.java 
* Created on 2012-4-28 上午10:33:32 
*/

package com.base.arquilian.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** 
* <p>Project: arquillian-tutorial</p> 
* <p>Title: Game.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2012-2020.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

@Entity
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;

    public Game() {
    }

    public Game(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    @Override
    public String toString() {
        return "Game@" + hashCode() + "[id = " + id + "; title = " + title + "]";
    }
}
