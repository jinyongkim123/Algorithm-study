import java.util.Arrays;
import java.util.Scanner;
public class StringBinarySearch {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		String[] x = {	//자바에서 사용하는 키워드(알파벳 순)
				"abstract",	"assert",	"boolean",	"break",	"byte",
				"case",		"catch",	"char",		"class",	"const"
		};
		
		System.out.print("원하는 키워드를 입력하세요: ");	//키값을 읽어 들임
		String ky = stdIn.next();
		
		int idx = Arrays.binarySearch(x, ky);		//배열 x에서 값이 ky인 요소를 검색
		
		if(idx < 0)
			System.out.println("해당 키워드가 없습니다.");
		else
			System.out.println("해당 키워드는 x["+ idx +"]에 있습니다.");
	}

}
