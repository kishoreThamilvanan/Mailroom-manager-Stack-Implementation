/**
 * Class Package contains the details of an individuval packages.
 *
 * @author Kishore Thamilvanan
 * email : kishore.thamilvanan@stonybrook.edu
 * SBU ID: 111373510
 */
public class Package {


    private String recipient;// recipient name of the package.
    private int arrivalDate;// arrival date of the package.
    private double weight;// weight of the arrived package

    /**
     * Default constructor which initializes the new package with
     *      default values.
     */
    public Package()

    {
        recipient = "";
        arrivalDate = 0;
        weight = 0;

    }

    /**
     * Paramterised constructor which assigns the user specified
     *      values to the new package.
     *
     * @param recipient
     *      String parameter which holds the name of the recipient.
     *
     * @param arrivaldate
     *      Integer parameter which holds the arrival date of the package.
     *
     * @param weight
     *      Integer parameter which holds the weight of the package.
     */
    public Package(String recipient, int arrivaldate, double weight)
    {
        this.recipient = recipient;
        this.arrivalDate = arrivaldate;
        this.weight = weight;

        System.out.print("This Object has been initialized with " +
                "specified recipient, arrival date and weight");
    }

    /**
     * method returns the recipient name.
     *
     * @return
     *      returns the recipient name.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * method sets the recieved String parameter to the
     *      recipient name of the package.
     *
     * @param recipient
     *      String parameter contains the user entered name of the recipient.
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * method returns the arrival date of the package.
     *
     * @return
     *      returns the arrivale date.
     */
    public int getArrivalDate() {
        return arrivalDate;
    }

    /**
     * method sets the user entered arrival date.
     *
     * @param Date
     *      parameter contains the user entered arrival date.
     */
    public void setArrivalDate(int Date) {
        arrivalDate = Date;
    }

    /**
     * method returns the weight of the package.
     *
     * @return
     *      returns the weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * method sets the package with the user entered package weight.
     *
     * @param weight
     *      parameter contains the user entered package weight.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * method returns the details of the package as a String.
     *
     * @return
     *      returns the details of a package as a String
     */
    @Override
    public String toString() {
        return  "["+this.recipient+" "+this.arrivalDate+"]";
    }
}
