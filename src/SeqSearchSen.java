import java.util.Scanner;
public class SeqSearchSen {
	//요솟수가 n인 배열에서 key와 값이 같은 요소를 보초법으로 선형 검색
	static int seqSearchSen(int[] a, int n, int key) {
		int i=0;
		
		a[n] = key;					//보초를 추가
		
		while(true) {
			if(a[i]==key) {			//검색 성공
				break;
			}
			i++;
		}
		return i==n ? -1 : i;
	}
	
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		System.out.print("요솟수 :");
		int num = stdIn.nextInt();						//요솟수가 num+1 인 배열
		int[] x = new int[num+1];
		
		for(int i=0; i<num; i++) {
			System.out.print("x[" + i + "]: ");
			x[i] = stdIn.nextInt();
		}
		
		System.out.print("검색할 값: ");
		int ky = stdIn.nextInt();
		
		int idx = seqSearchSen(x, num, ky);
		
		if(idx == -1) {
			System.out.println("검색 값의 요소가 없습니다.");
		}
		else {
			System.out.println("검색 값은 x[" + idx + "]에 있습니다.");
		}
	}

}
