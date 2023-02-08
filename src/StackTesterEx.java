import java.util.*;

class Stack<E> {
	private E[] stk;
	private int capacity;
	private int ptr;

	// 실행시 예외 : 스택이 비어있음
	public static class EmptyGstackException extends RuntimeException {
		public EmptyGstackException() {
		}
	}

	// 실행시 예외 : 스택이 가득 참
	public static class OverflowGstackException extends RuntimeException {
		public OverflowGstackException() {
		}
	}

	// 생성자
	public Stack(int maxlen) {
		ptr = 0;
		capacity = maxlen;
		try {
			stk = (E[]) new Object[capacity];
			// 스택 본체용 배열
		} catch (OutOfMemoryError e) {
			// 생성할 수 없음
			capacity = 0;
		}
	}

	// 스택에 x를 푸시
	public E push(E x) throws OverflowGstackException {
		if (ptr >= capacity) // 스택이 가득 참
			throw new OverflowGstackException();
		return stk[ptr++] = x;
	}

	// 스택에 데이터를 팝!
	public E pop() throws EmptyGstackException {
		if (ptr <= 0) // 스택이 빔
			throw new EmptyGstackException();
		return stk[--ptr];
	}

	// 스택에서 데이터를 피크(peek, 꼭대기 데이터를 들여다 봄)
	public E peek() throws EmptyGstackException {
		if (ptr <= 0) // 스택이 빔
			throw new EmptyGstackException();
		return stk[ptr - 1];
	}

	// 스택을 비움
	public void clear() {
		ptr = 0;
	}

	// 스택에서 x를 찾아 인덱스(없으면 -1)를 반환
	public int indexOf(E x) {
		for (int i = ptr - 1; i >= 0; i--) // 꼭대기 쪽 부터 선형 검색
			if (stk[i].equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	// 스택의 크기를 반환
	public int getCapacity() {
		return capacity;
	}

	// 스택에 쌓여있는 데이터 갯수를 반환
	public int size() {
		return ptr;
	}

	// 스택이 비어있는가?
	public boolean isEmpty() {
		return ptr <= 0;
	}

	// 스택이 가득 찼는가?
	public boolean isFull() {
		return ptr >= capacity;
	}

	// 스택 안의 모든 데이터를 바닥 -> 꼭대기 순서로 표시
	public void dump() {
		if (ptr <= 0)
			System.out.println("스택이 비어있습니다.");
		else
			for (int i = 0; i < ptr; i++) {
				System.out.print(stk[i] + " ");
			}
		System.out.println();
	}

}

public class StackTesterEx {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		Stack<String> s = new Stack<String>(64); // 최대 64개를 푸시할 수 있는 스택

		while (true) {
			System.out.println();// 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)푸시 (2)팝 (3)피크 (4)덤프 (5)전체삭제 (6)위치검색 (7)데이터 갯수확인 (8)데이터 출력 (0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			String x;
			switch (menu) {
			case 1: // 푸시
				System.out.print("데이터: ");
				x = stdIn.next();
				try {
					s.push(x);
				} catch (IntStack.OverflowIntStackException e) {
					System.out.println("스택이 가득 찼습니다.");
				}
				break;

			case 2: // 팝
				try {
					x = s.pop();
					System.out.println("팝한 데이터는 " + x + "입니다.");
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다.");
				}
				break;

			case 3: // 피크
				try {
					x = s.peek();
					System.out.println("피크한 데이터는 " + x + "입니다.");
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다.");
				}
				break;

			case 4: // 덤프
				s.dump();
				break;

			case 5:
				s.clear();
				break;

			case 6:
				System.out.print("위치를 알고 싶은 요소: ");
				x = stdIn.next();
				if (s.indexOf(x) < 0)
					System.out.println("검색 실패!");
				else
					System.out.println("검색한 요소의 위치는 stk[" + s.indexOf(x) + "]에 있습니다.");
				break;

			case 7:
				System.out.println("데이터의 갯수는 " + s.size() + "개 입니다.");
				break;

			case 8:
				System.out.println("용량 : " + s.getCapacity());
				System.out.println("데이터수 : " + s.size());
				System.out.println("비어 " + (s.isEmpty() ? "있습니다." : "있지 않습니다."));
				System.out.println("가득차 " + (s.isFull() ? "있습니다." : "있지 않습니다."));
				break;
			}
		}
	}

}
