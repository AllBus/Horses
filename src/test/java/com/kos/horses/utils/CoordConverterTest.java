package com.kos.horses.utils;

import com.kos.horses.structures.Coord;
import org.junit.Assert;
import org.junit.Test;

public class CoordConverterTest {

    private static Pair<String, Coord> pair(String value, long x, long y) {
        return new Pair<>(value, new Coord(x, y));
    }

    @Test
    public void exelCoordToBoardCoord() throws ConvertException {

        Pair[] checkValues = new Pair[]{
                pair("a1", 1, 1),
                pair("aac615", 705, 615),
                pair("b300", 2, 300),
                pair("za9", 677, 9),
                pair("z111", 26, 111),
                pair("aa1001", 27, 1001)
        };

        for (Pair checkValue : checkValues) {
            Assert.assertEquals(checkValue.right, CoordConverter.exelCoordToBoardCoord((String) checkValue.left));
        }

    }

    @Test
    public void exelCoordToBoardCoordExcepted() {

        String[] checkValues = new String[]{
                "1a",
                "aac615!",
                "89b300",
                "za@9",
                "",
                "qqqqqqqqgggggqqqq7",
                "df777777777777777888888777",
                "s5s5",
                "45664",
                "sdfdfg",
                "1",
                "v",
                "f-2"
        };

        for (String checkValue : checkValues) {
            try {
                CoordConverter.exelCoordToBoardCoord(checkValue);
                Assert.fail("Test " + checkValue);
            } catch (ConvertException ignored) {

            }
        }

    }

    @Test
    public void readDigitIndexFromEmptyString() throws ConvertException {
        String text = "";
        CoordConverter.ReadValueResult result = CoordConverter.readDigitIndex(0, text);
        Assert.assertEquals(0, result.getValue());
        Assert.assertEquals(text.length(), result.getPosition());
    }

    @Test
    public void readDigitIndex() throws ConvertException {
        long[] values = {1009, 1016, 2345, 9874, 67532, 977432, 100000, 4564689, 324245464};

        for (int value = 0; value < 1000; value++) {
            String text = String.valueOf(value);
            CoordConverter.ReadValueResult result = CoordConverter.readDigitIndex(0, text);
            Assert.assertEquals(value, result.getValue());
            Assert.assertEquals(text.length(), result.getPosition());
        }

        for (long value : values) {
            String text = String.valueOf(value);
            CoordConverter.ReadValueResult result = CoordConverter.readDigitIndex(0, text);
            Assert.assertEquals(value, result.getValue());
            Assert.assertEquals(text.length(), result.getPosition());
        }
    }


    @Test
    public void readDigitIndexWithPrefix() throws ConvertException {
        long[] values = {1009, 1016, 2345, 9874, 67532, 977432, 100000, 4564689, 324245464};

        StringBuilder prefix = new StringBuilder("a");
        for (int value = 0; value < 1000; value++) {
            prefix.append("a");
            String text = prefix.toString() + String.valueOf(value);
            CoordConverter.ReadValueResult result = CoordConverter.readDigitIndex(value + 2, text);
            Assert.assertEquals(value, result.getValue());
            Assert.assertEquals(text.length(), result.getPosition());
        }

        for (long value : values) {
            String text = "prefix" + String.valueOf(value);
            CoordConverter.ReadValueResult result = CoordConverter.readDigitIndex(6, text);
            Assert.assertEquals(value, result.getValue());
            Assert.assertEquals(text.length(), result.getPosition());
        }
    }

    @Test
    public void readDigitIndexWithSuffix() throws ConvertException {
        long[] values = {1009, 1016, 2345, 9874, 67532, 977432, 100000, 4564689, 324245464};

        StringBuilder suffix = new StringBuilder("a");

        for (int value = 0; value < 1000; value++) {

            String text = String.valueOf(value);
            CoordConverter.ReadValueResult result = CoordConverter.readDigitIndex(0,
                    text + suffix.toString());
            Assert.assertEquals(value, result.getValue());
            Assert.assertEquals(text.length(), result.getPosition());
        }

        for (long value : values) {
            String text = String.valueOf(value) + "suffix";
            CoordConverter.ReadValueResult result = CoordConverter.readDigitIndex(0, text);
            Assert.assertEquals(value, result.getValue());
            Assert.assertEquals(text.length() - 6, result.getPosition());
        }
    }

    @Test
    public void readDigitIndexWithPrefixAndSuffix() throws ConvertException {
        long[] values = {1009, 1016, 2345, 9874, 67532, 977432, 100000, 4564689, 324245464};

        StringBuilder prefix = new StringBuilder("a");
        for (int value = 0; value < 1000; value++) {
            prefix.append("a");
            String text = prefix.toString() + String.valueOf(value) + prefix.toString();
            CoordConverter.ReadValueResult result = CoordConverter.readDigitIndex(prefix.length(), text);
            Assert.assertEquals(value, result.getValue());
            Assert.assertEquals(text.length() - prefix.length(), result.getPosition());
        }

        for (long value : values) {
            String text = "pref" + String.valueOf(value) + "suf";
            CoordConverter.ReadValueResult result = CoordConverter.readDigitIndex(4, text);
            Assert.assertEquals(value, result.getValue());
            Assert.assertEquals(text.length() - 3, result.getPosition());
        }
    }

    @Test
    public void readTextIndex() throws ConvertException {

        final int CURRENT_SIZE= 20;
        for (int i = 1; i <= 26; i++) {
            char start = 'a';
            char end = (char) (start + i);
            char[] current=new char[CURRENT_SIZE+1];
            int currentLength=1;
            for (int p=0;p<CURRENT_SIZE;p++){
                current[p] = (char) (start-1);
            }
            current[CURRENT_SIZE]='a';

            String text= String.valueOf(current,CURRENT_SIZE+1-currentLength,currentLength);
            CoordConverter.ReadValueResult result = CoordConverter.readTextIndex(
                    0, text,start,end);
            Assert.assertEquals("a",text);
            Assert.assertEquals(1, result.getValue());
            Assert.assertEquals(text.length(), result.getPosition());
            for (int value = 2; value < 1000000; value++) {

                if (current[CURRENT_SIZE]==end){
                    int p=0;
                    while (p<currentLength && (current[CURRENT_SIZE-p]==end)){
                        current[CURRENT_SIZE-p]='a';
                        p++;
                    }
                    current[CURRENT_SIZE-p]+=1;
                    if (p==currentLength)
                        currentLength+=1;

                }else
                    current[CURRENT_SIZE]+=1;

                text= String.valueOf(current,CURRENT_SIZE+1-currentLength,currentLength);

                result = CoordConverter.readTextIndex( 0, text,start,end);
                Assert.assertEquals(value, result.getValue());
                Assert.assertEquals(text.length(), result.getPosition());

            }
        }
    }
}