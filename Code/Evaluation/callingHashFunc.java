public class callingHashFunc {
	
	public int HashFunc(String key){
		
		String S = key;
		int n = S.length();
		char c;
		int hash = 0;
		for(int i =0; i< n; i++)
		{
		 c = S.charAt(i);
		 //System.out.println(c);
		 hash = hash + (int)c;
		 //System.out.print(hash);
		}
		//System.out.println("HAshvalue:"+hash);
		return hash;
	}

}
