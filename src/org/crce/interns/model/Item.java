/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.model;

import java.util.Objects;

/**
 *
 * @author Leon
 */
public class Item {

    public Item(String object, String predicate) {
        this.object = object;
        this.predicate = predicate;
    }
    public String object;
    public String predicate;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.object);
        hash = 79 * hash + Objects.hashCode(this.predicate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.object, other.object)) {
            return false;
        }
        if (!Objects.equals(this.predicate, other.predicate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "object=" + object + "+ predicate=" + predicate + '}';
    }

    

    

   
}
