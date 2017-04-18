import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class testReflect {

	public testReflect() {
		// TODO Auto-generated constructor stub
	}
	
	public static void say(String s){
		System.out.println(s);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz=Class.forName("testReflect");
		Method method = clazz.getMethod("say", String.class);
		Object o = method.invoke(null, "whatever");
		System.out.println(o);
		System.out.println();
	}

}
