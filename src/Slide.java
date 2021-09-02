/**
 * The Slide Class creates a Slide object with
 * title, bullets, and duration of the Slide.
 *
 * @author Yanhui Chen
 *      e-mail: yanhui.chen@stonybrook.edu
 * Data members: String title
 *               String[] bullets
 *               double duration
 */
public class Slide {
    public static final int MAX_BULLETS = 5;
    private String title;
    private String[] bullets;
    private double duration;

    /**
     * return an instance of Slide
     *
     * @postconditions
     *      Slide is initialized to an
     *      empty Slide
     */
    public Slide() {
        bullets = new String[MAX_BULLETS];
        title = null;
        duration = 0;
    }

    /**
     * returns title of the Slide
     *
     * @return
     *      the title of the Slide object
     */
    public String getTitle() {
        return title;
    }

    /**
     * set title of the instance
     *
     * @param newTitle
     *      title of the instance
     * @preconditions
     *      title is not null
     *
     * @Throws IllegalArgumentException
     *      throw if the title is null
     */
    public void setTitle(String newTitle) {
        if (newTitle == null) {
            throw new IllegalArgumentException("Title cannot be" +
                    "null.");
        } else
            title = newTitle;
    }

    /**
     * return duration of the instance
     *
     * @return
     *      duration of the Slide instance
     */
    public double getDuration() {
        return duration;
    }

    /**
     * set duration for the instance
     *
     * @param newDuration
     *      duration of the slide
     *
     * @Preconditions
     *      newDuration is greater than 0
     *
     * @Throws IllegalArgumentException
     *      thrown when newDuration is less than or equal to 0
     */
    public void setDuration(double newDuration){
        if (newDuration <= 0) {
            throw new IllegalArgumentException("duration should be" +
                    "greater than 0.");
        } else
            duration = newDuration;
    }

    /**
     * set duration to -1, representing that
     * it is deleted
     *
     * @Postcondition
     *      duration set to -1
     */
    public void deleteDuration(){
        duration = 0;
    }
    /**
     * returns the number of bullets in the Slide instance
     *
     * @return
     *      number of bullets in the Slide instance
     */
    public int getNumBullets() {
        int count = 0;
        for (int i = 0; i < MAX_BULLETS; i++) {
            if (bullets[i] != null)
                count++;
        }
        return count;
    }

    /**
     * Returns bullet information in the specific position
     *
     * @param i
     *      position of bullet
     *
     * @precondition
     *      index to retrieve from the array. This value
     *      must be between 1 and MAX_BULLETS, inclusive
     *
     * @return
     *      Returns information that's in the specific position
     *      of the bullet
     *
     * @Throws IllegalArgumentException
     *      Thrown if i is not in valid range
     */
    public String getBullet(int i) {
        if (i >= 1 && i <= MAX_BULLETS) {
            return bullets[i-1];
        } else
            throw new IllegalArgumentException("Not in valid range.");
    }

    /**
     * change bullet at the specific position of the new bullet
     *
     * @param bullet
     *      the new information replace the
     *      old String in the specific position
     * @param i
     *      position of the bullet
     *
     * @precondition
     *      i is less than i and larger than MAX_BULLETS inclusive
     *
     * @Postcondition
     *      The bullet point at position has been updated to
     *      the String bullet and there is no holes in the bullet
     *      array
     *
     * @Throws IllegalArgumentException
     */
    public void setBullet(String bullet, int i) {
        if (i >= 1 && i <= this.getNumBullets() + 1) {
            int index = i-1;
            if (bullet == null) {
                for (int x = index; x < this.getNumBullets(); x++ ) {
                    if (x == this.getNumBullets() - 1)
                        break;
                    bullets[x] = bullets[x+1];
                }
                bullets[this.getNumBullets() - 1] = null;

            } else {
                bullets[index] = bullet;
            }

        } else
            throw new IllegalArgumentException("Not in valid range.");
    }

    /**
     * edit String in bullet at position i
     *
     * @param i
     *      position of the bullet
     * @param s
     *      new String to replace the String originally
     *      at the bullet position
     * @Precondition
     *      i is in between 1 and the number of bullets, inclusive, in the
     *      Slide instance
     * @Postcondition
     *      the String at ith position of bullet is replaced with
     *      the new String s
     * @Throws IllegalArgumentException
     *      thrown when i is not within 1 and the number of
     *      bullets, inclusive.
     */
    public void editBullet(int i, String s) {
        if (i >= 1 && i <= this.getNumBullets()) {
            int index = i - 1;
            bullets[index] = s;
        } else
            throw new IllegalArgumentException("Not in valid range.");
    }

    /**
     * deletes the bullet at index i and
     * leaves no holes, move bullets at the position
     * that's greater than i one position back
     *
     * @param i
     *      position of the bullet
     * @Precondition
     *      i is in between 1 and the number of bullets, inclusive, in
     *      the Slide instance
     * @Postcondition
     *      the bullet at position i is deleted, and there
     *      is no holes in the bullet array
     * @Throws IllegalArgumentException
     *      thrown is i is not in valid range
     */
    public void deleteBullet(int i) {
        if (i >= 1 && i <= this.getNumBullets()) {
            int index = i - 1;
            for (int x = index; x < this.getNumBullets(); x++ ) {
                if (x == this.getNumBullets() - 1)
                    break;
                bullets[x] = bullets[x+1];
            }
            bullets[getNumBullets()-1] = null;
        } else
            throw new IllegalArgumentException("Not in valid range.");
    }

    /**
     * Creates a String representation of Slide class
     *
     * @return
     *      String that represents the Slide class
     */
    public String toString() {
        String bullet= "";
        for (int i = 1; i <= this.getNumBullets(); i++) {
            bullet += i + ". " + getBullet(i) + "\n";
        }
        String s = "==============================================\n"+
                "Title: "+ this.getTitle() +"\n"+
                "==============================================\n" +
                "Duration: " + this.getDuration()  +"minute(s)\n" +
                "==============================================\n" +
                bullet +
                "==============================================\n";

        return s;
    }

    /**
     * prints the Slide in a table format
     *
     * @Preconditon
     *      the Slide instance is initialized
     *
     * @Postcondition
     *      Prints a Slide instance that shows the title,
     *      bullets, and duration if the Slide object
     */
    public void printSlide() {
        System.out.print(toString());
    }
}
