/**
 * The SlideListNode class creates a SlideListNode
 * object that contains data and prev&next pointer
 * references that points the the previous and next
 * Slide object.
 *
 * @author Yanhui Chen
 *      e-mail: yanhui.chen@stonybrook.edu
 *
 * Data members: Slide data
 *               SlideListNode next
 *               SlideListNode prev
 */
public class SlideListNode {
    private Slide data;
    private SlideListNode next;
    private SlideListNode prev;

    /**
     * returns an instance of SlideListNode
     * object
     *
     * @param initData
     *      a Slide object that's not null
     *
     * @Precondtions
     *      iniData is not null
     *
     * @Postconditions
     *      SlideList Node has been initialized to wrap the
     *      indicated Slide and prev and next has been set
     *      to null
     *
     * @Throws IllegalArgumentException
     *      Thrown when initData is null
     *
     */
    public SlideListNode(Slide initData) {
        if (initData == null)
            throw new IllegalArgumentException();
        else {
            data = initData;
            next = null;
            prev = null;
        }
    }

    /**
     * returns the Slide's data
     *
     * @return
     *      the Slide's data
     */
    public Slide getData() {
        return data;
    }

    /**
     * set data mener variable with a reference to a new Slide
     *
     * @param newData
     *      reference to a new Slide object to update the
     *      data member variable newData can't be null
     *
     * @Preconditions
     *      newDta is not null
     *
     * @Throws IllegalArgumentException
     *      thrown when newData is null
     */
    public void setData(Slide newData) {
        if (newData == null) {
            throw new IllegalArgumentException();
        } else {
            data = newData;
        }
    }

    /**
     * return the next member variable of the
     * list node
     *
     * @return
     *      returns the Slide object after the
     *      cursor
     */
    public SlideListNode getNext() {
        return next;
    }

    /**
     * set next member variable with a
     * new SlideListNode reference
     *
     * @param newNext
     *      reference to a new SlideListNode object
     *      to update the next member variable
     */
    public void setNext(SlideListNode newNext) {
        next = newNext;
    }

    /**
     * returns the reference to the prev member
     * variable of the list node
     *
     * @return
     *      returns the reference of the prev
     *      member variable
     */
    public SlideListNode getPrev() {
        return prev;
    }

    /**
     * set the pre member variable with a new
     * SlideListNode reference
     *
     * @param newPrev
     *      reference to a new SlideListNode object tp update
     *      the prev member variable
     */
    public void setPrev(SlideListNode newPrev) {
        prev = newPrev;
    }
}
