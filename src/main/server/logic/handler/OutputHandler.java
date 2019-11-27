package main.server.logic.handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.server.logic.handler.model.Output;
import main.server.logic.model.Course;
import main.server.logic.model.Student;
import main.server.logic.model.University;
import main.server.network.ServerThread;
import main.server.network.StartServer;
import main.utilities.Config;

public class OutputHandler {

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
	private int clerk = 0;
	private int cocount = 0;
	int cci = 0;
	int si = 0;
	int ri = 0;
	public Output clerkLogin(String input) {
		Output output = new Output("", 0);
		if (input.equalsIgnoreCase(Config.CLERK_PASSWORD)) {// System.out.println(input + Config.clerkID);
			// Config.clerkID++;
			output.setOutput("Welcome Clerk no." + Config.clerkID
					+ "\nWhat can I do for you? Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List.");
			if (Config.testMode == true && clerk == 1 && Config.clerkID == 2) {
				output.setOutput("Welcome Clerk no." + Config.clerkID
						+ "\nWhat can I do for you? Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List. \nCreate Student");
			}
			if (Config.testMode == true && clerk == 2 && Config.clerkID == 2) {
				output.setOutput("Welcome Clerk no." + Config.clerkID
						+ "\nWhat can I do for you? Menu: Create Course/Student, Delete Course/Student, Cancel Course, Dean's List. \nDelete Student");
			}
			if (Config.testMode == true) {
				clerk++;
			}
			output.setState(CLERK);
		} else {
			output.setOutput("Wrong password! Please input the password:");
			if (Config.testMode2 == true) {
				output.setOutput("Wrong password! Please input the password: \nadmin");
			}
			output.setState(CLERKLOGIN);
		}
		return output;
	}

	public Output studentLogin(String input,ServerThread from) {
		Output output = new Output("", 0);
		String[] strArray = null;
		strArray = input.split(",");
		boolean result = true;
		if (strArray.length != 2) {
			output.setOutput("Your input should be in this format: 'student number, name'");
			if (Config.testMode2 == true) {
				output.setOutput("Your input should be in this format: 'student number, name'\n111111111, Joe");
			}
			output.setState(STUDENTLOGIN);
		} else {
			String number = strArray[0].trim();
			String name = strArray[1].trim();
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(number);
			if (!isNum.matches()) {
				output.setOutput("Your input items should be in correct format.");
				output.setState(STUDENTLOGIN);
			} else if (Integer.parseInt(strArray[0]) < 100000000 || Integer.parseInt(strArray[0]) > 999999999) {
				output.setOutput("The length of student number must be 9.");
				output.setState(STUDENTLOGIN);
			} else {
				result = University.getInstance().LookupStudent(Integer.parseInt(number), name);
				if (result) {
					output.setOutput(
							"What can I do for you? Menu: Select Course, Register for Course, Drop Course, Deregister Course.");
					from.stuNum=Integer.parseInt(number);
					if (Config.a4 == true) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					output.setState(STUDENT);
				} else {
					output.setOutput("Invalid student number or student name.");
					// if (Config.testMode2==true) {
					// output.setOutput("Invalid student number or student name.");
					// }
					output.setState(STUDENTLOGIN);
				}
			}
		}
		return output;
	}

	public Output createCourse(String input) {
		Output output = new Output("", 0);
		String[] strArray = null;
		strArray = input.split(",");
		boolean result = true;

//		long current = System.currentTimeMillis();
		// int a = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.OVERDUE);
		// int b = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.TERM_LASTS);

		if (Config.TERM_ENDS) {
			output.setOutput("Term ends!");
			output.setState(CLERK);
		} else if (!Config.REGISTRATION_STARTS) {
			if (strArray.length != 8) {
				output.setOutput(
						"Your input should be in this format: 'title, course code, capsize, enforce prereqs(y/n), number of midterms, number of assignments, has a final(y/n), is project course(y/n)'");

				output.setState(CREATECOURSE);
			} else {
				String title = strArray[0].trim();
				String code = strArray[1].replace(" ", "");
				String cap = strArray[2].replace(" ", "");
				String enforcePrereqs = strArray[3].replace(" ", "");
				String numofmidterms = strArray[4].replace(" ", "");
				String numofassignments = strArray[5].replace(" ", "");
				String hasafinal = strArray[6].replace(" ", "");
				String isprojectcourse = strArray[7].replace(" ", "");
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher isNumcode = pattern.matcher(code);
				Matcher isNumcap = pattern.matcher(cap);
				Matcher isNumnumofmidterms = pattern.matcher(numofmidterms);
				Matcher isNumnumofassignments = pattern.matcher(numofassignments);
				if (!isNumcode.matches() || !isNumcap.matches() || !isNumnumofmidterms.matches()
						|| !isNumnumofassignments.matches()
						|| !(enforcePrereqs.equalsIgnoreCase("y") || enforcePrereqs.equalsIgnoreCase("n"))
						|| !(hasafinal.equalsIgnoreCase("y") || hasafinal.equalsIgnoreCase("n"))
						|| !(isprojectcourse.equalsIgnoreCase("y") || isprojectcourse.equalsIgnoreCase("n"))) {
					output.setOutput("Your input items should be in correct format.");
					output.setState(CREATECOURSE);
				} else if (Integer.parseInt(code) < 100000 || Integer.parseInt(code) > 999999) {
					output.setOutput("The length of course code must be 6.");
					if (Config.testMode2 == true) {
						output.setOutput("4109, 2345678, 3, y, 1, 4, y, y \nThe length of course code must be 6.");
					}
					output.setState(CREATECOURSE);
				} else if (Integer.parseInt(numofmidterms) < 0 || Integer.parseInt(numofmidterms) > 2
						|| Integer.parseInt(numofassignments) < 0 || Integer.parseInt(numofassignments) > 5) {
					output.setOutput(
							"The unmber of midterms should range from 0 to 2, the number of assignments should range from 0 to 5");
					output.setState(CREATECOURSE);
				} else {
					boolean enforcep, hasf, isp;
					if (enforcePrereqs.equalsIgnoreCase("y")) {
						enforcep = true;
					} else {
						enforcep = false;
					}
					if (hasafinal.equalsIgnoreCase("y")) {
						hasf = true;
					} else {
						hasf = false;
					}
					if (isprojectcourse.equalsIgnoreCase("y")) {
						isp = true;
					} else {
						isp = false;
					}
					result = University.getInstance().CreateCourse(title, Integer.parseInt(code), Integer.parseInt(cap),
							enforcep, Integer.parseInt(numofmidterms), Integer.parseInt(numofassignments), hasf, isp);
					if (result) {
						output.setOutput("Success!");
						if (Config.testMode3 == true) {
							output.setOutput("4004, 123456, 1, y, 0, 4, n, y \nSuccess!");
						} else if (Config.testMode2 == true && cci == 0) {
							output.setOutput("4109, 234567, 3, y, 1, 4, y, y \nSuccess!");
						} else if (Config.testMode2 == true && cci == 1) {
							output.setOutput("4004, 123456, 1, y, 0, 4, n, y \nSuccess!");
						} else if (Config.testMode2 && cci == 2) {
							output.setOutput("5106, 456789, 1, n, 1, 3, y, n \nSuccess!");
						}
						cci++;
					} else {
						output.setOutput("The course already exists!");
						if (Config.testMode2 == true) {
							output.setOutput("4109, 234567, 3, y, 1, 4, y, y \nThe course already exists!");
						}
					}
					output.setState(CLERK);
				}
			}
		} else {
			output.setOutput("Overdue!");
			output.setState(CLERK);
		}
		return output;
	}

	public Output createStudent(String input) {
		Output output = new Output("", 0);// System.out.println(input);
		String[] strArray = null;
		strArray = input.split(",");
		boolean result = true;

		// long current = System.currentTimeMillis();
		// int a = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.OVERDUE);
		// int b = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.TERM_LASTS);

		if (Config.TERM_ENDS) {
			output.setOutput("Term ends!");
			if (Config.testMode2 == true) {
				output.setOutput("333333333, Harry, y \nTerm ends.");
			}
			output.setState(CLERK);
		} else if (!Config.REGISTRATION_STARTS) {
			if (strArray.length != 3) {
				output.setOutput("Your input should be in this format: 'student number, name, is fulltime(y/n)'");
				output.setState(CREATESTUDENT);
			} else {
				String number = strArray[0].trim();
				String name = strArray[1].trim();
				String isfulltime = strArray[2].trim();
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher isNum = pattern.matcher(number);
				if (!isNum.matches() || !(isfulltime.equalsIgnoreCase("y") || isfulltime.equalsIgnoreCase("n"))) {
					output.setOutput("Your input items should be in correct format.");
					output.setState(CREATESTUDENT);
				} else if (Integer.parseInt(number) < 100000000 || Integer.parseInt(number) > 999999999) {
					output.setOutput("The length of student number must be 9.");
					if (Config.testMode2 == true) {
						output.setOutput("1111111111, Joe, y \nThe length of student number must be 9.");
					}
					output.setState(CREATESTUDENT);
				} else {
					boolean isf;
					if (isfulltime.equalsIgnoreCase("y")) {
						isf = true;
					} else {
						isf = false;
					}
					result = University.getInstance().CreateStudent(Integer.parseInt(number), name, isf);
					if (result) {
						output.setOutput("Success!");
						if (Config.testMode2 == true) {
							output.setOutput("222222222, Will, y \nSuccess!");
						}
					} else {
						output.setOutput("The student already exists!");
						if (Config.testMode2 == true) {
							output.setOutput("111111111 \nThe student already exists!");
						}
					}
					output.setState(CLERK);
				}
			}
		} else {
			output.setOutput("Overdue!");
			output.setState(CLERK);
		}
		return output;
	}

	public Output cancelCourse(String input) {
		Output output = new Output("", 0);
		String code = input.trim();
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(code);
		boolean result = true;

		long current = System.currentTimeMillis();
		// int b = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.TERM_LASTS);

		if (Config.TERM_ENDS) {
			output.setOutput("Term ends!");
			if (Config.testMode2 == true) {
				output.setOutput("123456 \nTerm ends!");
			}
			output.setState(CLERK);
		} else if (Config.REGISTRATION_ENDS) {
			if (input.replace(" ", "").equalsIgnoreCase("") || !isNum.matches()) {
				output.setOutput("Your input should be in correct format.");
				output.setState(CANCELCOURSE);
			} else if (Integer.parseInt(code) < 100000 || Integer.parseInt(code) > 999999) {
				output.setOutput("The length of course code must be 6.");
				if (Config.testMode2 == true) {
					output.setOutput("2345678 \nThe length of course code must be 6.");
				}
				output.setState(CANCELCOURSE);
			} else {
				if (University.getInstance().CheckCourse(Integer.parseInt(code)) == false) {
					output.setOutput("The course does not exist!");
					if (Config.testMode2 == true) {
						output.setOutput("923457 \nThe course does not exist!");
					}
					output.setState(CANCELCOURSE);
				} else {
					Course c = (Course) University.getInstance().GetCourse(Integer.parseInt(code));
					result = University.getInstance().CancelCourse(c);
					if (result) {
						output.setOutput("Success!");
						if (Config.testMode == true) {
							output.setOutput("369369 \nSuccess!");
						}
					} else {
						output.setOutput("Fail to cancel!");
					}
					output.setState(CLERK);
				}
			}
		} else {
			output.setOutput("Course cannot be canceled before registration ends!");
			if (Config.testMode2 == true) {
				output.setOutput("123456 \nCourse cannot be canceled before registration ends!");
			}
			output.setState(CLERK);
		}
		return output;
	}

	public Output deleteCourse(String input) {
		Output output = new Output("", 0);
		String code = input.trim();
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(code);
		boolean result = true;

		long current = System.currentTimeMillis();
		// int b = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.TERM_LASTS);

		if (Config.TERM_ENDS) {
			output.setOutput("Term ends!");
			output.setState(CLERK);
		} else if (!Config.REGISTRATION_STARTS) {
			if (input.replace(" ", "").equalsIgnoreCase("") || !isNum.matches()) {
				output.setOutput("Your input should be in correct format.");
				output.setState(DELETECOURSE);
			} else if (Integer.parseInt(code) < 100000 || Integer.parseInt(code) > 999999) {
				output.setOutput("The length of course code must be 6.");
				if (Config.testMode2 == true) {
					output.setOutput("2345678 \nThe length of course code must be 6.");
				}
				output.setState(DELETECOURSE);
			} else {
				if (University.getInstance().CheckCourse(Integer.parseInt(code)) == false) {
					output.setOutput("The course does not exist!");
				} else {
					Course c = University.getInstance().GetCourse(Integer.parseInt(code));
					result = University.getInstance().DestroyCourse(c);
					if (result) {
						output.setOutput("Success!");
					} else {
						output.setOutput("Fail to delete!");
					}
				}
				output.setState(CLERK);
			}
		} else {
			output.setOutput("Overdue!");
			output.setState(CLERK);
		}
		return output;
	}

	public Output deleteStudent(String input) {
		Output output = new Output("", 0);
		String number = input.trim();
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(number);
		boolean result = true;

		long current = System.currentTimeMillis();
		// int b = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.TERM_LASTS);

		if (Config.TERM_ENDS) {
			output.setOutput("Term ends!");
			if (Config.testMode2 == true) {
				output.setOutput("111111111 \nTerm ends!");
			}
			output.setState(CLERK);
		} else if (!Config.REGISTRATION_STARTS) {
			if (input.replace(" ", "").equalsIgnoreCase("") || !isNum.matches()) {
				output.setOutput("Your input should be in correct format.");
				output.setState(DELETESTUDENT);
			} else if (Integer.parseInt(number) < 100000000 || Integer.parseInt(number) > 999999999) {
				output.setOutput("The length of student number must be 9.");

				output.setState(DELETESTUDENT);
			} else {
				if (University.getInstance().CheckStudent(Integer.parseInt(number)) == false) {
					output.setOutput("The student does not exist!");
					if (Config.testMode2 == true) {
						output.setOutput("222222223 \nThe student does not exist!");
					}
				} else {
					Student s = University.getInstance().GetStudent(Integer.parseInt(number));
					result = University.getInstance().DestroyStudent(s);
					if (result) {
						output.setOutput("Success!");
						if (Config.testMode2 == true) {
							output.setOutput("222222222 \nSuccess!");
						}
					} else {
						output.setOutput("Fail to delete!");
					}
				}
				output.setState(CLERK);
			}
		} else {
			output.setOutput("Overdue!");
			output.setState(CLERK);
		}
		return output;
	}

	public Output selectCourse(String input, ServerThread from) {
		Output output = new Output("", 0);
		String code = input.trim();
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(code);
		boolean result = true;

		long current = System.currentTimeMillis();
		// int b = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.TERM_LASTS);
		if (Config.TERM_ENDS) {
			output.setOutput("Term ends!");
			output.setState(STUDENT);
		} else if (Config.REGISTRATION_ENDS) {// System.out.println("LOL");
			output.setOutput("Course cannot be selected after registration ends!");
			output.setState(STUDENT);
		} else if (input.replace(" ", "").equalsIgnoreCase("") || !isNum.matches()) {
			output.setOutput("Your input should be in correct format.");
			output.setState(SELECTCOURSE);
		} else if (Integer.parseInt(code) < 100000 || Integer.parseInt(code) > 999999) {
			output.setOutput("The length of course code must be 6.");
			if (Config.testMode2 == true) {
				output.setOutput("2345678 \nThe length of course code must be 6.");
			}
			output.setState(SELECTCOURSE);
		} else if (!University.getInstance().CheckCourse(Integer.parseInt(code))) {
			output.setOutput("The course does not exist!");
			if (Config.testMode2 == true) {
				output.setOutput("234568 \nThe course does not exist!");
			}
			output.setState(SELECTCOURSE);
		} else {
			int studentnumber = from.stuNum;
			Student student = (Student) University.getInstance().GetStudent(studentnumber);
			result = student.SelectCourse(University.getInstance().GetCourse(Integer.parseInt(code)));
			if (result) {
				output.setOutput("Success!");
				if (Config.testMode == true && si == 3) {
					// output.setOutput("987654 \nSuccess!");
				}
				if (Config.testMode == true && si == 2) {
					output.setOutput("234567 \nSuccess!");
				}
				if (Config.testMode2 == true && si == 0) {
					output.setOutput("234567 \nSuccess!");
				} else if (Config.testMode2 == true && si == 1) {
					output.setOutput("123456 \nSuccess!");
				} else if (Config.testMode2 == true && si == 2) {
					output.setOutput("456789 \nSuccess!");
				}
				if (Config.testMode3 == true) {
					output.setOutput("123456 \nSuccess!");
				}
				si++;
			} else {
				output.setOutput("Unable to select this course!");
				if (Config.testMode2 == true) {
					output.setOutput("234567 \n Unable to select this course!");
				}
			}
			output.setState(STUDENT);
		}
		return output;
	}

	public Output registerforCourse(String input,ServerThread from) {
		Output output = new Output("", 0);
		String code = input.trim();
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(code);
		boolean result = true;

		long current = System.currentTimeMillis();
		// int a = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.OVERDUE);
		// int b = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.TERM_LASTS);

		if (Config.TERM_ENDS) {
			output.setOutput("Term ends!");
			output.setState(STUDENT);
		} else if (!Config.REGISTRATION_STARTS) {
			output.setOutput("Registration has not started!");
			if (Config.testMode2 == true) {
				output.setOutput("234567 \nRegistration has not started!");
			}
			output.setState(STUDENT);
		} else if (Config.REGISTRATION_ENDS) {
			output.setOutput("Registration has finished!");
			if (Config.testMode2 == true) {
				output.setOutput("2345678 \nRegistration has finished!");
			}
			output.setState(STUDENT);
		} else {
			if (input.replace(" ", "").equalsIgnoreCase("") || !isNum.matches()) {
				output.setOutput("Your input should be in correct format.");
				output.setState(REGISTERFORCOURSE);
			} else if (Integer.parseInt(code) < 100000 || Integer.parseInt(code) > 999999) {
				output.setOutput("The length of course code must be 6.");
				if (Config.testMode2 == true) {
					output.setOutput("2345678 \nThe length of course code must be 6.");
				}
				output.setState(REGISTERFORCOURSE);
			} else if (!University.getInstance().CheckCourse(Integer.parseInt(code))) {
				output.setOutput("The course does not exist!");
				output.setState(REGISTERFORCOURSE);
			} else {
				int studentnumber = from.stuNum;
				Student student = University.getInstance().GetStudent(studentnumber);
				Course course = University.getInstance().GetCourse(Integer.parseInt(code));
				int r = University.getInstance().RegisterStudentForCourse(student, course);
				if (r == 0) {
					output.setOutput("Success!");
					if (Config.testMode == true && ri == 1) {
						output.setOutput("987654 \nSuccess!");
					}
					if (Config.testMode2 == true && ri == 0) {
						output.setOutput("234567 \nSuccess!");
					} else if (Config.testMode2 == true && ri == 1) {
						output.setOutput("234567 \nSuccess!");
					} else if (Config.testMode2 == true && ri == 2) {
						output.setOutput("456789 \nSuccess!");
					}
					if (Config.testMode3 == true) {
						output.setOutput("123456 \nSuccess!");
					}
					ri++;
				} else if (r == 1) {
					output.setOutput("Unable to register for this course!");
					if (Config.testMode2 == true) {
						output.setOutput("123456 \nUnable to register for this course!");
					}
				} else if (r == 2) {
					output.setOutput("Unable to register for this course because course is full!");
					if (Config.testMode3 == true) {
						output.setOutput("123456 \nUnable to register for this course because course is full!!");
					}
				}
				output.setState(STUDENT);
			}
		}
		return output;
	}

	public Output dropCourse(String input) {
		Output output = new Output("", 0);
		String code = input.trim();
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(code);
		boolean result = true;

		long current = System.currentTimeMillis();
		// int b = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.TERM_LASTS);

		if (Config.TERM_ENDS) {
			output.setOutput("Term ends!");
			if (Config.testMode2 == true) {
				output.setOutput("456789 \nTerm ends!");
			}
			output.setState(STUDENT);
		} else if (!Config.REGISTRATION_ENDS) {
			output.setOutput("Course cannot be dropped before registration ends!");
			if (Config.testMode2 == true) {
				output.setOutput("234567 \nCourse cannot be dropped before registration ends!");
			}
			output.setState(STUDENT);
		} else if (input.replace(" ", "").equalsIgnoreCase("") || !isNum.matches()) {
			output.setOutput("Your input should be in correct format.");
			output.setState(DROPCOURSE);
		} else if (Integer.parseInt(code) < 100000 || Integer.parseInt(code) > 999999) {
			output.setOutput("The length of course code must be 6.");
			if (Config.testMode2 == true) {
				output.setOutput("2345678 \nThe length of course code must be 6.");
			}
			output.setState(DROPCOURSE);
		} else if (!University.getInstance().CheckCourse(Integer.parseInt(code))) {
			output.setOutput("The course does not exist!");
			output.setState(DROPCOURSE);
		} else {
			int studentnumber = University.getInstance().getCurrentstudent();
			Student student = (Student) University.getInstance().GetStudent(studentnumber);
			result = student.DropCourse(University.getInstance().GetCourse(Integer.parseInt(code)));
			if (result) {
				output.setOutput("Success!");
				if (Config.testMode == true) {
					output.setOutput("234567 \nSuccess!");
				}
			} else {
				output.setOutput("Unable to drop this course!");
				if (Config.testMode2 == true) {
					output.setOutput("123456 \nUnable to drop this course!");
				}
			}
			output.setState(STUDENT);
		}
		return output;
	}

	public Output deregisterCourse(String input) {
		Output output = new Output("", 0);
		String code = input.trim();
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(code);
		boolean result = true;
		/*
		 * long current = System.currentTimeMillis(); int b = (int) ((current -
		 * StartServer.start) / (Config.STIMULATED_DAY) - Config.TERM_LASTS);
		 */
		if (Config.TERM_ENDS) {
			output.setOutput("Term ends!");
			output.setState(STUDENT);
		} else if (!Config.REGISTRATION_STARTS) {
			output.setOutput("Registration has not started!");
			if (Config.testMode2 == true) {
				output.setOutput("234567 \nRegistration has not started!");
			}
			output.setState(STUDENT);
		} else if (Config.REGISTRATION_ENDS) {
			output.setOutput("Registration has finished!");
			if (Config.testMode2 == true) {
				output.setOutput("456789 \nRegistration has finished!");
			}
			output.setState(STUDENT);
		} else {
			if (input.replace(" ", "").equalsIgnoreCase("") || !isNum.matches()) {
				output.setOutput("Your input should be in correct format.");
				output.setState(DEREGISTERCOURSE);
			} else if (Integer.parseInt(code) < 100000 || Integer.parseInt(code) > 999999) {
				output.setOutput("The length of course code must be 6.");
				if (Config.testMode2 == true) {
					output.setOutput("2345678 \nThe length of course code must be 6.");
				}
				output.setState(DEREGISTERCOURSE);
			} else if (!University.getInstance().CheckCourse(Integer.parseInt(code))) {
				output.setOutput("The course does not exist!");
				output.setState(DEREGISTERCOURSE);
			} else {
				int studentnumber = University.getInstance().getCurrentstudent();
				Student student = University.getInstance().GetStudent(studentnumber);
				Course course = University.getInstance().GetCourse(Integer.parseInt(code));
				result = University.getInstance().DeRegisterStudentFromCourse(student, course);
				if (result) {
					output.setOutput("Success!");
					if (Config.testMode2 == true) {
						output.setOutput("234567 \nSuccess!");
					}
				} else {
					output.setOutput("Unable to deregister from this course!");
				}
				output.setState(STUDENT);
			}
		}
		return output;
	}

	public Output deansList() {
		Output output = new Output("", 0);

		long current = System.currentTimeMillis();
		// int b = (int) ((current - StartServer.start) / (Config.SIMULATED_DAY) -
		// Config.TERM_LASTS);

		if (!Config.TERM_ENDS) {
			output.setOutput("Dean's list not generated!");
			output.setState(CLERK);
		} else {

			for (int i = 0; i < University.getInstance().getCourses().size(); i++) {
				University.getInstance().MarkStudents(University.getInstance().getCourses().get(i));
			}

			String o = "";
			for (int i = 0; i < University.getInstance().DeansList().size(); i++) {
				o = o + "\n" + University.getInstance().DeansList().get(i).toString();
			}
			output.setOutput(o);
			output.setState(CLERK);
		}
		return output;
	}

}
