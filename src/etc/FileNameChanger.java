package etc;

import java.io.*; //입출력

public class FileNameChanger {
	public static void main(String[] args) {
		changeFiles();
	}// end main
	
	public static void changeFiles(){
		// 폴더 참조
		String pathInput = "C:\\Users\\packe\\git\\PS\\input";
		File dir = new File(pathInput); // 절대경로
		if (dir.exists())
		{
			// 폴더의 내용물 확인 -> 폴더 & 파일..
			File[] result = dir.listFiles(); // 내용 목록 반환
			System.out.println("--- START ---");
			for (int i = 0; i < result.length; i++)
			{
				if (result[i].isFile())
				{
					String oldName = result[i].getName();
					System.out.println(oldName);
//					File file = new File(oldName);
					String newName = pathInput + "\\" + oldName.split("_")[1];
					System.out.println(newName);
					File file2 = new File(newName);// 변경
					result[i].renameTo(file2); // 변경
				}
			}
			System.out.println("--- END ---");
		}
	}// end m6()
}