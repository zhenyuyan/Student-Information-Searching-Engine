/** Andrew ID :zhenyuy1.
 *
 * @author Zhenyu Yang
 *
 */
public class Student {
  /** id.
 *
 */
  private String id;
  /** first name.
 *
 */
  private String fname;
  /** last name.
 *
 */
  private String lname;
  /** phone number.
 *
 */
  private String phoneNum;

  /** Student constructor.
   *
   * @param andrewId is id.
   */
  public Student(String andrewId) {
    id = andrewId;
  }

  /** Return id.
 *
 * @return id.
 */
  public String getAndrewId() {
    return id;
  }

  /**Return first name.
 *
 * @return name
 */
  public String getFirstName() {
    return fname;
  }

  /**Return Last Name.
   *
   * @return name
   */
  public String getLastName() {
    return lname;
  }

  /** Return.
   *
   * @return the phone number
   */
  public String getPhoneNumber() {
    return phoneNum;
  }

  /** s is firstname.
   *
   * @param s is firstname.
   */
  public void setFirstName(String s) {
    fname = s;
  }

  /** Set last name.
   *
   * @param s is last name.
   */
  public void setLastName(String s) {
    lname = s;
  }

  /**Phone Number.
   *
   * @param s is phone number
   */
  public void setPhoneNumber(String s) {
    phoneNum = s;
  }

  /** Results.
   * @return a string of results.
   */
  public String toString() {
    String s;
    s = fname + " " +  lname + " " + "(Andrew ID: ";
    s = s + id + ", " + "Phone Number: " + phoneNum + ")";
    return s;
  }
}
