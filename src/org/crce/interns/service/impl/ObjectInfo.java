/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service.impl;

/**
 *
 * @author Leon
 */
public class ObjectInfo {

        private ObjectNode root;

        public ObjectInfo() {
            root = new ObjectNode("");
        }

        public void insert(String entry) {

            ObjectNode current = root;
            String s[] = entry.split(":");
            String p = s[0];
            s[0] = s[2];
            s[2] = p;
            for (String ch : s) {
                ObjectNode child = current.subNode(ch);
                if (child != null) {
                    current = child;
                } else {
                    current.childList.add(new ObjectNode(ch));
                    current = current.subNode(ch);
                }
                current.count++;
            }
            current.isEnd = true;
        }

        public boolean search(String entry) {
            ObjectNode current = root;
            for (String ch : entry.split(":")) {
                if (current.subNode(ch) == null) {
                    return false;
                } else {
                    current = current.subNode(ch);
                }
            }
            if (current.isEnd == true) {
                return true;
            }
            return false;
        }
    
}
