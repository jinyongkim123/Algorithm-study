import java.util.Scanner;
public class question3_2 {

	
	static int seqSearchEx3_2(int[] a, int n ,int key) {
		System.out.print("   |");
		for(int i=0; i<n; i++)
			System.out.print("   "+i);
		System.out.println();
		
		System.out.print("---+");
		for(int i=0; i<n*4+2; i++)
			System.out.print("-");
		System.out.println();
		
		for(int i=0; i<n; i++) {
			System.out.print("   |");
			System.out.printf(String.format("%%%ds*\n", (i*4)+3), ""); //https://annyeongworld.tistory.com/82 해석
			System.out.printf("%3d|",i);
			for(int k=0; k<n; k++) {
				System.out.printf("%4d",a[k]);
			}
			System.out.println("\n   |");
			
			if(a[i] == key)
				return i;
			
			}
		return -1;
}	
	
	
	public static void main(String[] args) {
		Scanner put = new Scanner(System.in);
		
		System.out.print("요솟수 : ");
		int num = put.nextInt();
		int[] id = new int[num];
	
		System.out.println("값 입력");
		for(int i=0; i<num;i++) {
			System.out.print("idx["+i+"] : ");
			id[i] = put.nextInt();
		}
		
		System.out.print("검색 값 : ");
		
		int ky = put.nextInt();
		
		int idx = seqSearchEx3_2(id, num, ky);
		
		if(idx == -1)
			System.out.println("그 값의 요소는 존재하지 않습니다.");
		else
			System.out.println("그 값은 x[" + idx + "]에 있습니다.");
		

	}
	
	
		
}


