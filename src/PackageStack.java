import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * Class PackageStack uses a Linked List of packages to implement a Stack.
 *
 * @author Kishore Thamilvanan
 * email : kishore.thamilvanan@stonybrook.edu
 * SBU ID: 111373510
 */
public class PackageStack extends Package
{
    private final int CAPACITY = 7;//capacity of the stack

    LinkedList<Package> stack  = new LinkedList<Package>();

    /**
     * Method adds a new package to the Stack at the top.
     *
     * @param x
     *      parameter X is a package with a user entered details which
     *          is to be added to the stack.
     *
     * @throws Exception
     *      throws exception when the stack is full.
     */
    public void push(Package x) throws Exception
    {
        if(stack.size() < CAPACITY)
        stack.push(x);

        else
            throw new Exception("The Stack is full.");
    }

    /**
     * method removes the package which is at the top of the stack.
     *
     * @return
     *      returns the object of the removed package.
     *
     * @throws EmptyStackException
     *      throws Exception when the stack is empty.
     */
    public Package pop() throws EmptyStackException
    {
        if(stack.size() > 0)
            return stack.pop();

        else
            throw new EmptyStackException();

    }

    /**
     * method returns the object at the top of the stack.
     *
     * @return
     *      returns the object at the top of the stack.
     */
    public Package peek()
    {
        if(stack.size() > 0)
            return stack.peek();

        else
            throw new EmptyStackException();

    }

    /**
     * method returns the size of the stack.
     *
     * @return
     *      returns the size of the stack.
     */
    public int size()
    {
        return stack.size();
    }

    /**
     * method checks the stack being full or not.
     *
     * @return
     *      returns the boolean value checking the stack being full or not.
     */
    public Boolean isFull()
    {
         return stack.size() == CAPACITY;
    }

    /**
     * method checks for the Stack being empty of not.
     *
     * @return
     *      returns a boolean value checking the stack being empty or not.
     */
    public Boolean isEmpty()
    {
            return stack.size() == 0;
    }

    /**
     * method returns the details of the stack as a String.
     *
     * @return
     *      returns the Stack details as a String.
     */
    public String toString() {

        LinkedList<Package> temp  = new LinkedList<Package>();

        int j= stack.size();
        String details = "";

        while(j>0)
        {
            temp.push(stack.pop());
            j--;
        }

        for(int i = temp.size();i>0;i--)
        {
            details += temp.peek().toString()+"\t";
            stack.push(temp.pop());

        }

        if(this.size() > 0)
            return details;

        else
            return "[Empty]";

    }
}

