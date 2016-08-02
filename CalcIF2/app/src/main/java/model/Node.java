package model;

/**
 * Created by alana on 02/08/16.
 */
public class Node {

    private double value;
    private char operator;
    private Node prox;

    public Node(double value, char operator) {
        this.operator = operator;
        this.value = value;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Node getProx() {
        return prox;
    }

    public void setProx(Node prox) {
        this.prox = prox;
    }
}
