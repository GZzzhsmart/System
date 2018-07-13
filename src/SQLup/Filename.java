package SQLup;

import java.io.File;
import java.util.Date;
import com.oreilly.servlet.multipart.FileRenamePolicy;
public class Filename implements FileRenamePolicy{
	public File rename(File file) {
		String filename="";
		String filehouzui="";
		int port = file.getName().lastIndexOf(".");
		if(port!=-1){
			filehouzui = file.getName().substring(port);
			filename = new Date().getTime()+"";
		}else {
			filename = (new Date()).getTime()+"";
			filehouzui="";
		}
		String filerealname = filename+filehouzui;
		file = new File(file.getParent(),filerealname);
		return file;
	}
}
