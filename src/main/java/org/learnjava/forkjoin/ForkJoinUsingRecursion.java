package org.learnjava.forkjoin;

import org.learnjava.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static org.learnjava.util.CommonUtil.delay;
import static org.learnjava.util.CommonUtil.stopWatch;
import static org.learnjava.util.LoggerUtil.log;

public class ForkJoinUsingRecursion extends RecursiveTask<List<String>> {
    private List<String> inputList;

    private ForkJoinUsingRecursion(List<String> inputList) {
        this.inputList = inputList;
    }

    public static void main(String[] args) {

        stopWatch.start();
        List<String> resultList = new ArrayList<>();
        List<String> names = DataSet.namesList();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinUsingRecursion forkJoinUsingRecursion = new ForkJoinUsingRecursion(names);
        resultList=forkJoinPool.invoke(forkJoinUsingRecursion);
        log("names : " + names);


        stopWatch.stop();
        log("Final Result : " + resultList);
        log("Total Time Taken : " + stopWatch.getTime());
    }


    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length() + " - " + name;
    }

    @Override
    protected List<String> compute() {
        if(inputList.size()<=1){
            List<String> resultList=new ArrayList<>();
            inputList.forEach(name->resultList.add(addNameLengthTransform(name)));
            return resultList;
        }
        int midpoint = inputList.size() / 2;
        ForkJoinTask<List<String>> leftInputList = new ForkJoinUsingRecursion(inputList.subList(0, midpoint)).fork();
        inputList=inputList.subList(midpoint,inputList.size());
        List<String> rightResult=compute(); // this is where recursion happens
        List<String> leftResult=leftInputList.join();
        leftResult.addAll(rightResult);
        return leftResult;
    }
}
