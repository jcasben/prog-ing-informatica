import prettyprint.annotations.CustomPrettyPrint;
import prettyprint.annotations.HowToPrettyPrint;
import prettyprint.annotations.NotPrettyPrinted;
import prettyprint.annotations.PrettyPrintable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PrettyPrintable
class Student {
	
	String firstName;
	String lastName;
	
	@CustomPrettyPrint(type=1)
	String personalNumber;                      // "YYMMDD/NNNN" => "YYMMDD / NNNN"
	
	@NotPrettyPrinted
	String favouriteColor;
	
	@CustomPrettyPrint(type=2)
	List<Student> friends;
	
	@CustomPrettyPrint(type=2)
	List<Student> classMates;
	
	@CustomPrettyPrint(type=3)
	Map<Integer,Integer> numberOfHoursPerDay;
	
	@HowToPrettyPrint(type=1)
	void printPersonalNumber(String personalNumber)
	{
		// >>>>>>>> name of the field   (provided by the calling method)
        // "YYMMDD / NNNN"		
		// <<<<<name of the field         (provided by the calling method)
		
		String[] pn = personalNumber.split("/");
		System.out.println(pn[0] + " / " + pn[1]); 
	}
	
	@HowToPrettyPrint(type=2)
	void printList(List<Student> listOfStudents)
	{
		// >>>>>>>> name of the field   (provided by the calling method)
		//#########1
		// student printed
		//#########2
		// student printed
		//#########3
		// <<<<<name of the field         (provided by the calling method)
		int n = 1;
		for (Student s : listOfStudents)
		{
		   System.out.println("#########" + n++);
		   System.out.println(s.firstName + " " + s.lastName);
//		   PrettyPrinting.prettyPrint(s);
		}		   
	}

	@HowToPrettyPrint(type=3)
	void printHashMap(HashMap<Integer, Integer> schedule)
	{
		// >>>>>>>> name of the field   (provided by the calling method)
		// Monday: x hours
		// Tuesday: y hours
		// ...
		// Friday: z hours
		// <<<<<name of the field         (provided by the calling method)
		String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		for (int day = 0; day < 5; day++)
		{
		  System.out.println(dayNames[day] + ": " + schedule.get(day + 1) + " hours");
		}
	}
	
	static class StudentSchedule
	{
		Map<Integer, Integer> schedule;
		
		public StudentSchedule(int mon, int tue, int wed, int thu, int fri)
		{
			schedule = new HashMap<>();
			schedule.put(1, mon);
			schedule.put(2, tue);
			schedule.put(3, wed);
			schedule.put(4, thu);
			schedule.put(5, fri);
		}
		
		public Map<Integer,Integer> toHashMap()
		{
			return schedule;
		}
	}
	
	static List<Student> exampleData()
	{
		List<Student> data = new ArrayList<>();
		
		Student s1 = new Student();
		s1.firstName = "Jan";
		s1.lastName = "Vesely";
		s1.personalNumber = "891117/1234";
		s1.favouriteColor = "zelena";
		s1.friends = null;
		s1.classMates = null;
		s1.numberOfHoursPerDay = new StudentSchedule(0, 10, 10, 10, 0).toHashMap();
		
        Student s2 = new Student();
		s2.firstName = "Emil";
		s2.lastName = "Rozumny";
		s2.personalNumber = "900315/4321";
		s2.favouriteColor = "modra";
		s2.friends = null;
		s2.classMates = null;
		s2.numberOfHoursPerDay = new StudentSchedule(3, 2, 0, 4, 2).toHashMap();

        Student s3 = new Student();
		s3.firstName = "Andrea";
		s3.lastName = "Vyborna";
		s3.personalNumber = "030101/3434";
		s3.favouriteColor = "biela";
		s3.friends = null;
		s3.classMates = null;
		s3.numberOfHoursPerDay = new StudentSchedule(5, 3, 3, 2, 1).toHashMap();
		
		s1.classMates = new ArrayList<>();
		s1.classMates.add(s2);
		s1.classMates.add(s3);
		
		s2.classMates = new ArrayList<>();
		s2.classMates.add(s1);
		s2.classMates.add(s3);
		
		s3.classMates = new ArrayList<>();
		s3.classMates.add(s1);
		s3.classMates.add(s2);
		
		s1.friends = new ArrayList<>();
		s1.friends.add(s2);
		s1.friends.add(s3);

		s2.friends = new ArrayList<>();
		s2.friends.add(s3);

		s3.friends = new ArrayList<>();
		s3.friends.add(s2);		
		
		data.add(s1);
		data.add(s2);
		data.add(s3);
		return data;
	}
}