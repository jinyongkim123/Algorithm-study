import java.util.*;
public class SearchIdx {

	static int searchIdx(int[] a, int n, int key, int[] Idx) {
		
		//배열 a의 앞쪽 n개의 요소에서 key와 일치하는 모든 요소의 인덱스를
		//배열 idx의 앞쪽부터 순서대로 저장하고 일치하는 요솟수를 반환합니다
		
		int j=0;
		//key와 일치하는 요솟수
		for(int i=0; i<n; i++) {
			if(key==a[i]) {
				Idx[j]=i; 
				j++;
			}
		}
		
		return j;
	}
	
	
	public static void main(String[] args) {

		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("요솟 수: ");
		int num = stdIn.nextInt();
		int[] x = new int[num];
		//요솟수가 num 인 배열
		int[] y = new int[num];
		//요솟수가 num 인 배열
		
		System.out.println("입력하시오");
		
		for(int i=0; i<num; i++) {
			System.out.print("x["+ i +"]: ");
			x[i] = stdIn.nextInt();
		}
		
		System.out.print("키 값 입력: "); //키값 입력받음
	
		int ky = stdIn.nextInt();
		
		int total = searchIdx(x, num, ky, y);//배열 x안의 값이 ky인 요소를 y에 copy
		
		if(total == 0) {
			System.out.println("그 값의 요소는 존재하지 않습니다.");
		}
		else {
			for(int i=0; i<total; i++) {
				System.out.println("그 값은" + "x["+y[i]+"] 에 있습니다.");
			}
		}
		
		
		
		
	}

}
