package the_crypter_project;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.IOException;


public class The_Crypter_Project{
	
	static Scanner in = new Scanner (System.in);
//	static matrixInverter inv=new matrixInverter();	
	
	public static void main(String[]args) throws Exception{
		String Text = in.nextLine();
//		System.out.println(Text);
		int[] SpacesIndexes = SpaceFinder(Text); 
		int[] NumbredText = TextToNumber(Text);
		int[][] Key = KeyGenerator(NumbredText.length);
		int[] MultiplectedMatrix = MultiplectionOfMatrixes(NumbredText,Key);
		int[] ModedNumbers = Mode(MultiplectedMatrix);
		String RawText = NumbersToText(ModedNumbers);
//		System.out.println(RawText);
		FormatAndPrit(RawText,SpacesIndexes,Text.length());
	}
	
	public static int[] TextToNumber(String Text){
//		List <String> Numbers = new ArrayList<String>();
		int tempo;
		String SimpleText = SpaceDeleter(Text);                                                                                   
		int[] Numbers = new int[SimpleText.length()];
//		System.out.println(SimpleText);
		for (int i=0;i<SimpleText.length();i++) {
			tempo = SimpleText.charAt(i);
			if (97<tempo&&tempo<122)
				Numbers[i]=tempo-97;
			else
				Numbers[i]=tempo-65;
		}
//		System.out.println(Nubmers);
//		for (int i=0;i<SimpleText.length();i++)
//			System.out.print(Numbers[i]+" ");
		return Numbers;
	}
	
	public static int[] MultiplectionOfMatrixes(int[] NumberedText,int[][] Key){
		int[] MultiplectedMatrix=new int[NumberedText.length];
		for (int Row=0;Row<NumberedText.length;Row++)
			for (int Column=0;Column<NumberedText.length;Column++)
				MultiplectedMatrix[Row]=MultiplectedMatrix[Row]+(NumberedText[Column]*Key[Row][Column]);
//		for (int Row=0;Row<NumberedText.length;Row++)
//				System.out.print(MultiplectedMatrix[Row]+" ");
		return MultiplectedMatrix;
	}
	
	public static String NumbersToText(int[]InputNumbers){
		String RawCryptedText = new String();
		char tempo;
		for (int i=0;i<InputNumbers.length;i++){
			tempo= (char) (InputNumbers[i]+65);
			RawCryptedText = RawCryptedText + tempo;
		}
//		System.out.println(RawCryptedText);
		return RawCryptedText;
	}
	
	public static void FormatAndPrit(String RawText,int[] SpaceIndexes,int thelastchar){
		String Crypted="";
		int i=0, start=0, p=SpaceIndexes[i];
		try {
		 while(p!=0)
				{			 		
			 		if(SpaceIndexes[i]==0&&p>1)
					SpaceIndexes[i]=thelastchar;
			 		Crypted = Crypted + RawText.substring(start,SpaceIndexes[i]-i) + " ";
					start=SpaceIndexes[i]-i;
		 			p=SpaceIndexes[i];
					i++;
//					System.out.println(Crypted);
				}
		}catch(Exception e) {}
			System.out.println(Crypted);
		}
	
	public static int[][] KeyGenerator(int SideLength) throws Exception{
		File myObj = new File("filename.txt");
		FileWriter myWriter = new FileWriter("filename.txt");
		int[][] Key=new int[SideLength][SideLength];
		Random RandomNumber=new Random();
		for (int Row=0;Row<SideLength;Row++)
			for (int Column=0;Column<SideLength;Column++) {
				Key[Row][Column]=RandomNumber.nextInt(10);
				myWriter.write(Integer.toString(Key[Row][Column]));
			}
//		for (int Row=0;Row<SideLength;Row++) {
//			for (int Column=0;Column<SideLength;Column++)
//				System.out.print(Key[Row][Column]);
//			System.out.print("\n");
//		}
	      myWriter.close();
		return Key;
	}
	
	public static int[] Mode(int[] multiplectedMatrix){
		int[] ModedMatrix = new int[multiplectedMatrix.length];
		for (int i=0;i<multiplectedMatrix.length;i++)
			ModedMatrix[i]=multiplectedMatrix[i]%26;
//		for (int i=0;i<multiplectedMatrix.length;i++)
//			System.out.print(multiplectedMatrix[i]+" ");
		return ModedMatrix;
	}
	
 	public static String SpaceDeleter(String Text){
		for (int i=0;i<Text.length();i++){
			if (Text.charAt(i) == ' '){
				Text=Text.substring(0,i)+Text.substring(i+1);
			}
		}
		return Text;
	}
 	
 	public static int[] SpaceFinder(String Text){
 		int[] IndexesOfSpaces = new int[Text.length()];
 		int j=0;
		for (int i=0;i<Text.length();i++){
			if (Text.charAt(i) == ' '){
				IndexesOfSpaces[j]=i;
				j++;
			}
		}
//		for(int t=0;t<IndexesOfSpaces.length;t++)
//			System.out.println(IndexesOfSpaces[t]);
		return IndexesOfSpaces;
	}
 	
}