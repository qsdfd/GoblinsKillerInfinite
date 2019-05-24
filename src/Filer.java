import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Filer {
	
	private static final String BOT_PATH = "C:\\Users\\kaisu\\Desktop\\Bot.txt";
	private static final String MEMBER_PATH = "C:\\Users\\kaisu\\Desktop\\Member.txt";
	
	public Filer(){}
	
	public void writeMember(String login){
		try {
			FileReader fr = new FileReader(new File(MEMBER_PATH));
			BufferedReader br = new BufferedReader(fr);
			
			String alogin;
			ArrayList<String> logins = new ArrayList<>();
			for(int i = 0 ; (alogin = br.readLine()) != null; i++)
				logins.add(alogin);
			
			br.close();
			fr.close();
			
			if(!logins.contains(login)){
				FileWriter fw = new FileWriter(new File(MEMBER_PATH),true);
				PrintWriter pw = new PrintWriter(fw);
				
				pw.println(login);
				
				pw.close();
				fw.close();
			}
		} catch (IOException e) {System.out.println(e.getMessage());}
	}
	
	public void write(boolean check,int world,String acc){
		try {
			FileWriter fw = new FileWriter(new File(BOT_PATH));
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(check);
			pw.println(world);
			pw.println(acc);
			
			pw.close();
			fw.close();
		} catch (IOException e) {System.out.println(e.getMessage());}
	}
	
	public String[] read(){
		String[] values = new String[3];
		try {
			FileReader fr = new FileReader(new File(BOT_PATH));
			BufferedReader br = new BufferedReader(fr);
			
			String str;
			for(int i = 0 ; (str = br.readLine()) != null; i++)
				values[i] = str;
			
			br.close();
			fr.close();
		} catch (IOException e) {System.out.println(e.getMessage());}
		
		return values;
	}
	
}