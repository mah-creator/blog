public class Host{
	public static void main(String[] args) throws Exception{
		Blog blog = new Blog("Mahod's Blog");
		
		startHosting(blog);
		
		while(true){
			int actionChoice = blog.promptUserToAct();
			
			switch(actionChoice){
				case 0 : blog.listPosts(); break;
				case 1 : blog.viewPosts(); break;
				case 2 : blog.addPost(); break;
				case 3 : System.out.print("Good Bye"); System.exit(0); break;
				default : System.out.println(">> Wrong choice"); 
				
			}
		}
	}
	
	private static void startHosting(Blog blog){
		System.out.printf("<blog> Welcome to %s </blog>\n", blog.getBlogName());
	}
}