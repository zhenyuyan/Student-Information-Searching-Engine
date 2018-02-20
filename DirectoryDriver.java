/**
 * @author Zhenyu Yang
 */
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/** Andrew id: zhenyuy1.
 *
 * @author yangzhenyu
 *
 */
public class DirectoryDriver {
  /**
    * add Button.
    */
  private JButton addButton;
  /** Delete Button.
   *  To delete a student.
   */
  private JButton deleteButton;
  /** Search Button.
   *  To search a student by id.
   */
  private JButton searchidButton;
  /** Search Button.
   *  To search a student by first name.
   */
  private JButton searchFnameBut;
  /** Search Button.
   *  To search a student by last name.
   */
  private JButton searchLnameBut;
  /** Text Area
   *  To create a text Area.
   */
  private JTextArea textArea;
  /** CSVReader class.
 *
 * @author yangzhenyu
 *
 */
  public class CSVReader extends BufferedReader {
    /** CSVReader.
      *
      * @param in is a reader.
      */
    public CSVReader(Reader in) {
        super(in);
    }
    /**
     *
     * @return a Slice of String
     * @throws IOException is error.
     */
    public String[] readCSVLine() throws IOException {
      String line = super.readLine();
      if (line == null) {
        return null;
      }
      int commaCount = 0;
      for (int i = 0; i < line.length(); i++) {
        if (line.charAt(i) == ',') {
          commaCount = commaCount + 1;
        }
      }
      String[] values = new String[commaCount + 1];
      int beginIndex = 0;
      for (int i = 0; i < commaCount; i++) {
        int endIndex = line.indexOf(',', beginIndex);
        if (line.charAt(beginIndex) == '"' && line.charAt(endIndex - 1) == '"') {
          values[i] = line.substring(beginIndex + 1, endIndex - 1);
        } else {
          values[i] = line.substring(beginIndex, endIndex);
        }
        beginIndex = endIndex + 1;
      }
      if (line.charAt(beginIndex) == '"' && line.charAt(line.length() - 1) == '"') {
        values[commaCount] = line.substring(beginIndex + 1, line.length() - 1);
      } else {
        values[commaCount] = line.substring(beginIndex, line.length());
      }
      return values;
    }

    /** This is a set directory constructor.
      *
      * @param fr is a reader
      * @return a directory
      * @throws IOException error happens
      */
    public Directory setDirectory(FileReader fr) throws IOException {
      CSVReader c = new CSVReader(fr);
      Directory d = new Directory();
      int lineNum = 0;
      boolean eof = false;
      while (!eof) {
        String[] values = c.readCSVLine();
        if (values == null) {
          eof = true;
        } else {
          lineNum = lineNum + 1;
          Student s = new Student(values[2]);
          for (int i = 0; i < values.length; i++) {
            if (i == 0 && lineNum != 1) {
              s.setFirstName(values[i]);
            }
            if (i == 1 && lineNum != 1) {
              s.setLastName(values[i]);
            }
            if (i == 3 && lineNum != 1) {
              s.setPhoneNumber(values[i]);
            }
          }
          d.addStudent(s);
        }
      }
      c.close();
      return d;
    }
  }

  /** This is the class of constructing GUI.
    *
    * @author yangzhenyu
    *
    */
  public class SetGUI extends JFrame {
    /** Set GUI.
      *
      * @param dir is the directory.
      */
    public SetGUI(Directory dir) {
      //-----------------Add a Student-----------------------------------
      setTitle("Student Information System");
      setSize(1000, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Font font = new Font(Font.SERIF, Font.BOLD, 20);
      JPanel line0 = new JPanel();
      line0.setBorder(BorderFactory.createTitledBorder("Add a New Student"));
      line0.setPreferredSize(new Dimension(950, 80));
      // JLabel label = new JLabel("Add a student:");
      //label.setFont(font);
      //line0.add(label);
      addButton = new JButton("Add");
      addButton.setVerticalTextPosition(SwingConstants.BOTTOM);
      addButton.setHorizontalTextPosition(SwingConstants.CENTER);
      // anonymous class that implements ActionListener.
      JLabel label0 = new JLabel("First Name:");
      line0.add(label0);
      JTextField textfield00 = new JTextField(8);
      line0.add(textfield00);
      JLabel label1 = new JLabel("Last Name:");
      line0.add(label1);
      // Create labels and text area for the "Add" button.
      JTextField textfield01 = new JTextField(8);
      line0.add(textfield01);
      JLabel label2 = new JLabel("Andrew ID:");
      line0.add(label2);
      JTextField textfield02 = new JTextField(8);
      line0.add(textfield02);
      JLabel label3 = new JLabel("Phone Number:");
      line0.add(label3);
      JTextField textfield03 = new JTextField(8);
      line0.add(textfield03);
      addButton.addActionListener(new ActionListener() {
        @Override
      public void actionPerformed(ActionEvent e) {
          String fname = textfield00.getText().replace(" ", "");
          String lname = textfield01.getText().replace(" ", "");
          String id = textfield02.getText().replace(" ", "");
          String phonenum = textfield03.getText().replace(" ", "");
          if (fname.trim().isEmpty() || lname.trim().isEmpty() || id.trim().isEmpty()) {
            textArea.append("First name or last name or ID missing !\n");
          } else {
            try {
              Student s = new Student(id);
              s.setFirstName(fname);
              s.setLastName(lname);
              s.setPhoneNumber(phonenum);
              dir.addStudent(s);
              textfield00.setText("");
              textfield01.setText("");
              textfield02.setText("");
              textfield03.setText("");
              textArea.append("New entry: " + fname + " " + lname + " ");
              textArea.append(id + " " + phonenum + " is Added! \n");
            } catch (IllegalArgumentException e1) {
              textArea.append("IllegalArgumentException: Andrew id exists! \n");
            }
          }
          }
      });
      line0.add(addButton);
      // ---------------------------------------------------------------------
      // The following code implements delete button
      JPanel line1 = new JPanel();
      //JLabel label5 = new JLabel("Delete a student:");
      //label5.setFont(font);
      line1.setBorder(BorderFactory.createTitledBorder("Delete a student"));
      line1.setPreferredSize(new Dimension(900, 80));
      //line1.add(label5);
      JLabel label6 = new JLabel("Andrew ID:");
      line1.add(label6);
      JTextField textfield10 = new JTextField(8);
      line1.add(textfield10);
      deleteButton = new JButton("Delete");
      // line0.add(deleteButton);
      deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
      deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
      deleteButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          String idname = textfield10.getText().replace(" ", "");
          try {
            Student s = dir.searchByAndrewId(idname);
            textArea.append(String.valueOf(s.toString() + " is deleted!" + "\n"));
            dir.deleteStudent(idname);
            textfield10.setText("");
          } catch (IllegalArgumentException e) {
            textArea.append(idname + " does not exist! \n");
          } catch (NullPointerException e) {
            textArea.append(idname + " no such a id! \n");
          }
        }
      });
      line1.add(deleteButton);
      //-----------------------------------------------------------------
      // The following code implements search students
      //JLabel label7 = new JLabel("Search Students :");
      //label7.setFont(font);
      JPanel line2 = new JPanel();
      line2.setBorder(BorderFactory.createTitledBorder("Search Students"));
      line2.setPreferredSize(new Dimension(900, 80));
      //line2.add(label7);
      JTextField textfield20 = new JTextField(8);
      textfield20.requestFocusInWindow();
      addWindowListener(new WindowAdapter() {
        public void windowOpened(WindowEvent e) {
          textfield20.requestFocus();
        }
      });
      JLabel label8 = new JLabel("Search Key:");
      line2.add(label8);
      searchidButton = new JButton("By Andrew ID");
      searchFnameBut = new JButton("By First Name");
      searchLnameBut = new JButton("By Last Name");
      line2.add(textfield20);
      line2.add(searchidButton);
      line2.add(searchFnameBut);
      line2.add(searchLnameBut);
      searchidButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          String id = textfield20.getText().replace(" ", "");
          try {
            Student s = dir.searchByAndrewId(id);
            textArea.append("Here is the Student: " + s.toString() + "\n");
            textfield20.setText("");
          } catch (IllegalArgumentException e2) {
            textArea.append("IllegalArgumentException: No such ID  " + id + "\n");
          } catch (NullPointerException e2) {
            textArea.append("NullPointerException: No such ID  " + id + "\n");
          }
        }
      });
      textfield20.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent key) {
          if (key.getKeyChar() == KeyEvent.VK_ENTER) {
            String id = textfield20.getText().replace(" ", "");
            try {
              Student s = dir.searchByAndrewId(id);
              textArea.append("Here is the Student: " + s.toString() + "\n");
              textfield20.setText("");
            } catch (IllegalArgumentException e2) {
              textArea.append("IllegalArgumentException: No such ID  " + id + "\n");
            } catch (NullPointerException e2) {
              textArea.append("NullPointerException: No such ID  " + id + "\n");
            }
          }
        }
      });
      searchFnameBut.addActionListener(new ActionListener() {
        /** Action Performed.
         *
         */
        public void actionPerformed(ActionEvent arg0) {
          String fname = textfield20.getText().replace(" ", "");
          try {
            List<Student> l = dir.searchByFirstName(fname);
            if (l.size() == 0) {
              textArea.append("No such first name " + fname + "!\n");
            } else {
              textArea.append(l.toString() + "\n");
              textfield20.setText("");
            }
          } catch (IllegalArgumentException e3) {
              textArea.append("IllegalArgumentException: No such first name " + fname + "\n");
          } catch (NullPointerException e3) {
              textArea.append("NullPointerException: No such first name " + fname + "\n");
          }
        }
      });
      searchLnameBut.addActionListener(new ActionListener() {
        /** Action Performed.
         *
         */
        public void actionPerformed(ActionEvent arg0) {
          String lname = textfield20.getText().replace(" ", "");
            try {
              List<Student> l = dir.searchByLastName(lname);
              if (l.size() == 0) {
                textArea.append("No such last name " + lname + "! \n");
              } else {
                textArea.append(l.toString() + "\n");
                textfield20.setText("");
              }
            } catch (IllegalArgumentException e3) {
              textArea.append("IllegalArgumentException: No such last name " + lname + "\n");
            } catch (NullPointerException e3) {
              textArea.append("NullPointerException: No such last name " + lname + "\n");
            }
          }
      });
      //_________________________________________________________________
      // The following code implements textarea
      JPanel line3 = new JPanel();
      line3.setBorder(BorderFactory.createTitledBorder("Results"));
      line3.setPreferredSize(new Dimension(900, 200));
      textArea = new JTextArea(10, 70);
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      textArea.setEditable(false);
      line3.add(textArea);
      JScrollPane scroller = new JScrollPane(textArea);
      line3.add(scroller);
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      JPanel panel = new JPanel();
      panel.add(line0);
      panel.add(line1);
      panel.add(line2);
      panel.add(line3);
      add(panel);
      setContentPane(panel);
      setVisible(true);
    }
  }

  /** Main function.
 *
 * @param args parameters
 * @throws IOException error.
 */
  public static void main(String[] args) throws IOException {
    FileReader fr = new FileReader(args[0]);
    DirectoryDriver d = new DirectoryDriver();
    DirectoryDriver.CSVReader c = d.new CSVReader(fr);
    Directory dir = c.setDirectory(fr);
    d.new SetGUI(dir);
  }
}
