package com.kos.horses.controllers;

import java.util.ArrayList;
import java.util.List;

import static com.kos.horses.structures.Board.MAX_BOARD_SIZE;

public class HorseControllerTestUtils {

    public String api() {
       return  "";
    }

   public String createQuery(long width,long height,String start,String end){
        return api()+"?width="+width+"&height="+height+"&start="+start+"&end="+end;
    }

    public static final List<QueryParamsTest> successArgumentList = new ArrayList<>();
    public static final List<QueryParamsTest> outboundArgumentList = new ArrayList<>();
    public static final List<QueryParamsTest> errorSizeArgumentList = new ArrayList<>();

    static {
        successArgumentList.add(new QueryParamsTest(1, 1, "a1", "a1", "0"));
        successArgumentList.add(new QueryParamsTest(3, 3, "a1", "b2", "-1"));
        successArgumentList.add(new QueryParamsTest(30, 30, "c6", "f3", "2"));
        successArgumentList.add(new QueryParamsTest(30, 8, "c6", "f3", "2"));
        successArgumentList.add(new QueryParamsTest(1000000000000000L, 1000000000000000L, "Bssssssssss155555555555", "daaaaaa950000000000056", "474922222222251"));


        outboundArgumentList.add(new QueryParamsTest(1, 1, "c1", "a1", "-1"));
        outboundArgumentList.add(new QueryParamsTest(5, 8, "c1", "f1", "-1"));
        outboundArgumentList.add(new QueryParamsTest(5, 8, "c1", "d10", "-1"));
        outboundArgumentList.add(new QueryParamsTest(5, 8, "c1", "d0", "-1"));
        outboundArgumentList.add(new QueryParamsTest(5, 8, "ad2","c1",  "-1"));
        outboundArgumentList.add(new QueryParamsTest(5, 8, "d10","c1",  "-1"));
        outboundArgumentList.add(new QueryParamsTest(5, 8, "d0", "c1", "-1"));
        outboundArgumentList.add(new QueryParamsTest(5, 8, "d10","d10",  "-1"));
        outboundArgumentList.add(new QueryParamsTest(5, 8, "h2","h2",  "-1"));

        errorSizeArgumentList.add(new QueryParamsTest(MAX_BOARD_SIZE+2, 10, "c1", "a1", "-1"));
        errorSizeArgumentList.add(new QueryParamsTest(6,MAX_BOARD_SIZE+1,  "c1", "a1", "-1"));
        errorSizeArgumentList.add(new QueryParamsTest(MAX_BOARD_SIZE+6,MAX_BOARD_SIZE+1,  "c1", "a1", "-1"));
        errorSizeArgumentList.add(new QueryParamsTest(-10,12,  "c1", "a1", "-1"));
        errorSizeArgumentList.add(new QueryParamsTest(10,-12,  "c1", "a1", "-1"));
        errorSizeArgumentList.add(new QueryParamsTest(-10,-12,  "c1", "a1", "-1"));

        errorSizeArgumentList.add(new QueryParamsTest(10,12,  "cavfgfgdgdgdenvn1", "a1", "-1"));
    }



    public static class QueryParamsTest{
        public final long width;
        public final long height;
        public final String start;
        public final String end;
        public final String result;

        public QueryParamsTest(long width, long height, String start, String end, String result) {
            this.width = width;
            this.height = height;
            this.start = start;
            this.end = end;
            this.result = result;
        }
    }

}
