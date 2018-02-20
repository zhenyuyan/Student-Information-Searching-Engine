import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Directory.
*
* @author Zhenyu Yang
*/
public class Directory {
  /** map of andrew id.
   *
   */
  private Map<String, Student> dicAndrew = new HashMap<String, Student>();
  /** map of first name.
   *
   */
  private Map<String, List<Student>> dicFname = new HashMap<String, List<Student>>();
  /** map of last name.
   *
   */
  private Map<String, List<Student>> dicLname = new HashMap<String, List<Student>>();

  /** Student.
   *
   * @param s is a Student
   */
  public void addStudent(Student s) {
    if (s == null) {
      throw new IllegalArgumentException("The student does not exist!");
    }
    boolean a = dicAndrew.containsKey(s.getAndrewId());
    if (a) {
      throw new IllegalArgumentException("Andrew ID already exists!");
    } else {
      Student news = new Student(s.getAndrewId());
      news.setFirstName(s.getFirstName());
      news.setLastName(s.getLastName());
      news.setPhoneNumber(s.getPhoneNumber());
      dicAndrew.put(news.getAndrewId(), news);
      if (!dicFname.containsKey(news.getFirstName())) {
        List<Student> l1 = new ArrayList<Student>();
        l1.add(news);
        dicFname.put(news.getFirstName(), l1);
      } else {
        dicFname.get(news.getFirstName()).add(news);
      }
      if (!dicLname.containsKey(news.getLastName())) {
        List<Student> l2 = new ArrayList<Student>();
        l2.add(news);
        dicLname.put(news.getLastName(), l2);
      } else {
        dicLname.get(news.getLastName()).add(news);
      }
    }
  }

  /** Andrew id.
   *
   * @param andrewId is andrew ID.
   */
  public void deleteStudent(String andrewId) {
    if (andrewId == null) {
      throw new IllegalArgumentException("Andrew ID is null!");
    }
    if (!dicAndrew.containsKey(andrewId)) {
      throw new IllegalArgumentException("We don't have that andrew ID");
    } else {
      Student stud = dicAndrew.get(andrewId);
      dicAndrew.remove(andrewId);
      dicFname.get(stud.getFirstName()).remove(stud);
      dicLname.get(stud.getLastName()).remove(stud);
    }
  }

  /** id.
   *
   * @param andrewId is the id.
   * @return A student with a certian id.
   */
  public Student searchByAndrewId(String andrewId) {
    if (andrewId == null) {
      throw new IllegalArgumentException("Andrew ID does not exist!");
    }
    if (!dicAndrew.containsKey(andrewId)) {
      return null;
    } else {
      String id1 = dicAndrew.get(andrewId).getAndrewId();
      Student sk = new Student(id1);
      sk.setFirstName(dicAndrew.get(andrewId).getFirstName());
      sk.setLastName(dicAndrew.get(andrewId).getLastName());
      sk.setPhoneNumber(dicAndrew.get(andrewId).getPhoneNumber());
      return sk;
    }
  }

  /** Search by firstname.
   *
   * @param firstName a name.
   * @return a list of students
   */
  public List<Student> searchByFirstName(String firstName) {
    if (firstName == null) {
      throw new IllegalArgumentException("First name does not exist!");
    }
    if (!dicFname.containsKey(firstName)) {
      List<Student> emptyList = new ArrayList<Student>();
      return emptyList;
    } else {
      List<Student> lm = new ArrayList<Student>();
      for (int i = 0; i < dicFname.get(firstName).size(); i++) {
        Student sk = new Student(dicFname.get(firstName).get(i).getAndrewId());
        sk.setFirstName(dicFname.get(firstName).get(i).getFirstName());
        sk.setLastName(dicFname.get(firstName).get(i).getLastName());
        sk.setPhoneNumber(dicFname.get(firstName).get(i).getPhoneNumber());
        lm.add(sk);
      }
      return lm;
    }
  }

  /** Search by last name.
   *
   * @param lastName is name.
   * @return a list of students
   */
  public List<Student> searchByLastName(String lastName) {
    if (lastName == null) {
      throw new IllegalArgumentException("Last name does not exist!");
    }
    if (!dicLname.containsKey(lastName)) {
      List<Student> emptyList2 = new ArrayList<Student>();
      return emptyList2;
    } else {
      List<Student> li = new ArrayList<Student>();
      for (int i = 0; i < dicLname.get(lastName).size(); i++) {
        //System.out.println(dicLname.get(lastName).get(i).getAndrewId());
        Student sj = new Student(dicLname.get(lastName).get(i).getAndrewId());
        sj.setFirstName(dicLname.get(lastName).get(i).getFirstName());
        sj.setLastName(dicLname.get(lastName).get(i).getLastName());
        sj.setPhoneNumber(dicLname.get(lastName).get(i).getPhoneNumber());
        li.add(sj);
      }
      return li;
    }
  }

  /** Size.
   *
   * @return size of the map
   */
  public int size() {
    int size = dicAndrew.size();
    return size;
  }
}
