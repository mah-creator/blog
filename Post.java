import java.util.Scanner;
import java.io.File;

public class Post{
	private File file;
	private String title;
	private String content;
	
	public Post(int postNum){
		this(postNum + ".txt");
	}
	
	public Post(String fileName){
		String postLoacation = "./data/" + fileName;
		file = new File(postLoacation);
	}
	
	
	// Support methodes
	
	void storeTitle(){
		String postTitle = "";
		Scanner fileReader = null;
		try{
			fileReader = new Scanner(file);
			postTitle = fileReader.nextLine();
		}
		catch(Exception e){
			System.out.println("Error : Coudn't read file");
		}

		title = postTitle;
		fileReader.close();
	}
	
	void storeContent(){
		String postContent = "";
		Scanner fileReader = null;

		try{
			fileReader = new Scanner(file);
			String line = "";
			
			String enter = "";
			fileReader.nextLine();
			while(fileReader.hasNext()){
				postContent += enter;
				line = fileReader.nextLine();
				postContent += line;
				enter = "\n";
			}
			postContent += "\n";
		}
		catch(Exception e){
			System.out.println("Error : Coudn't read file");
		}

		fileReader.close();
		content = postContent;
	}

	
	// Getters
	
	File getPostLocation(){
		return file;
	}
	
	public String getTitle(){return title;}

	public String getContent(){return content;}
	
	// Setters
	
	void setTitle(String title){this.title = title;}
	void setContent(String content){this.content = content;}
	
}