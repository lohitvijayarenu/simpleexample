package org.apache.hadoop.simpleexample;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PalindromeMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  private static final IntWritable one = new IntWritable(1);
  private Text palindrome = new Text();
  
  public void map(LongWritable key, Text value, Context context) 
      throws InterruptedException, IOException {
    String line = value.toString();
    StringTokenizer tokenizer = new StringTokenizer(line);
    while (tokenizer.hasMoreTokens()) {
      String word = tokenizer.nextToken();
      String reverse = new StringBuffer(word).reverse().toString();
      if (word.equals(reverse)) {
        palindrome.set(word);
        context.write(palindrome, one);
      }
    }
  }
}
