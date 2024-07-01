package utils;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

public class JSFunction {
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = ""
					+ "<script>"
					+ "alert('"+msg+"');"
					+ "location.href='"+url+"';"
					+"</script>";
			out.print(script);
		}catch(Exception e){}
	}

	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = "" + "<script>" + " alert('"+msg+"');" + " history.back();" +"</script>";
			out.print(script);
		}catch(Exception e){}
	}

	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		// TODO Auto-generated method stub
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
					+ "<script>"
					+ "alert('"+msg+"');"
					+ "location.href='"+url+"';"
					+"</script>";
			writer.print(script);
		}catch(Exception e){}
	}

	public static void alertBack(HttpServletResponse resp, String msg) {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			String script = "" + "<script>" + " alert('"+msg+"');" + " history.back();" +"</script>";

			writer.print(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
