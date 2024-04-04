package model;

import java.sql.*;
import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;

public class Registration {

	    private Connection con;
	    HttpSession se;

	    public Registration(HttpSession session) {
	        try {

	            Class.forName("com.mysql.jdbc.Driver"); // load the drivers
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "tiger");
	            // connection with data base
	            se = session;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public String Registration(String name, String phone, String email, String pw) {
	        PreparedStatement ps;
	        String status = "";
	        try {
	            Statement st = null;
	            ResultSet rs = null;
	            st = con.createStatement();
	            rs = st.executeQuery("select * from spring1 where phone='" + phone + "' or email='" + email + "';");
	            boolean b = rs.next();
	            if (b) {
	                status = "existed";
	            } else {
	                ps = (PreparedStatement) con.prepareStatement("insert into spring1(name,phone,email,password,date)values(?,?,?,?,now())");
	                ps.setString(1, name);
	                ps.setString(2, phone);
	                ps.setString(3, email);
	                ps.setString(4, pw);
	                int a = ps.executeUpdate();
	                if (a > 0) {
	                    status = "success";
	                } else {
	                    status = "failure";
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return status;
	    }
	    
	    

	    public String login(String email, String pass) {
	        String status1 = "", id = "";
	        String name = "", emails = "";

	        try {
	            Statement st = null;
	            ResultSet rs = null;
	            st = con.createStatement();

	            rs = st.executeQuery("select * from spring1 where email='" + email + "' and password='" + pass + "';");
	            boolean b = rs.next();
	            if (b == true) {
	                id = rs.getString("slno");
	                name = rs.getString("name");
	                emails = rs.getString("email");
	                se.setAttribute("uname", name);
	                se.setAttribute("email", emails);
	                se.setAttribute("id", id);
	                status1 = "success";
	            } else {
	                status1 = "failure";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return status1;
	    }
	    
	    
	    
	    public Student getInfo() {
	        Statement st = null;
	        ResultSet rs = null;
	        Student s = null;
	        try {
	            st = con.createStatement();
	            rs = st.executeQuery("select * from spring1 where slno= '" + se.getAttribute("id") + "'");
	            boolean b = rs.next();
	            if (b == true) {
	                s = new Student();
	                s.setName(rs.getString("name"));
	                s.setPhonenumber(rs.getString("phone"));
	                s.setEmail(rs.getString("email"));
	            } else {
	                s = null;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return s;
	    }
	    
	    
	    
	    
	    public String update(String name, String pno, String email) {
	        String status = "";
	        Statement st = null;
	        ResultSet rs = null;
	        try {
	            st = con.createStatement();
	            st.executeUpdate("update spring1  set name='" + name + "',phone='" + pno + "',email='" + email + "' where slno= '" + se.getAttribute("id") + "' ");
	            se.setAttribute("uname", name);
	            status = "success";
	        } catch (Exception e) {
	            status = "failure";
	            e.printStackTrace();
	        }

	        return status;
	    }
	    
	    
	    
	    
	    public ArrayList<Student> getUserinfo(String id) {
	        Statement st = null;
	        ResultSet rs = null;
	        ArrayList<Student> al = new ArrayList<Student>();
	        try {
	            st = con.createStatement();
	            String qry = "select * from  spring1 where slno = '" + id + "';";
	            rs = st.executeQuery(qry);
	            while (rs.next()) {
	                Student p = new Student();
	                p.setId(rs.getString("slno"));
	                p.setName(rs.getString("name"));
	                p.setEmail(rs.getString("email"));
	                p.setPhonenumber(rs.getString("phone"));
	                p.setDate(rs.getString("date"));
	                al.add(p);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return al;
	    }
	    
	    
	    
        public ArrayList<Student> getUserDetails(){
        	ArrayList<Student> al=new ArrayList<>();
        	
        	Statement st=null;
        	ResultSet rs=null;
        	try {
        		st=con.createStatement();
        		rs=st.executeQuery("select * from spring1 where slno not in(1)");
        		while(rs.next()) {
        			Student s=new Student();
        			s.setId(rs.getString("slno"));
        			s.setName(rs.getString("name"));
        			s.setPhonenumber(rs.getString("phone"));
        			s.setEmail(rs.getString("email"));
        		    al.add(s);
        		}
        		
        	}
        	catch (Exception e) {
				e.printStackTrace();
			}
        	return al;	
        }
        
        public String delete(int id) {
        	String status="";
        	int a;
        	Statement s=null;
        	try {
        		s=con.createStatement();
        		a=s.executeUpdate("Delete from spring1 where slno='"+id+"'");
        		if(a>0) {
        			status="success";
        		}
        		else {
        			status="failure";
        		}
        	}
        	catch (Exception e) {
				e.printStackTrace();
			}
        	return status;
        }
        
       

		public boolean updatePassword(String email, String password) {
			boolean check = false;
        	Statement st = null;
        	try {
        		st = con.createStatement();
        	int a = st.executeUpdate("update spring.spring1 set password='" + password + "'where email='" + email + "' ");
        	if (a > 0) {
        		check = true;
        	} else {
        		System.out.println("Update password failed ");
        		}
        	} catch (Exception e) {
        		e.printStackTrace();
        	}

        		return check;
		}

	}
