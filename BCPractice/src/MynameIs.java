import JustPractice.hello;

public class MynameIs {
	public void test(hello a) {

	}

	public static void main(String[] args) {
		MynameIs a = new MynameIs();
		a.test(new hello() {// An unnamed type that implements the hello
							// interface is made. So,It is of type hello. It
							// implements the methods that hello has.The
							// advantage of this is you dont need to create a
							// new class just for this reason if you dont need
							// to.

			@Override
			public void billa() {
				// TODO Auto-generated method stub

			}
		});
	}
}
