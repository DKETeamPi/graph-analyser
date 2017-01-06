import java.util.Scanner;
import java.io.*;

public class CheckTheGraph
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("What graph do you want to see 1-20");
		int graph=in.nextInt();
		
		String fileName="graph\\block3_graph"+(graph<10 ? "0"+graph:graph)+".txt";
		Parameters pars=ReadGraph.read(new String[]{fileName});
		
		int n=pars.n;
		int m=pars.m;
		ColEdge[] e=pars.e;
		
		int[][] vertex =new int[n][n-1];
		int[] length=new int[n];
		for(int i=0;i<e.length;i++)
		{
			int a=e[i].u-1;
			int b=e[i].v-1;
			vertex[a][length[a]]=b;
			vertex[b][length[b]]=a;
			length[a]++;
			length[b]++;
		}
		for(int i=0;i<length.length;i++)
		{
			System.out.println((i+1)+": "+length[i]);
		}
		try 
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName.replace(".txt","Degrees.txt").replace("graph","graphDegree")));
            for (int i=0;i<length.length;i++) 
            {
                out.write((i+1)+": "+length[i] + "\n");
            }
            out.close();
        } 
        catch (IOException ex) 
        {
        	System.out.println("file not found");
        }
        System.out.println("number of verticies ="+n);
        System.out.println("number of edges ="+m);
        while(true)
		{
			System.out.println("What vertex's connection do you want to see");
			int index=in.nextInt()-1;
			System.out.print((index+1)+" is connected to: {"+(vertex[index][0]+1));
			for(int i=1;i<length[index];i++)
			{
				System.out.print(", "+(vertex[index][i]+1));
			}
			System.out.println("}");
		}
	}
}
class Parameters
{
	public Parameters(int n,int m,ColEdge[] e)
	{
		this.n=n;
		this.m=m;
		this.e=e;
	}
	int n;
	int m;
	ColEdge[] e;
}