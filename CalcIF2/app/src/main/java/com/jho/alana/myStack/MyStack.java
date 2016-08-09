package com.jho.alana.myStack;

import model.Node;

/**
 * Created by alana on 26/07/16.
 */
public class MyStack<E> {

    Node prox = null;
    Node pt;

    public MyStack() {

    }

    public void push(E value) {
        Node node = new Node(value);

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

    public E top(){
        return pt != null ? (E) pt.getValue() : null;
    }

    public boolean isEmpty() {
        return pt == null;
    }
}
