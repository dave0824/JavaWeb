package test;

class A {
	void f(){
		System.out.println(1);
	}
}

class B extends A {
	A t;
	
	/**
	 * @Title: B
	 * @Description: TODO
	 * @param: @param t
	 * @throws
	 */
	public B(A t) {
		super();
		this.t = t;
	}

	void f(){
		t.f();
	}
}

class C extends B{

	public C(A t) {
		super(t);
	}
	void f(){
		super.f();
		System.out.println(2);
	}
	
}

class D extends C{

	public D(A t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	void f(){
		super.f();
		System.out.println(3);
	}
}

public class test {
	public static void main(String[] args) {
		A t = new A();
		D t2 = new D(t);
		t2.f();
		
	}
} 