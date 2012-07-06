/**
 * Simple MapReduce program to count Palindrome words
 */
package org.apache.hadoop.simpleexample;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class PalindromeCount {  
  
  public static void main(String[] args) 
      throws IOException, InterruptedException, ClassNotFoundException {
    Configuration conf = new Configuration();
    Job job = new Job(conf, "palindromecount");
    
    job.setMapperClass(PalindromeMapper.class);
    job.setReducerClass(PalindromeReducer.class);
    
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);
    
    job.setInputFormatClass(PalindromeTextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);
    
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    job.setJarByClass(PalindromeCount.class);
    job.waitForCompletion(true);
    
  }
}
