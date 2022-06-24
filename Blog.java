import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Blog{
	private String blogName;
	private ArrayList<Post> postsList = new ArrayList<Post>();

	public Blog() throws Exception{this("MY BLOG");}
	public Blog(String blogName) throws Exception{this.blogName = blogName; startUp();}

	/*
	 * prints saved posts to the user
	 */
	public void listPosts() throws Exception{
		System.out.println("----------");

		if(postsList.size() > 0){
			System.out.printf(">> Your posts are : \n");

			int postNum = 0;
			for(Post post : postsList){
				String title = post.getTitle();
				String newLine = (postNum == postsList.size() -1 ) ? "" : "\n";
				System.out.printf("%d. %s%s", postNum++, title, newLine);
			}

			System.out.println("");
		}
		else{System.out.println(">> You hava no posts");}

		System.out.println("----------");
	}

	
	public void viewPost() throws Exception{
		listPosts();

		if(postsList.size() > 0){
			int chosenPost = promptUserToChoose("<< Choose a post : ");

			String title = postsList.get(chosenPost).getTitle();
			String content = postsList.get(chosenPost).getContent();

			String seperator = seperation(title, '-');
			String sep = seperation(title, '*');

			System.out.println(seperator);
			System.out.println(title);
			System.out.println(sep);
			System.out.print(content);
			System.out.println(seperator);
		}
	}

	public void addPost() throws Exception{
		int postNum = postsList.size();

		System.out.println("-----------------\nTyping a post ...\n*****************");
		String title = promptUserToInputLine("<< What is the title for your post : ");
		String content = promptUserToInput("<< What's the content of your post (/end) : \n", "/end");
		System.out.println("-----------------");

		Post newPost = new Post(postNum);
		newPost.setTitle(title);
		newPost.setContent(content);
		postsList.add(newPost);

		File postLocation = newPost.getPostLocation();
		writePostToFile(postLocation, title, content);
		System.out.println("-----------------");
	}


	// Support methodes

	/*
	 * starts the blog by 
	 * defining the folder where posts are saved
	 * encapsulates each post file in a post object
	 */
	public void startUp(){
		try{
			File postsDirectory = new File(".\\data");

			String[] listOfExistingPosts = postsDirectory.list();

			if(listOfExistingPosts.length > 0){
				for(String fileName : listOfExistingPosts){
					Post existingPost = new Post(fileName);
					postsList.add(existingPost);
					existingPost.storeTitle();
					existingPost.storeContent();
				}
			}
		}
		catch(Exception e){
			System.out.print("");
		}
	}

	/*
	 * some methos to prompt user for input
	 */


	public int promptUserToAct(){
		return promptUserToChoose("Please select an action : \n0. List Posts\n1. View a Post\n2. Add a Post\n3. Exit\n<< Enter a choice : ");
	}

	private int promptUserToChoose(String prompt){
		int choice = 0;

		Scanner input = new Scanner(System.in);

		System.out.printf(prompt);
		choice = input.nextInt();

		return choice;
	}

	private String promptUserToInputLine(String prompt){
		String userInput = "";

		Scanner input = new Scanner(System.in);

		System.out.printf(prompt);
		userInput = input.nextLine();

		return userInput;
	}

	private String promptUserToInput(String prompt, String endWord){
		String userInput = "";
		String lineInput = "";

		Scanner input = new Scanner(System.in);
		System.out.printf(prompt);

		String newLine = "";
		do{
			lineInput += newLine;
			userInput += lineInput;
			lineInput = input.nextLine();
			newLine = "\n";
		}while(!lineInput.equals(endWord));

		return userInput;
	}

	/*
	 * saves a post to a file
	 * encoded as follows
	 * 
	 * ******************
	 * **** post.txt ****
	 * ******************
	 * *   post title   *
	 * *  post content  * 
	 * ******************
	 * **** END file ****
	 * ******************
	 */
	private void writePostToFile(File postLocation, String title, String content){
		try{
			FileWriter fileWriter = new FileWriter(postLocation);
			fileWriter.write(title + "\n");
			fileWriter.write(content);
			fileWriter.close();

			System.out.println(">> Saved succesfully");
		}
		catch(Exception e){
			System.out.println(">> Error : Couldn't save post");
		}
	}

	/*
	 * a method returns a string of the one character of a givin length 
	 */
	private String seperation(String specialTitle, char seperationChar){
		String seperator = "";
		for(int i=0; i < specialTitle.length(); i++){seperator += seperationChar;}
		return seperator;
	}

	// Getters

	public String getBlogName(){return blogName;}

}