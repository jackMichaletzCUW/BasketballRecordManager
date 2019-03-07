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

    public int getSize()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return (this.head == null);
    }

    public void removeAtIndex(int index)
    {
        if(index == 0)
        {
            this.removeFront();
        }
        else if(index == (count - 1))
        {
            this.removeEnd();
        }
        else
        {
            this.getAtIndex(index - 1).setNextNode(this.getAtIndex(index).getNextNode());
            count--;
        }
    }

    public void addAtIndex(int value, int index)
    {
        Node input = new Node(value);

        if(index == 0)
        {
            this.addFront(input);
        }
        else if(index == (count - 1))
        {
            this.addEnd(input);
        }
        else
        {
            input.setNextNode(this.getAtIndex(index - 1).getNextNode());
            this.getAtIndex(index - 1).setNextNode(input);
        }

        count++;
    }

    public void addAtIndex(Node input, int index)
    {
        if(index == 0)
        {
            this.addFront(input);
        }
        else if(index == (count - 1))
        {
            this.addEnd(input);
        }
        else
        {
            input.setNextNode(this.getAtIndex(index - 1).getNextNode());
            this.getAtIndex(index - 1).setNextNode(input);
        }

        count++;
    }

    public Node getAtIndex(int index)
    {
        Node currentNode = this.head;

        for(int nc = 0; nc < index; nc++)
        {
            currentNode = currentNode.getNextNode();
        }

        return currentNode;
    }

    public int getValueAtIndex(int index)
    {
        Node currentNode = this.head;

        for(int nc = 0; nc < index; nc++)
        {
            currentNode = currentNode.getNextNode();
        }

        return currentNode.getPayload();
    }

    public int getIndex(Node input)
    {
        Node currentNode = this.head;

        int currentIndex = 0;

        while(currentNode.getNextNode() != null)
        {
            if(currentNode == input)
            {
                return currentIndex;
            }
            else
            {
                currentIndex++;
            }
        }

        // If the node was not found, return -1
        return -1;
    }

    public void removeFront()
    {
        if(this.head != null)
        {
            if(this.head.getNextNode() != null)
            {
                this.head = this.head.getNextNode();
            }
            else
            {
                this.head = null;
            }

            count--;
        }
    }

    public void removeEnd()
    {
        if(this.tail != null)
        {
            this.getAtIndex(count - 1).setNextNode(null);
            this.tail = this.getAtIndex(count - 1);

            count--;
        }
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

    public void addFront(int value)
    {
        Node input = new Node(value);

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

    public void addEnd(int value)
    {
        Node input = new Node(value);

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

            // Don't print a comma as the last char in the String
            if(nc != (count - 1))
            {
                output += ",";
            }

            currentNode = currentNode.getNextNode();
        }

        return output;
    }

    public void displayToScreen()
    {
        System.out.println(this.toString());
    }
}
