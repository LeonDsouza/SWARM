/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service.impl;

import java.util.LinkedList;

/**
 *
 * @author Leon
 */
public class ObjectNode {

    String content;
    boolean isEnd;
    int count;
    LinkedList<ObjectNode> childList;

    /* Constructor */
    public ObjectNode(String c) {
        childList = new LinkedList<ObjectNode>();
        isEnd = false;
        content = c;
        count = 0;
    }

    public ObjectNode subNode(String c) {
        if (childList != null) {
            for (ObjectNode eachChild : childList) {
                //System.out.println(eachChild.content);
                if (eachChild.content.equals(c)) {
                    return eachChild;
                }
            }
        }
        return null;
    }

}
