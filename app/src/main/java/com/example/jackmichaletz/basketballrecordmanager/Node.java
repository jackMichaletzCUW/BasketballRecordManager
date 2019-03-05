package com.example.jackmichaletz.basketballrecordmanager;

public class Node
{
    private Node nextNode;
    private int payload;

    // Initialize the node
    public Node(int payload)
    {
        this.nextNode = null;
        this.payload = payload;
    }

    public void setNextNode(Node nextNode)
    {
        this.nextNode = nextNode;
    }

    public int getPayload()
    {
        return payload;
    }

    public Node getNextNode()
    {
        return nextNode;
    }

    @Override
    public String toString()
    {
        return String.format("%d", payload);
    }

    public void displayToScreen()
    {
        System.out.println(this.toString());
    }
}
