package model;

/**
 * Created by alana on 02/08/16.
 */
public class Node<E> {

    private E value;
    //private char operator;
    private Node prox;

    public Node(E value) {
        //this.operator = operator;
        this.value = value;
    }

    /*public char getOperator() {
        return operator;
    }*/

    /*public void setOperator(char operator) {
        this.operator = operator;
    }*/

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node getProx() {
        return prox;
    }

    public void setProx(Node prox) {
        this.prox = prox;
    }
}
