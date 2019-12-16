package org.springframework.beans.factory.annotation;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

public class QualifierAnnotationTests {

	@Test
	public void test() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(MyService1.class, MyService2.class,Person.class);
		annotationConfigApplicationContext.refresh();
		Person person = annotationConfigApplicationContext.getBean(Person.class);
		person.getService().doService();

	}

	@Component("myService1")
	public static class MyService1 implements Service {


		@Override
		public void doService() {
			System.out.println(getClass().getSimpleName());
		}
	}

	@Component("MyService2")
	public static class MyService2 implements Service {


		@Override
		public void doService() {
			System.out.println(getClass().getSimpleName());
		}
	}


	public   interface Service {
		void doService();
	}

	@Component
	public static  class Person {

		@Autowired
		@Qualifier("myService1")
		private Service service;

		public Service getService() {
			return service;
		}
	}
}
