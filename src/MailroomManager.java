import java.util.Scanner;

/**
 * Class Mailroom Manager uses LinkedLists to implements.
 * class does UI functions.
 *
 * @author Kishore Thamilvanan
 * email : kishore.thamilvanan@stonybrook.edu
 * SBU ID: 111373510
 */
public class MailroomManager extends PackageStack
{
    static PackageStack[] stacks = new PackageStack[6];//array of stacks.
    public static Scanner input = new Scanner(System.in);
    static int day=0;//parameter to indicate the current date

    /**
     * prints the menu of all UI functions available.
     */
    static void menu()
    {

        System.out.print("\n\n\t\t\t** MENU **\n");
        System.out.print("\n D) Deliver a package.");
        System.out.print("\n G) Get someone's package.");
        System.out.print("\n T) Make it tomorrow.");
        System.out.print("\n P) Print the stacks.");
        System.out.print("\n M) Move a package from one stack to another.");
        System.out.print("\n F) Find packages in the wrong stack and move to floor.");
        System.out.print("\n L) List of all packages awaiting a user.");
        System.out.print("\n E) Empty the floor.");
        System.out.print("\n S) Sort the packages in stacks according to name.");
        System.out.print("\n Q) Quit.");
        System.out.print("\n\n Please select an option: ");
    }

    /**
     * returns the current day.
     *
     * @return
     *      returns the current day.
     */
    public static int getCurrentDate() { return day; }

    /**
     * sets the current date.
     *
     * @param newDay
     *      recieved parameter which is set to the current day.
     */
    public static void setCurrentDate(int newDay)
    {
        day = newDay;
    }

    /**
     * method checks the first letter of the name of the recipient
     *       and returns the suitable stack number where the
     *              package is supposed to be placed.
     *
     * @param c
     *      it is the first letter of the name of the recipient.
     *
     * @return
     *      returns the suitable stack number.
     */
    public static int nameCheck(char c)
    {
        int i=0;
        if((c>='A' && c<='G') || (c>='a' && c<='g'))
            i=0;

        else if((c>='H' && c<='J') || (c>='h' && c<='j'))
            i=1;

        else if((c>='K' && c<='M') || (c>='k' && c<='m'))
            i=2;

        else if((c>='N' && c<='R') || (c>='n' && c<='r'))
            i=3;

        else if((c>='S' && c<='Z') || (c>='s' && c<='z'))
            i=4;

        return i;
    }

    /**
     * method brings up the required package by poping the packages above it
     *
     * @param name
     *      name of the recipient whose package is to be brought up.
     *
     * @return
     *      returns the true if package exists else retrns false.
     *
     * @throws Exception
     *      throws Exception when the stack is empty.
     */
    public static boolean bringUpPackage(String name) throws Exception
    {
        int i=0,j=0,flag=0;

        i = nameCheck(name.charAt(0));

        do{

            if(stacks[i].peek().getRecipient().equals(name))
            {
                flag=1;
                break;
            }

            else
            {
                movePackage(i,5);
            }

        }while(stacks[i].size()>0);

        if(flag==0)
        {
            for(j=0;j<stacks[5].size();j++)
            movePackage(5,i);
            return false;
        }

        else
            return true;

    }

    /**
     * method moves the package from the user entered position and destination.
     *
     * @param from
     *      integer which indicates the number of the stack from which
     *              the package has to be moved.
     * @param to
     *      integer which indicates the number of the stack to which
     *              the package has to be moved.

     * @throws Exception
     *       throws exception when the stack from which the package
     *              has to be moved is empty.
     */
    public static void movePackage(int from,int to) throws Exception
    {
        if(stacks[from].size()==0)
            System.out.print("\nThe stack "+from+" is empty.");

        else
        {
            stacks[to].push(stacks[from].pop());
            System.out.print("\nPackage moved.");
        }
    }

    /**
     * methods gets the details of a newly arrived package from the user
     *      and returns an Object of class package.
     *
     * @return
     *      returns an Object of class Package containing the details
     *          of newly arrived package
     */
    public static Package getNewPackage() {
        Package newPackage = new Package();
        double d;
        int flag = 0;

        System.out.print("\nEnter the details of the recpient:");
        System.out.print("\n\tRecipient name: ");
        newPackage.setRecipient(input.next());

            while (true) {
                System.out.print("\tPackage weight: ");
                try {
                    d = Double.parseDouble(input.next());
                    break; // will only get to here if input was a double
                } catch (NumberFormatException ignore) {
                    System.out.print("\nInvalid input");
                };
               }
        newPackage.setWeight(d);
        newPackage.setArrivalDate(getCurrentDate());
        return newPackage;

    }


    /**
     * displays and performs all the UI fucntions of the stack and returns an
     *      integer to indicate the success of the function.
     *
     * @param choice
     *      user entered choice to perform the preferred stack Sfunction.
     *
     * @return
     *      returns an integer to inform if the stack function has succesfully
     *              performed or not.
     *
     * @throws Exception
     *      throws exception when the function comes across a empty stack.
     */
    public static int UIFunctions(char choice) throws Exception {
        int i, j, flag = 0;

        if (choice == 'D' || choice == 'd') {
            i = 0;
            Package x = getNewPackage();
            i = nameCheck(x.getRecipient().charAt(0));

            if (!stacks[i].isFull()) {
                stacks[i].push(x);
                System.out.print("\n A "+ x.getWeight()+"lbs package " +
                        "is awaiting pickup by "+x.getRecipient());

            } else {
                j = i;
                do {
                    while (--i > -1) {
                        if (!stacks[i].isFull()) {
                            stacks[i].push(x);
                            flag = 1;
                            break;

                        }
                    }
                    if(flag != 1)  {
                        while (++j < 7) {
                            if (!stacks[j].isFull()) {
                                stacks[j].push(x);
                                flag = 1;
                                break;
                            }
                        }
                    }

                } while (flag == 0);


            }
            return 0;
        } else if (choice == 'G' || choice == 'g') {
            i = 0;

            System.out.print("\nEnter recipient name: ");
            String name = input.next();

            i = nameCheck(name.charAt(0));
            if (!stacks[i].isEmpty()) {
                if (bringUpPackage(name)) {
                    System.out.print("\n" + "Give " + name + "'s  package: \n" +
                            stacks[i].pop().toString());

                    for(j=0;j<stacks[5].size();j++)
                        movePackage(5,i);

                } else
                    System.out.print("\nSorry, there is no packages for " + name + ".");


            }
            return 0;


        } else if (choice == 'T' || choice == 't') {

            setCurrentDate(getCurrentDate() + 1);
            System.out.print("\tIt is now day " + getCurrentDate());

            PackageStack x = new PackageStack();
            PackageStack temp = new PackageStack();
            flag=0;

            if(getCurrentDate()>=5) {
                for (i = 0; i < 6; i++) {
                    int size = stacks[i].size();
                    for (j = 0; j < size; j++) {
                        if (getCurrentDate() - stacks[i].peek().getArrivalDate() == 5)
                        {
                            x.push(stacks[i].pop());
                            flag=1;
                        }

                        else
                            temp.push(stacks[i].pop());
                    }

                    for (j = 0; j < temp.size(); i++)
                        stacks[i].push(temp.pop());

                }
            }

            if(flag==1) {
                System.out.print("\nPackages arrived before five days " +
                        "are returned back to the sender");
                System.out.print("\nThe details of the sent packages are : \n"
                        + x.toString());
            }

            return 0;
        } else if (choice == 'P' || choice == 'p') {

            System.out.print("\nStack details are : ");
            System.out.print("\nDay: "+getCurrentDate());
            for (i = 0; i<6; i++)
                if (i == 5)
                    System.out.print("\n" + "Floor: " + stacks[i].toString());
                else
                    if(i==4)
                        System.out.print("\nStack" + (i + 1)+"(S-Z): " + stacks[i].toString());
                else
                    if(i==3)
                        System.out.print("\nStack" + (i + 1)+"(N-R): " + stacks[i].toString());
                else
                    if(i==2)
                        System.out.print("\nStack" + (i + 1)+"(K-M): " + stacks[i].toString());
                else
                    if(i==1)
                        System.out.print("\nStack" + (i + 1)+"(H-J): " + stacks[i].toString());
                else
                    if(i==0)
                        System.out.print("\nStack" + (i + 1)+"(A-G): " + stacks[i].toString());

            return 0;
        } else if (choice == 'M' || choice == 'm') {
            System.out.print("\nEnter the source stack(0 for floor): ");
            int from = input.nextInt()-1;

            System.out.print("\nEnter the destination stack: ");
            int to = input.nextInt()-1;

            if(from == -1)
                from = 5;

            if(to == -1)
                to = 5;

            movePackage(from, to);
            return 0;
        } else if (choice == 'f' || choice == 'F') {
            PackageStack temp = new PackageStack();

            flag=0;
            for (i = 0; i < 6; i++) {
                j = stacks[i].size();
                while (j > 0) {
                    if (nameCheck(stacks[i].peek().getRecipient().charAt(0)) != i)
                    {
                        movePackage(i, 5);
                        flag=1;
                    }

                    else
                        temp.push(stacks[i].pop());

                    j--;
                }

                while (temp.size()>0)
                    stacks[i].push(temp.pop());
            }

            if(flag==1)
                System.out.print("\nMisplaced packages are shifted to floor.");
            else
                System.out.print("\nThere are no misplaced packages.");

            return 0;
        } else if (choice == 'L' || choice == 'l') {
            int k = 0;
            System.out.print("\nEnter the recipient name: ");
            String name = input.next();

            PackageStack temp = new PackageStack();
            PackageStack x = new PackageStack();

            for (i = 0; i < 6; i++) {

                if (!stacks[i].isEmpty()) {
                    j = stacks[i].size();
                    while (j > 0) {

                        if (stacks[i].peek().getRecipient().equals(name))
                            x.push(stacks[i].peek());

                        temp.push(stacks[i].pop());
                        j--;
                        k++;

                    }

                    while (temp.size() > 0)
                        stacks[i].push(temp.pop());

                }

            }

            System.out.print("\n" + name + " has " + x.size() + " packages.");
            i=1;
            j = nameCheck(name.charAt(0));
            while(x.size()>0)
            {
                System.out.print("\nPackage "+i+" is in stack "+j+", " +
                        "it was delivered on day "+x.peek().getArrivalDate()
                            +" and weighs "+x.peek().getWeight()+"lbs.");
                x.pop();
                i++;
            }
            return 0;
        } else if(choice == 'Q' || choice == 'q') {
            System.out.print("\nThank you for using CSE 214 delivery services :)");
            return 1;
        }
        else if(choice == 'E' || choice == 'e') {

            if(stacks[5].size() == 0)
                System.out.print("\nThe floor is already empty.");
            else
            while (stacks[5].size() > 0)
                stacks[5].pop();

            System.out.print("\nThe Floor has been emptied. " +
                    "MR. Trash can is no longer hungry.");
            return 0;
        } /*else if(choice == 'S' || choice == 's'){

            flag=0;
            PackageStack t = new PackageStack();
            PackageStack rough = new PackageStack();
            Package x =  new Package();
            for(i=0;i<6;i++)
            {
                x = stacks[i].pop();

                for(int k=0;k<stacks[i].size();i++) {
                    String s2 = stacks[i].peek().getRecipient();
                    if (x.getRecipient().compareTo(s2) < 0) {
                        t.push(stacks[i].pop());

                        if(stacks[i].size()==1)
                            stacks[i].push(x);
                        flag=1;
                    }

                    else
                    {
                        rough.push(x);
                    }
                }


            }

        }*/

        else {

            System.out.print("\nInvalid option.");
            return 0;
        }
    }


    /**
     * Main method
     *     accepts the user choice and invokes the menu, UIfuntions
     *          method to perform the requested function.
     *
     * @param args
     *      arguments entered by user for the functioning of the program.
     *
     * @throws Exception
     *      throws exception when an exception is thrwon in
     *          UIfucntions method.
     */
    public static void main(String[] args) throws Exception
    {
        int flag =0;

        for (int i = 0; i < 6; i++)
            stacks[i] = new PackageStack(); //initialisation of the stacks.

        System.out.print("\nWelcome to the Irving Mailroom Manager." +
                "you can try to make it better, but the odds are stacked " +
                "against you");

        System.out.print("\n It is day: "+ getCurrentDate());
        do
        {
            menu();
            char choice = input.next().charAt(0);
            flag = UIFunctions(choice);

        }while(flag ==0);
    }
}
