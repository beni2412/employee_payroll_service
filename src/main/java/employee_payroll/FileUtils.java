package employee_payroll;

import java.io.File;

public class FileUtils {
	public static boolean deleteFiles(File contentToDelete) {
		File[] allContents = contentToDelete.listFiles();
		if(allContents!=null) {
			for(File file: allContents) {
				deleteFiles(file);
			}
		}
		return contentToDelete.delete();
	}
}
