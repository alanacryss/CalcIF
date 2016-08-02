package com.jho.alana.myStack;

import model.Node;

/**
 * Created by alana on 26/07/16.
 */
public class MyStack {

    Node prox = null;
    Node pt;

    public MyStack() {

    }

    public void push(double value, char operator) {
        Node node = new Node(value, operator);

        if (prox == null) {
            node.setProx(null);
            pt = node;
        }
    }

    public Node pop() {

        Node rec = pt;

        if (pt == null) {
            return null;
        }

        pt = pt.getProx();

        return rec;
    }

    public boolean isEmpty() {
        return pt == null;
    }
}
