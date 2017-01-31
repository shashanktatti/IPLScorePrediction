import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class newone1 
{

	public static class MapperClass extends Mapper<LongWritable, Text, Text, Text> 
	{
 		public void map(LongWritable key, Text empRecord, Context con)throws IOException, InterruptedException 
		{
			String out = "";
  			String[] word = empRecord.toString().split(",");
  			if(word[0].equals("ball"))
			{	String batsmen = ""; 
				int runs = 0;
				
   				String bowler = "";		
				try 
				{	
					batsmen =word[4];
					runs = Integer.parseInt(word[7]);
					bowler = word[6]; 
					out = word[9];
					if(out.equals("\"\""))
					{
						out = "notout";
					}
				} 
				catch (Exception e) 
				{
   					runs=0;
  				}
				String runs_out=runs+","+out;
				batsmen = batsmen+","+bowler;	
   				con.write(new Text(batsmen), new Text(runs_out));
  			}
 		}
	}

	public static class ReducerClass extends Reducer<Text, Text, Text, Text> 
	{
 		public void reduce(Text key, Iterable<Text> valueList,Context con) throws IOException, InterruptedException 
		{
			String bowl="";
			double probtotal0=0;
			double probtotal1=0;
			double probtotal2=0;
			double probtotal3=0;
			double probtotal4=0;
			double probtotal6=0;
			float count=0;
	
			double ptotal0  = 0;
			double ptotal1  = 0;
			double ptotal2  = 0;
			double ptotal3  = 0;
			double ptotal4  = 0;
			double ptotal6  = 0;
			int totalout=0;
			double probtotalout=0;
			double sum=0;
	
  			try 
			{
				int total0 = 0;
				int total1 = 0;
				int total2 = 0;
				int total3 = 0;
				int total4 = 0;
				int total6 = 0;
				int totalo=0;
				int var1;
				String outruns[] = {};
				
	
				for ( Text var : valueList)
				{       
					count=count+1;
					outruns=var.toString().split(",");
					var1=Integer.parseInt(outruns[0]);
		
					switch(var1)
					{
						case 0 :
								if(outruns[1].equals("notout"))
								{
									total0++;
									
									break;
								}
								else
								{
									totalo++;
									
									break;
								}
								
						case 1 :
								if(outruns[1].equals("notout"))
								{
									total1++;
									
									break;								}
								else
								{
									totalo++;
									
									break;
								}
								
						case 2 :
								if(outruns[1].equals("notout"))
								{
									total2++;
									
									break;								
								}
								else
								{
									totalo++;
									
									break;
								}
								
						case 3 :
								if(outruns[1].equals("notout"))
								{
									total3++;
									
									break;
								}
								else
								{
									totalo++;
									
									break;
								}
								
						case 4 :
								if(outruns[1].equals("notout"))
								{
									total4++;
									
									break;
								}
								else
								{
									totalo++;
									
									break;
								}
							
						case 6 :
								if(outruns[1].equals("notout"))
								{
									total6++;
									
									break;
								}
								else
								{
									totalo++;
									
									break;
								}
								
					}
		
		
				}
			
				if(count!=0)
				{
					probtotal0 = (total0)/count;
					probtotal1 = (total1)/count;
					probtotal2 = (total2)/count;
					probtotal3 = (total3)/count;
					probtotal4 = (total4)/count;
					probtotal6 = (total6)/count;
					probtotalout = totalo/count;

					ptotal0=1-probtotal0;
					ptotal1=1-probtotal1;
					ptotal2=1-probtotal2;
					ptotal3=1-probtotal3;
					ptotal4=1-probtotal4;
					ptotal6=1-probtotal6;
					sum = probtotal0+probtotal1+probtotal2+probtotal3+probtotal4+probtotal6+probtotalout;
	
				}	
				String bat_bowl ="Total probability = "+sum;
				String probability=probtotal0+","+probtotal1+","+probtotal2+","+probtotal3+","+probtotal4+","+probtotal6+","+probtotalout;
				con.write(new Text(key),new Text(probability));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}	
 		}
	}

	public static void main(String[] args) 
	{
 		Configuration conf = new Configuration();
		conf.set("mapred.textoutputformat.separator", ",");
 		try 
		{
  			Job job = Job.getInstance(conf, "FindTotalruns");
  			job.setJarByClass(newone1.class);
			job.setMapperClass(MapperClass.class);
			job.setReducerClass(ReducerClass.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
  			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			System.exit(job.waitForCompletion(true) ? 0 : 1);
 		} 
		catch (IOException e) 
		{
  			e.printStackTrace();
 		} 
		catch (ClassNotFoundException e) 
		{
  			e.printStackTrace();
 		} 
		catch (InterruptedException e) 
		{
  			e.printStackTrace();
 		}
	}
}
