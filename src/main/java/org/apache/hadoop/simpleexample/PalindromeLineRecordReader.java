package org.apache.hadoop.simpleexample;

import java.io.IOException;

import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

public class PalindromeLineRecordReader extends LineRecordReader {
  
  @Override
  public boolean nextKeyValue() throws IOException {
    boolean nextKeyValue;
    do {
      nextKeyValue = super.nextKeyValue();
    } while(nextKeyValue && (super.getCurrentValue().toString().length() == 1));
    
    return nextKeyValue;
  }
  
}
