package Generic;

public class StorageImpl<T> implements Storage<T>{
	private T[] products;
	
	public StorageImpl(int capacity) {
		products = (T[]) (new Object[capacity]);
	}
	
	@Override
	public void add(T item, int index) {
		products[index] = item;
		System.out.println(index+"번에 성공적으로 추가되었습니다.");
		System.out.println(products[0]);
		System.out.println(products[1]);	// 디버깅 확인코드
	}

	@Override
	public T get(int index) {
		if(products[0] != null) {
			System.out.println("성공적으로 불러왔습니다.");
		}else {
			System.out.println("해당 항목이 존재하지 않습니다.");
		}
		return products[index];
	}
	

}
