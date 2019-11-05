package main.server.logic.handler;

import java.util.ArrayList;
import java.util.List;

import main.server.logic.handler.model.Output;
import main.server.logic.handler.model.ServerOutput;
import main.server.logic.model.Course;
import main.server.logic.model.Student;
import main.server.logic.model.University;
import main.utilities.Config;

public class InputHandler {

	public static final int WAITING = 0;
	public static final int FINISHWAITING = 1;
	public static final int CLERK = 2;
	public static final int CLERKLOGIN = 3;
	public static final int STUDENT = 4;
	public static final int STUDENTLOGIN = 5;
	public static final int CREATECOURSE = 6;
	public static final int CREATESTUDENT = 7;
	public static final int CANCELCOURSE = 8;
	public static final int DELETECOURSE = 9;
	public static final int DELETESTUDENT = 10;
	public static final int SELECTCOURSE = 11;
	public static final int REGISTERFORCOURSE = 12;
	public static final int DROPCOURSE = 13;
	public static final int DEREGISTERCOURSE = 14;
	private int ccount = 0;
	private int cocount = 0;
	private int secount=0;
	private int scount = 0;
	private int sccount=0;
	private int rcount = 0;
	boolean dDone=false;
	// private boolean switchAcc=false;
	OutputHandler outputHandler = new OutputHandler();

	public ServerOutput processInput(String input, int state) {
		String output = "";
		Output o = new Output("", 0);
		ServerOutput oo = new ServerOutput(output, o.getState());
		if (state == WAITING) {
			output = "Who Are you? Clerk or Student?";

			state = FINISHWAITING;
			oo.setOutput(output);
			oo.setState(state);
		} else if (state == FINISHWAITING) {
			if (input.equalsIgnoreCase("clerk")) {
				if (Config.testMode == true) {
					output += "Clerk";
				}
				output += "\nPlease Input The Password:";
				if (Config.testMode == true) {
					output += "\nadmin";
				}
				state = CLERKLOGIN;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("student")) {
				if (Config.testMode == true) {
					output += "Student";
				}
				output += "\nPlease Input Student number and Name: 'student number, name'";
				if (Config.testMode == true) {
					if (scount == 0) {
						output += "\n111111111, Joe";
					}
					if (scount == 1) {
						output += "\n222222222, Will";
					}
					if (scount == 2) {
						output += "\n333333333, Harry";
					}
					if (scount == 3) {
						output += "\n444444444, Sarah";
					}
					scount++;
				}
				state = STUDENTLOGIN;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				output = "Who Are you? Clerk or Student?";
				if (Config.testMode == true) {
					output = "Who Are you? Clerk or Student?" + "\nClerk";
				}
				state = FINISHWAITING;
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == CLERKLOGIN) {
			o = outputHandler.clerkLogin(input);
			output = o.getOutput();
			state = o.getState();
			oo.setOutput(output);
			oo.setState(state);
		} else if (state == STUDENTLOGIN) {
			o = outputHandler.studentLogin(input);
			output = o.getOutput();
			state = o.getState();
			oo.setOutput(output);
			oo.setState(state);
		} else if (state == CLERK) {
			if (input.equalsIgnoreCase("create course")) {
				output += "Create Course \n";
				output += "Please Input Course Info: 'title, course code, capsize, enforce prereqs(y/n), number of midterms, number of assignments, has a final(y/n), is project course(y/n)'";
				if (Config.testMode == true) {
					if (ccount == 0) {
						output += "\nTesting, 123456, 2, y, 0, 4, n, n";
					}
					if (ccount == 1) {
						output += "\n3004, 456789, 1, y, 1, 2, y, y";
					}
					if (ccount == 2) {
						output += "\n4109, 234567, 3, y, 1, 4, y, y";
					}
					if (ccount == 3) {
						output += "\n2404, 654321, 2, y, 2, 3, y, y";
					}
					if (ccount == 4) {
						output += "\n2402, 345678, 1, y, 2, 1, y, y";
					}
					if (ccount == 5) {
						output += "\n3007, 987654, 1, y, 2, 2, y, n";
					}
					ccount++;
				}

				state = CREATECOURSE;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("create student")) {
				output = "Please Input Student Info: 'student number, name, is fulltime(y/n)'";
				state = CREATESTUDENT;
				if (Config.testMode == true && sccount==0) {
					output = "Please Input Student Info: 'student number, name, is fulltime(y/n)'"
							+ "\n100996742, Amr, y";
				}
				if (Config.testMode == true && sccount==1) {
					output = "Please Input Student Info: 'student number, name, is fulltime(y/n)'"
							+ "\n111111111, Joe, y";
				}
				if (Config.testMode == true && sccount==2) {
					output = "Please Input Student Info: 'student number, name, is fulltime(y/n)'"
							+ "\n222222222, Will, y";
				}
				if (Config.testMode == true && sccount==3) {
					output = "Please Input Student Info: 'student number, name, is fulltime(y/n)'"
							+ "\n333333333, Harry, y";
				}
				if (Config.testMode == true && sccount==4) {
					output = "Please Input Student Info: 'student number, name, is fulltime(y/n)'"
							+ "\n444444444, Sam, y";
				}
				if (Config.testMode == true && sccount==5) {
					output = "Please Input Student Info: 'student number, name, is fulltime(y/n)'"
							+ "\n555555555, Sasha, y";
				}
				sccount++;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("cancel course")) {
				output = "Please Input Course Info: 'course code'\nAvailable Course List: ";
				for (int i = 0; i < University.getInstance().Courses().size(); i++) {
					output = output + "\n" + University.getInstance().Courses().get(i).toString();
				}
				state = CANCELCOURSE;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("delete course")) {
				output += "Delete Course \n";
				output += "Please Input Course Info: 'course code'\nAvailable Course List: ";
				for (int i = 0; i < University.getInstance().Courses().size(); i++) {
					output = output + "\n" + University.getInstance().Courses().get(i).toString();
				}
				if (Config.testMode == true) {
					output += "\n123456";
				}
				state = DELETECOURSE;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("delete student")) {
				output += "What can I do for you? Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List.";
				output += "\nDelete Student";
				output += "\nPlease Input Student Info: 'student number'\nAvailable Student List: ";
				for (int i = 0; i < University.getInstance().Students().size(); i++) {
					output = output + "\n" + University.getInstance().Students().get(i).toString();
				}
				if (Config.testMode == true) {
					output += "\n100996742";
				}
				state = DELETESTUDENT;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("dean's list")) {
				o = outputHandler.deansList();
				output = o.getOutput();
				state = o.getState();
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("log out")) {
				output = "Successfully Log Out!";
				if (Config.testMode == true) {
					output = "log out" + "\nSuccessfully Log Out!";
				}
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List.";
				state = CLERK;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				output = "Please select from the menu.Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List.";

				state = CLERK;
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == STUDENT) {
			int studentnumber = University.getInstance().getCurrentstudent();
			Student student = (Student) University.getInstance().GetStudent(studentnumber);
			if (input.equalsIgnoreCase("select course")) {
				List<Course> availableCourses = new ArrayList<Course>(University.getInstance().Courses());
				List<Course> notavailableCourses = new ArrayList<Course>(student.getSelectedCourses());
				notavailableCourses.addAll(student.getRegisteredCourses());
				notavailableCourses.addAll(student.getCompletedCourses());
				availableCourses.removeAll(notavailableCourses);
				if (availableCourses.size() > 0) {
					if (Config.testMode == true) {
						output = "Select Course\n";
					}
					output += "Please Input Course Info: 'course code'\nAvailable Course List: ";
					for (int i = 0; i < availableCourses.size(); i++) {
						output = output + "\n" + availableCourses.get(i).toString();
					}
					state = SELECTCOURSE;
				} else {
					output = "No Available Courses!";
					state = SELECTCOURSE;
				}
				if (Config.testMode == true && secount==0) {
					output+="\n456789";
				}
				if (Config.testMode == true && secount==1) {
					output+="\n987654";
				}
				if (Config.testMode == true && secount==3) {
					output+="\n234567";
				}
				//output+=secount;
				secount++;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("register for course")) {
				List<Course> availableCourses = new ArrayList<Course>(student.getSelectedCourses());
				if (availableCourses.size() > 0) {
					if (Config.testMode == true) {
					output = "Register for Course\n";
					}
					output += "Please Input Course Info: 'course code'\nAvailable Course List: ";
					for (int i = 0; i < availableCourses.size(); i++) {
						output = output + "\n" + availableCourses.get(i).toString();
					}
					state = REGISTERFORCOURSE;
				} else {
					output = "No Available Courses!";
					state = REGISTERFORCOURSE;
				}
				if (Config.testMode == true && rcount==0) {
					output+="\n456789";
				}
				if (Config.testMode == true && rcount==1) {
					output+="\n987654";
				}
				if (Config.testMode == true && rcount==2) {
					output+="\n234567";
				}
				rcount++;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("drop course")) {
				List<Course> availableCourses = new ArrayList<Course>(student.getRegisteredCourses());
				if (availableCourses.size() > 0) {
					if (Config.testMode == true) {
						output="Drop course\n";
					}
					output += "Please Input Course Info: 'course code'\nAvailable Course List: ";
					for (int i = 0; i < availableCourses.size(); i++) {
						output = output + "\n" + availableCourses.get(i).toString();
					}
					state = DROPCOURSE;
				} else {
					output = "No Available Courses!";
					state = DROPCOURSE;
				}
				if (Config.testMode == true) {
					output+="\n234567";
				}
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("deregister course")) {
				List<Course> availableCourses = new ArrayList<Course>(student.getRegisteredCourses());
				if (availableCourses.size() > 0) {
					if (Config.testMode == true) {
						output="Please select from the menu. Menu: Select Course, Register for Course, Drop Course, Deregister Course.y";
						output+="Deregister Course\n";
						dDone=true;
					}
					output += "Please Input Course Info: 'course code'\nAvailable Course List: ";
					for (int i = 0; i < availableCourses.size(); i++) {
						output = output + "\n" + availableCourses.get(i).toString();
					}
					state = DEREGISTERCOURSE;
				} else {
					output = "No Available Courses!";
					state = DEREGISTERCOURSE;
				}
				if (Config.testMode == true) {
					output+="\n456789";
				}
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("log out")) {
				if (Config.testMode == true) {
					output += "log out\n";
				}
				output += "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Select Course, Register for Course, Drop Course, Deregister Course.";
				state = STUDENT;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				output = "Please select from the menu. Menu: Select Course, Register for Course, Drop Course, Deregister Course.";
				if (Config.testMode == true) {
				//	output += "\nRegister for course";
				}
				state = STUDENT;
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == CREATECOURSE) {
			if (input.equalsIgnoreCase("log out")) {
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List.";
				state = CLERK;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				o = outputHandler.createCourse(input);
				output = o.getOutput();
				state = o.getState();
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == CREATESTUDENT) {
			if (input.equalsIgnoreCase("log out")) {
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List.";
				state = CLERK;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				o = outputHandler.createStudent(input);
				output = o.getOutput();
				state = o.getState();
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == CANCELCOURSE) {
			if (input.equalsIgnoreCase("log out")) {
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List.";
				state = CLERK;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				o = outputHandler.cancelCourse(input);
				output = o.getOutput();
				state = o.getState();
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == DELETECOURSE) {
			if (input.equalsIgnoreCase("log out")) {
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List.";
				state = CLERK;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				o = outputHandler.deleteCourse(input);
				output = o.getOutput();
				state = o.getState();
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == DELETESTUDENT) {
			if (input.equalsIgnoreCase("log out")) {
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List.222";
				state = CLERK;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				o = outputHandler.deleteStudent(input);
				output = o.getOutput();
				state = o.getState();
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == SELECTCOURSE) {
			if (input.equalsIgnoreCase("log out")) {
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Select Course, Register for Course, Drop Course, Deregister Course.";

				state = STUDENT;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				o = outputHandler.selectCourse(input);
				output = o.getOutput();
				state = o.getState();
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == REGISTERFORCOURSE) {
			if (input.equalsIgnoreCase("log out")) {
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Select Course, Register for Course, Drop Course, Deregister Course.";
				if (Config.testMode == true) {
					output += "\nRegister For Course";
				}
				state = STUDENT;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				o = outputHandler.registerforCourse(input);
				output = o.getOutput();
				state = o.getState();
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == DROPCOURSE) {
			if (input.equalsIgnoreCase("log out")) {
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Select Course, Register for Course, Drop Course, Deregister Course.";
				state = STUDENT;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				o = outputHandler.dropCourse(input);
				output = o.getOutput();
				state = o.getState();
				oo.setOutput(output);
				oo.setState(state);
			}
		} else if (state == DEREGISTERCOURSE) {
			if (input.equalsIgnoreCase("log out")) {
				output = "Successfully Log Out!";
				state = WAITING;
				oo.setOutput(output);
				oo.setState(state);
			} else if (input.equalsIgnoreCase("main menu")) {
				output = "What can I do for you? Menu: Select Course, Register for Course, Drop Course, Deregister Course.";
				state = STUDENT;
				oo.setOutput(output);
				oo.setState(state);
			} else {
				o = outputHandler.deregisterCourse(input);
				output = o.getOutput();
				state = o.getState();
				oo.setOutput(output);
				oo.setState(state);
			}
		}
		return oo;
	}

}
