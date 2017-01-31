import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class clustertocluster {

    public static void main(String[] args) throws Exception{

        String csvFile = "bat_cluster.csv";
        String csvFile1 = "bowl_cluster.csv";
        String line = "";
        String cvsSplitBy = ",";
	int rowLength=0;
	ArrayList<String> batsmanName = new ArrayList<String>();
	ArrayList<Integer> batsmanCluster = new ArrayList<Integer>();
	ArrayList<String> bowlerName = new ArrayList<String>();
	ArrayList<Integer> bowlerCluster = new ArrayList<Integer>();
		
	
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	    line = br.readLine();
            while ((line = br.readLine()) != null) {
		
                String[] batClusterrows = line.split(cvsSplitBy);
                batsmanName.add(batClusterrows[0]);
                batsmanCluster.add(Integer.parseInt(batClusterrows[1]));
                //System.out.println(bat.get(rowLength) + " : " + batsman.get(rowLength));
		rowLength++;
            //System.out.println(rowLength);
            }
            }
            int rowLength1=0;
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile1))) {
	    line = br.readLine();
            while ((line = br.readLine()) != null) {
		
                String[] bowlClusterrows = line.split(cvsSplitBy);
                bowlerName.add(bowlClusterrows[0]);
                bowlerCluster.add(Integer.parseInt(bowlClusterrows[1]));
                //System.out.println(bowl.get(rowLength1) + " : " + bowler.get(rowLength1));
		rowLength1++;
            }
            //System.out.println(rowLength1);
            }
            
            
            
	String csvFile2 = "results5.csv";  
	String striker="";
	String nonStriker="";
	String bowlerBowling="";
	double [][] cluster1withothers=new double[5][7];
	double [][] cluster2withothers=new double[5][7];
	double [][] cluster3withothers=new double[5][7];
	double [][] cluster4withothers=new double[5][7];
	double [][] cluster5withothers=new double[5][7];
	
	int[] noOfotherClusterValues1=new int[5];
	int[] noOfotherClusterValues2=new int[5];
	int[] noOfotherClusterValues3=new int[5];
	int[] noOfotherClusterValues4=new int[5];
	int[] noOfotherClusterValues5=new int[5];
	
	//File file = new File("Output_Cluster.txt");
	//bw = new BufferedWriter(file);
	
	StringBuilder sb = new StringBuilder();
	PrintWriter pw = new PrintWriter(new File("results4.csv"));
	
	int rowLength2=0;
	int i=0,j=0;
	int call=0;
	try (BufferedReader br1 = new BufferedReader(new FileReader(csvFile2))) {
            while ((line = br1.readLine()) != null) {
		call++;
                String[] probability = line.split(cvsSplitBy);
                striker=probability[0];
                //nonStriker=Double.parseDouble(probability[4];
                bowlerBowling=probability[1];
                int index1=batsmanName.indexOf(striker);
		int index2=bowlerName.indexOf(bowlerBowling);
		
                if(index1>=0 && index2>=0)
                {
                int c1=batsmanCluster.get(index1);
                int c2=bowlerCluster.get(index2);
                //int c3=batsman.get(bat.indexOf(nonStriker));
                if(c1==1)
                {
                	
                	cluster1withothers[c2][0]=cluster1withothers[c2][0]+Double.parseDouble(probability[2]);
                	cluster1withothers[c2][1]=cluster1withothers[c2][1]+Double.parseDouble(probability[3]);
                	cluster1withothers[c2][2]=cluster1withothers[c2][2]+Double.parseDouble(probability[4]);
                	cluster1withothers[c2][3]=cluster1withothers[c2][3]+Double.parseDouble(probability[5]);
                	cluster1withothers[c2][4]=cluster1withothers[c2][4]+Double.parseDouble(probability[6]);
                	cluster1withothers[c2][5]=cluster1withothers[c2][5]+Double.parseDouble(probability[7]);
                	cluster1withothers[c2][6]=cluster1withothers[c2][6]+Double.parseDouble(probability[8]);
                	
                	noOfotherClusterValues1[c2]=noOfotherClusterValues1[c2]+1;
                	
                	
                	}
                	
                 if(c1==2)
                {
                	cluster2withothers[c2][0]=cluster2withothers[c2][0]+Double.parseDouble(probability[2]);
                	cluster2withothers[c2][1]=cluster2withothers[c2][1]+Double.parseDouble(probability[3]);
                	cluster2withothers[c2][2]=cluster2withothers[c2][2]+Double.parseDouble(probability[4]);
                	cluster2withothers[c2][3]=cluster2withothers[c2][3]+Double.parseDouble(probability[5]);
                	cluster2withothers[c2][4]=cluster2withothers[c2][4]+Double.parseDouble(probability[6]);
                	cluster2withothers[c2][5]=cluster2withothers[c2][5]+Double.parseDouble(probability[7]);
                	cluster2withothers[c2][6]=cluster2withothers[c2][6]+Double.parseDouble(probability[8]);
                	
                	noOfotherClusterValues2[c2]=noOfotherClusterValues2[c2]+1;
                	
                	
                	}
               
                if(c1==3)
                {
                	cluster3withothers[c2][0]=cluster3withothers[c2][0]+Double.parseDouble(probability[2]);
                	cluster3withothers[c2][1]=cluster3withothers[c2][1]+Double.parseDouble(probability[3]);
                	cluster3withothers[c2][2]=cluster3withothers[c2][2]+Double.parseDouble(probability[4]);
                	cluster3withothers[c2][3]=cluster3withothers[c2][3]+Double.parseDouble(probability[5]);
                	cluster3withothers[c2][4]=cluster3withothers[c2][4]+Double.parseDouble(probability[6]);
                	cluster3withothers[c2][5]=cluster3withothers[c2][5]+Double.parseDouble(probability[7]);
                	cluster3withothers[c2][6]=cluster3withothers[c2][6]+Double.parseDouble(probability[8]);
                	
                	
                	noOfotherClusterValues3[c2]=noOfotherClusterValues3[c2]+1;
                	
                	
                	} 
                	
                if(c1==4)
                {
                	cluster4withothers[c2][0]=cluster4withothers[c2][0]+Double.parseDouble(probability[2]);
                	cluster4withothers[c2][1]=cluster4withothers[c2][1]+Double.parseDouble(probability[3]);
                	cluster4withothers[c2][2]=cluster4withothers[c2][2]+Double.parseDouble(probability[4]);
                	cluster4withothers[c2][3]=cluster4withothers[c2][3]+Double.parseDouble(probability[5]);
                	cluster4withothers[c2][4]=cluster4withothers[c2][4]+Double.parseDouble(probability[6]);
                	cluster4withothers[c2][5]=cluster4withothers[c2][5]+Double.parseDouble(probability[7]);
                	cluster4withothers[c2][6]=cluster4withothers[c2][6]+Double.parseDouble(probability[8]);
                	
                	
                	noOfotherClusterValues4[c2]=noOfotherClusterValues4[c2]+1;
                	
                	
                	} 
                	
                	
                if(c1==0)
                {
                	cluster5withothers[c2][0]=cluster5withothers[c2][0]+Double.parseDouble(probability[2]);
                	cluster5withothers[c2][1]=cluster5withothers[c2][1]+Double.parseDouble(probability[3]);
                	cluster5withothers[c2][2]=cluster5withothers[c2][2]+Double.parseDouble(probability[4]);
                	cluster5withothers[c2][3]=cluster5withothers[c2][3]+Double.parseDouble(probability[5]);
                	cluster5withothers[c2][4]=cluster5withothers[c2][4]+Double.parseDouble(probability[6]);
                	cluster5withothers[c2][5]=cluster5withothers[c2][5]+Double.parseDouble(probability[7]);
                	cluster5withothers[c2][6]=cluster5withothers[c2][6]+Double.parseDouble(probability[8]);
                	
                	
                	
                	noOfotherClusterValues5[c2]=noOfotherClusterValues5[c2]+1;
                	
                	
                	} 
                	
                		
            //System.out.println(call);
		}
		}
		for(i=0;i<5;i++)
		{
			sb.append("1"+","+""+i+",");
			for(j=0;j<7;j++)
			{	
				if(j!=6)
				//System.out.print(cluster1withothers[i][j]/noOfotherClusterValues1[i]+",");
				sb.append(cluster1withothers[i][j]/noOfotherClusterValues1[i]+",");
				else
				//System.out.print(cluster1withothers[i][j]/noOfotherClusterValues1[i]);
				sb.append(cluster1withothers[i][j]/noOfotherClusterValues1[i]);
			}
			sb.append("\n");
			//System.out.println(":"+noOfotherClusterValues1[i]+" ");
		}
		for(i=0;i<5;i++)
		{
			//System.out.print("type"+(i+1)+" :");
			sb.append("2"+","+""+i+",");
			for(j=0;j<7;j++)
			{
				if(j!=6)
				//System.out.print(cluster1withothers[i][j]/noOfotherClusterValues1[i]+",");
				sb.append(cluster2withothers[i][j]/noOfotherClusterValues2[i]+",");
				else
				//System.out.print(cluster1withothers[i][j]/noOfotherClusterValues1[i]);
				sb.append(cluster2withothers[i][j]/noOfotherClusterValues2[i]);
			}
			sb.append("\n");
			//System.out.println(":"+noOfotherClusterValues2[i]+" ");
		}
		for(i=0;i<5;i++)
		{
			sb.append("3"+","+""+i+",");
			for(j=0;j<7;j++)
			{
				if(j!=6)
				//System.out.print(cluster1withothers[i][j]/noOfotherClusterValues1[i]+",");
				sb.append(cluster3withothers[i][j]/noOfotherClusterValues3[i]+",");
				else
				//System.out.print(cluster1withothers[i][j]/noOfotherClusterValues1[i]);
				sb.append(cluster3withothers[i][j]/noOfotherClusterValues3[i]);
			}
			sb.append("\n");
			//System.out.println(":"+noOfotherClusterValues3[i]+" ");
		}
		for(i=0;i<5;i++)
		{
			sb.append("4"+","+""+i+",");
			for(j=0;j<7;j++)
			{
				if(j!=6)
				//System.out.print(cluster1withothers[i][j]/noOfotherClusterValues1[i]+",");
				sb.append(cluster4withothers[i][j]/noOfotherClusterValues4[i]+",");
				else
				//System.out.print(cluster1withothers[i][j]/noOfotherClusterValues1[i]);
				sb.append(cluster4withothers[i][j]/noOfotherClusterValues4[i]);
			}
			sb.append("\n");
			//System.out.println(":"+noOfotherClusterValues4[i]+" ");
		}
		for(i=0;i<5;i++)
		{
			sb.append("0"+","+""+i+",");
			for(j=0;j<7;j++)
			{
				if(j!=6)
				//System.out.print(cluster1withothers[i][j]/noOfotherClusterValues1[i]+",");
				sb.append(cluster5withothers[i][j]/noOfotherClusterValues5[i]+",");
				else
				//System.out.print(cluster1withothers[i][j]/noOfotherClusterValues1[i]);
				sb.append(cluster5withothers[i][j]/noOfotherClusterValues5[i]);
			}
			sb.append("\n");
			//System.out.println(":"+noOfotherClusterValues5[i]+" ");
		}
            
            pw.write(sb.toString());
            pw.close();
 	           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
