package org.learnjava.parallelstreams;

import org.junit.jupiter.api.Test;
import org.learnjava.util.CommonUtil;
import org.learnjava.util.DataSet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.learnjava.util.CommonUtil.startTimer;
import static org.learnjava.util.CommonUtil.timeTaken;

class ParallelStreamsExampleTest {
    ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

    @Test
    void stringTransform() {
        //given
        List<String> inputList = DataSet.namesList();

        //when
        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransform(inputList);
        timeTaken();

        //then
        assertEquals(4, resultList.size());
        resultList.forEach(name -> {
            assertTrue(name.contains("-"));
        });
    }

}