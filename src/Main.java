import java.io.*;
import java.util.*;


public class Main {

  public static void main(String[] args) throws IOException {
    Map<String, List<Student>> mapStudent = new HashMap<>();
    File inputFile = new File("res/input.txt");
    BufferedReader fr = new BufferedReader(new FileReader(inputFile));
    int groups = Integer.parseInt(fr.readLine());
    for (int groupId = 0; groupId < groups; ++groupId) {
      readGroup(fr, mapStudent);
    }
    fr.close();

    for (Map.Entry groupList : mapStudent.entrySet()) {
      String groupName = "" + groupList.getKey();
      System.out.println("\nGroup name: " + groupName);
      System.out.println("Students:");
      List<Student> students = new ArrayList<>(mapStudent.get(groupName));
      for (Student student : students) {
        System.out.printf("%s, %s%n", student.getName(),
            student.getEMail() == null ? "no eMail" : student.getEMail());
      }
    }
  }

  // - прочитать название группы
  // - прочитать количество студентов
  // - прочитать информацию о студентах - "имя" или "имя,e-mail" для каждого в отдельной строке
  private static void readGroup(BufferedReader fr, Map<String, List<Student>> mapStudent) throws IOException {
    String groupName = fr.readLine();
    if (!mapStudent.containsKey(groupName)) {
      mapStudent.put(groupName, new ArrayList<>());
    }
    int studentsNumber = Integer.parseInt(fr.readLine());
    for (int i = 0; i < studentsNumber; ++i) {
      String line = fr.readLine();
      Student student = Student.parseStudent(groupName, line);
      mapStudent.get(groupName).add(student);
    }
  }
}