/**
 * The SlideList Object creates a SlideList object
 * that stores head, tail, and cursor of the
 * SlideListNode object.
 *
 * @author Yanhui Chen
 *      e-mail: yanhui.chen@stonybrook.edu
 *
 * Data members: SlideListNode head
 *               SlideListNode tail
 *               SlideListNode cursor
 *               int numSlides
 */
public class SlideList {
    private SlideListNode head;
    private SlideListNode tail;
    private SlideListNode cursor;
    private int numSlides;

    /**
     * returns an instance of SlideList object
     *
     * @Postcondition
     *      This SlideList has been initialized with
     *      head, tail, and cursor all set to null
     *
     */
    public SlideList() {
        head = null;
        tail = null;
        cursor = null;
        numSlides = 0;
    }

    /**
     * returns the total number of Slides
     * in the slideshow
     *
     * @return
     *      returns the amount of Slide objects in
     *      the slideshow
     *
     */
    public int size() {
        return numSlides;
    }

    /**
     * returns the total duration of the slideshow
     *
     * @return
     *      returns the duration sum of all
     *      Slide objects
     */
    public double duration() {
        double sum = 0;
        SlideListNode ptr = head;
        if (ptr != null) {
            while (ptr != null) {
                sum += ptr.getData().getDuration();
                ptr = ptr.getNext();
            }
        }
        return sum;
    }

    /**
     * returns the total number of bullet
     * points in the slideshow
     *
     * @return
     *      returns the total number of bullet
     *      points in the slideshow
     */
    public int numBullets() {
        int sum = 0;
        SlideListNode ptr = head;
        if (ptr != null) {
            while (ptr != null) {
                sum += ptr.getData().getNumBullets();
                ptr = ptr.getNext();
            }
        }
        return sum;
    }

    /**
     * returns the Slide object referenced by cursor
     * @return
     *      the slide referenced by cursor
     */
    public Slide getCursorSlide() {
        return cursor.getData();
    }

    /**
     * sets cursor to head of the list
     *
     * @Postconditions
     *      cursor set to head, if head is null
     *      cursor will be set to null
     */
    public void resetCursorToHead() {
        cursor = head;
    }

    /**
     * move cursor to the next SlideListNode in the list
     *
     * @throws EndOfListException
     *      thrown when cursor is at the tail of the list
     */
    public void cursorForward() throws EndOfListException {
        if (cursor == tail) {
            throw new EndOfListException("Cursor at tail cannot" +
                    " move forward.\n");
        } else {
            cursor = cursor.getNext();
        }
    }

    /**
     * mover cursor to the previous SlideListNode in the list
     *
     * @throws EndOfListException
     *      thrown when cursor is at the head of the list
     */
    public void cursorBackward() throws EndOfListException {
        if (cursor == head) {
            throw new EndOfListException("Cursor at head cannot " +
                    "move backward.\n");
        } else {
            cursor = cursor.getPrev();
        }
    }

    /**
     * inserts the indicated Slide before cursor
     *
     * @param newSlide
     *      the Slide object to be wrapped and inserted
     *      into the list before cursor
     *
     * @Precondtions
     *      newSlide is not null
     *
     * @Postconditions
     *      newSlide has been wrapped in a new SlideListNode object.
     *      If cursor was previously null, set the created
     *      SlideListNode has been set to head, tail, and cursor.
     *      If cursor was previously not null, the newly created
     *      SlideListNode has been inserted before the cursor.
     *      The cursor will also references the newly created
     *      SlideListNode
     *
     * @Throws IllegalArgumentException
     *      Thrown when newSlide is null
     */
    public void insertBeforeCursor(Slide newSlide) {
        if (newSlide == null) {
            throw new IllegalArgumentException("slide cannot be null\n");
        } else {
            SlideListNode addedSlide = new SlideListNode(newSlide);
            if (cursor == head) {
                addedSlide.setNext(cursor);
                cursor.setPrev(addedSlide);
                numSlides++;
                cursor = addedSlide;
                head = addedSlide;
            } else if (cursor == null) {
                cursor = addedSlide;
                head = addedSlide;
                tail = addedSlide;
                numSlides++;
            } else {
                addedSlide.setNext(cursor);
                addedSlide.setPrev(cursor.getPrev());
                addedSlide.getPrev().setNext(addedSlide);
                cursor.setPrev(addedSlide);
                numSlides++;
                cursor = addedSlide;
            }
        }
    }

    /**
     * inserts the indicated Slide after thr tail of list
     *
     * @param newSlide
     *      The Slide object to be wrapped and inserted into
     *      the list after the tail of the list
     *
     * @Preconditions
     *      newSlide is not null
     *
     * @Postconditions
     *      newSlide has been wrapped in a new SlideListNode object.
     *      If tail was previously not null, the newly created
     *      SlideListNode has been inserted into the list after
     *      the tail. If tail was previously null, the newly created
     *      SlideListNode has been set as the new head and tail of
     *      the list. The tail now references the newly created
     *      SlideListNode.
     *
     * @Throws IllegalArgumentException
     *      Thrown when newSlide is null
     */
    public void appendToTail(Slide newSlide) {
        if (newSlide == null) {
            throw new IllegalArgumentException("slide cannot be null\n");
        }
        SlideListNode addedSlide = new SlideListNode(newSlide);
        if (tail == null) {
            cursor = addedSlide;
            head = addedSlide;
            tail = addedSlide;
        } else {
            tail.setNext(addedSlide);
            addedSlide.setPrev(tail);
        }
        tail = addedSlide;
        numSlides++;
    }

    /**
     * removes the SlideListNode referenced by cursor and
     * returns the Slide inside
     *
     * @return
     *      The Slide object that's removed
     *
     * @Precondition
     *      cursor is not null
     *
     * @Postcondition
     *      The SlideListNode referenced by cursor has been
     *      removed from the list. All other SlideListNodes
     *      in the list exist in the same order as before.
     *      The cursor now references the previous SlideListNode.
     *
     * @throws EndOfListException
     *      Thrown if cursor is null
     *
     */
    public Slide removeCursor() throws EndOfListException {
        if (cursor == null) {
            throw new EndOfListException("cursor is pointed at null");
        } else {
            Slide temp = cursor.getData();
            if (cursor == tail && cursor != head) {
                tail = cursor.getPrev();
                cursor.getPrev().setNext(null);
                cursor = tail;
            } else if (cursor == head && cursor != tail) {
                head = cursor.getNext();
                head.setPrev(null);
                cursor = head;
            } else if (cursor == head && cursor == tail) {
                cursor = null;
                head = null;
                tail = null;
            } else {
                cursor.getNext().setPrev(cursor.getPrev());
                cursor.getPrev().setNext(cursor.getNext());
                cursor = cursor.getPrev();
            }
            numSlides--;
            return temp;
        }
    }

    /**
     * returns a String representation of
     * SlideList object
     *
     * @return
     *      a String representation of
     *      SlideList object
     */
    public String toString() {
        String info = "";
        int slideNum = 1;
        String title;
        double duration;
        int bullets;
        SlideListNode ptr = head;
        while (ptr != null) {
            title = ptr.getData().getTitle();
            duration = ptr.getData().getDuration();
            bullets = ptr.getData().getNumBullets();
            if (ptr == cursor) {
                info = info + String.format("-> "+"%-8d%-14s%-12.1f%-7d\n",
                        slideNum,title,duration,bullets);

            } else {
                info = info + String.format("   "+"%-8d%-14s%-12.1f%-7d\n",
                        slideNum,title,duration,bullets);
            }
            slideNum++;
            ptr = ptr.getNext();
        }
        String s ="Slideshow Summary: \n" +
                "==============================================\n" +
                "  Slide    Title         Duration   Bullets\n" +
                "----------------------------------------------\n" +
                info +
                "==============================================\n" +
                "Total: " + size() + " slide(s), " + duration() +
                " minute(s), "+ numBullets() + " bullet(s)\n" +
                "==============================================\n";

        return s;
    }

    /**
     * Print the slides' summary in a table format
     *
     * @Preconditions
     *      The SlideList is initialized
     *
     * @PostConditions
     *      It prints the Slides' summary of the slide show
     *      showing their order, title, duration, and bullets
     *      in a table format, the total number of slides and
     *      total duration, and total number of bullets is also
     *      shown at the bottom.
     */
    public void printSlidesSummary() {
        System.out.printf(toString());
    }
}
