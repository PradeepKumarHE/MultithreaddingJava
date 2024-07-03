package org.learnjava.parallelstreams;

import org.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static org.learnjava.util.CommonUtil.*;
import static org.learnjava.util.LoggerUtil.log;

public class ParallelStreamsExample {
    public List<String> stringTransform(List<String> nameList){
        //return nameList.stream().map(this::addNameLengthTransform).collect(Collectors.toList());
        return nameList.parallelStream().map(this::addNameLengthTransform).collect(Collectors.toList());
    }
    public static void main(String[] args) {
        List<String> nameList=DataSet.namesList();
        ParallelStreamsExample parallelStreamsExample=new ParallelStreamsExample();
        startTimer();
        List<String> result=parallelStreamsExample.stringTransform(nameList);
        log("Result :: "+result);
        timeTaken();


    }
    private  String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}
