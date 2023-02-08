import java.util.Scanner;

class DoubleHeadIntStack{
	private int[] stk; //스택의 본체
	private int capacity; //스택 용량(A B의 합계)
	private int ptrA; //스택포인터A
	private int ptrB; //스택포인터B
	
	public enum AorB {StackA, StackB};
	
	//실행 시 예외 : 스택이 비어있음
	public class EmptyDoubleHeadIntStackException extends RuntimeException{
		public EmptyDoubleHeadIntStackException() {}
	}
	
	//실행 시 예외 : 스택이 가득 참
	public class OverflowDoubleHeadIntStackException extends RuntimeException{
		public OverflowDoubleHeadIntStackException () {}
	}
	
	//생성자 
	public DoubleHeadIntStack(int maxlen) {
		ptrA = 0;
		ptrB = maxlen - 1;
		capacity = maxlen;
		try {
			stk = new int[capacity];
			//스택 본체용 배열을 생성
		} catch(OutOfMemoryError e) {
			//생성할 수 없음
			capacity = 0;
		}
	}
	
	//스택에 x를 푸시
	public int push(AorB sw, int x) throws OverflowDoubleHeadIntStackException{
		if(ptrA >= ptrB + 1)
			//스택이 가득 참
			throw new OverflowDoubleHeadIntStackException();
		switch (sw){
			case StackA: stk[ptrA++] = x; break;
			case StackB: stk[ptrB--] = x; break;
		}
		return x;
	}
	
	//스택에서 데이터를 팝
	public int pop(AorB sw) throws EmptyDoubleHeadIntStackException{
		int x = 0;
		switch(sw) {
		case StackA:
			if(ptrA <= 0)
				//스택 A가 비어 있음
				throw new EmptyDoubleHeadIntStackException();
			x = stk[--ptrA];
			break;
		case StackB:
			if(ptrB >= capacity - 1)
				//스택 B가 비어 있음
				throw new EmptyDoubleHeadIntStackException();
			x = stk[++ptrB];
			break;
		}
		return x;
	}
	//스택에서 데이터를 피크(꼭대기의 데이터를 들여다 봄)
	public int peek(AorB sw) throws EmptyDoubleHeadIntStackException{
		int x = 0;
		switch(sw) {
		case StackA:
			if(ptrA <= 0)
				//스택 A가 비어있음
				throw new EmptyDoubleHeadIntStackException();
			x = stk[ptrA -1];
			break;
		case StackB:
			if (ptrB >= capacity - 1)
				//스택 B가 비어있음
				throw new EmptyDoubleHeadIntStackException();
			x = stk[ptrB + 1];
			break;
		}
		return x;
	}
	
	//스택에서 x를 검색하여 인덱스(발견하지 못하면 -1)를 반환합니다.
	public int indexOf(AorB sw, int x) {
		switch(sw) {
		case StackA:
			for(int i = ptrA -1; i >= 0; i--)
				//꼭대기에서 선형 검색
				if(stk[i] == x)
					return i;
			//검색 성공
			break;
		case StackB:
			for(int i = ptrB + 1; i < capacity; i++)
				//꼭대기 쪽에서 선형검색
				if(stk[i] == x)
					return i;
			//검색 성공
			break;
		}
		return -1;
		//검색 실패
	}
	
	//스택을 비웁니다
	public void clear(AorB sw) {
		switch (sw) {
		case StackA: ptrA = 0; break;
		case StackB: ptrB = capacity - 1; break;
		}
	}
	
	//스택 용량을 반환합니다.(A와 B의 합계)
	public int getCapacity() {
		return capacity;
	}
	
	//스택 쌓여있는 데이터수를 반환합니다
	public int size(AorB sw) {
		switch(sw) {
		case StackA: return ptrA;
		case StackB: return capacity - ptrB - 1;
		}
		return 0;
	}
	
	//스택이 비어있는가?
	public boolean isEmpty(AorB sw) {
		switch(sw) {
		case StackA: return ptrA <= 0;
		case StackB: return ptrB >= capacity - 1;
		}
		return true;
	}
	
	//스택이 가득 찼는가?
	public boolean isFull() {
		return ptrA >= ptrB + 1;
	}
	
	//스택 안의 모든 데이터를 바닥 -> 꼭대기의 순서로 출력
	public void dump(AorB sw) {
		switch(sw) {
		case StackA:
			if(ptrA <= 0)
				System.out.println("스택이 비어 있습니다.");
			else {
				for(int i=0; i < ptrA; i++)
					System.out.print(stk[i] + " ");
				System.out.println();
			}
			break;
		case StackB:
			if(ptrB >=capacity - 1)
				System.out.println("스택이 비어 있습니다.");
			else {
				for(int i=capacity - 1; i > ptrB; i--)
					System.out.print(stk[i] + " ");
				System.out.println();
			}
			break;
		}
	}
	
}



public class DoubleHeadIntStackTester {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		DoubleHeadIntStack s = new DoubleHeadIntStack(64);
		//최대 64개 푸시할 수 있는 머리가 둘인 스택
		
		while(true) {
			System.out.println("현재 데이터 개수 : "+"A : " + s.size(DoubleHeadIntStack.AorB.StackA) + "/" + "B : " + s.size(DoubleHeadIntStack.AorB.StackB));
			System.out.println("( 1) A 푸시 ( 2) A팝 ( 3) A 피크 ( 4) A덤프 ( 5) A 검색 ( 6) A 비움\n"+
								"( 7) B푸시 ( 8) B팝 ( 9) B 피크 ( 10) B 덤프 ( 11) B 검색 ( 12) B 비움 ( 13) 출력 ( 0) 종료 :");
			
			int menu = stdIn.nextInt();
			if(menu == 0) break;
			
			int n, x = 0;
			switch (menu) {
			case 1:
				// A에 푸시
				System.out.print("데이터 : ");
				x = stdIn.nextInt();
				try {
					s.push(DoubleHeadIntStack.AorB.StackA, x);
				} catch(DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
					System.out.println("스택이 가득 찼습니다.");
				}
				break;
				
			case 2:
				// A에서 팝
				try {
					x = s.pop(DoubleHeadIntStack.AorB.StackA);
					System.out.println("팝한 데이터는 " + x + "입니다.");
				} catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
					System.out.println("스택이 비어 있습니다.");
				}
				break;
				
			case 3:
				// A에서 피크
				try {
					x = s.peek(DoubleHeadIntStack.AorB.StackA);
					System.out.println("피크한 데이터는 " + x + "입니다.");
				}catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
					System.out.println("스택이 비어 있습니다.");
				}
				
			case 4:
				// A를 덤프
				s.dump(DoubleHeadIntStack.AorB.StackA);
				break;

			case 5:
				// A에서 검색
				System.out.print("검색할 데이터 : ");
				x = stdIn.nextInt();
				n = s.indexOf(DoubleHeadIntStack.AorB.StackA, x);
				if(n >= 0)
					System.out.println("꼭대기에서 " + (s.size(DoubleHeadIntStack.AorB.StackA) - n) + "번쨰에 있습니다.");
				else
					System.out.println("그 데이터가 없습니다.");
				break;
				
			case 6:
				//비웁니다
				s.clear(DoubleHeadIntStack.AorB.StackA);
				break;
				
			case 7:
				//B에 푸시
				System.out.print("데이터 : ");
				x = stdIn.nextInt();
				try {
					s.push(DoubleHeadIntStack.AorB.StackB, x);
				}catch(DoubleHeadIntStack.OverflowDoubleHeadIntStackException e) {
					System.out.println("스택이 가득 찼습니다.");
				}
				break;
				
			case 8:
				//B에서 팝
				try {
					x = s.pop(DoubleHeadIntStack.AorB.StackB);
					System.out.println("팝한 데이터는 " + x + "입니다.");
				} catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
					System.out.println("스택이 비어 있습니다");
				}
				break;
				
			case 9:
				//B에서 피크
				try {
					x = s.peek(DoubleHeadIntStack.AorB.StackB);
				} catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
					System.out.println("스택이 비어 있습니다");
				}
				break;
				
			case 10:
				//B를 덤프
				s.dump(DoubleHeadIntStack.AorB.StackB);
				break;
				
			case 11:
				//B에서 검색
				System.out.print("검색할 데이터 : ");
				x = stdIn.nextInt();
				n = s.indexOf(DoubleHeadIntStack.AorB.StackB, x);
				if(n >= 0)
					System.out.println("꼭대기에서 " + (s.size(DoubleHeadIntStack.AorB.StackB) - (s.getCapacity() - n) + 1) + "번째에 있습니다.");
				else
					System.out.println("그 데이터가 없습니다.");
				break;
				
			case 12:
				//비웁니다
				s.clear(DoubleHeadIntStack.AorB.StackB);
				break;
				
			case 13:
				// 데이터 출력
				System.out.println("용량 : " + s.getCapacity());
				System.out.println("A의 데이터수 : " + s.size(DoubleHeadIntStack.AorB.StackA));
				System.out.println("B의 데이터수 : " + s.size(DoubleHeadIntStack.AorB.StackB));
				System.out.println("A는 비어 " + (s.isEmpty(DoubleHeadIntStack.AorB.StackA) ? "있습니다." : "있지 않습니다."));
				System.out.println("B는 비어 " + (s.isEmpty(DoubleHeadIntStack.AorB.StackB) ? "있습니다." : "있지 않습니다."));
				System.out.println("가득 차 " + (s.isFull() ? "있습니다." : "있지 않습니다."));
				break;
			}
			
		}
	}

}
