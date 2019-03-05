package com.example.jackmichaletz.basketballrecordmanager;

public class LinkedList
{
    private Node head;
    private int count;

    // Creating this object to avoid a (potentially) long loop being run whenever addEnd() is called.
    // -> This is the last node in the linkedList.
    private Node tail;

    public LinkedList()
    {
        this.head = null;
        this.count = 0;
    }

    public void addFront(Node input)
    {
        if(this.head == null)
        {
            this.head = input;

            // Only set tail for this case of addFront (empty list), as when adding more elements
            // -> to the front of the list, the tail remains the same.
            this.tail = input;
        }
        else
        {
            input.setNextNode(this.head);
            this.head = input;
        }

        count++;
    }

    public void addEnd(Node input)
    {
        if(this.head == null)
        {
            this.head = input;
            this.tail = input;
        }
        else
        {
            this.tail.setNextNode(input);
            this.tail = input;
        }

        count++;
    }

    @Override
    public String toString()
    {
        String output = "";
        Node currentNode = this.head;

        for(int nc = 0; nc < count; nc++)
        {
            output += currentNode.toString();
            currentNode = currentNode.getNextNode();
        }

        return output;
    }

    public void displayToScreen()
    {
        System.out.println(this.toString());
    }
}
