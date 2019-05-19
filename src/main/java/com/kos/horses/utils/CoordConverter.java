package com.kos.horses.utils;

import com.kos.horses.structures.Coord;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

public class CoordConverter {
    @NonNull
    public static Coord exelCoordToBoardCoord(@NonNull String exelCoord) throws ConvertException {
        String coordText = exelCoord.toLowerCase();


        ReadValueResult valueX = readTextIndex(0, coordText, 'a', 'z');
        if (valueX.position == 0)
            throw new ConvertException("Error coordinate x");

        ReadValueResult valueY = readDigitIndex(valueX.position, coordText);
        if (valueY.position != coordText.length() || valueY.position == valueX.position)
            throw new ConvertException("Error coordinate y");

        return new Coord(valueX.value, valueY.value);
    }

    public static ReadValueResult readDigitIndex(int position, String text) throws ConvertException {
        long resultValue = 0;

        for (; position < text.length(); position++) {
            char c = text.charAt(position);
            if (c >= '0' && c <= '9') {
                resultValue = resultValue * 10 + (c - '0');
                if (resultValue < 0)
                    throw new ConvertException("Error coordinate value " + resultValue);
            } else {
                break;
            }
        }

        return new ReadValueResult(position, resultValue);
    }

    public static ReadValueResult readTextIndex(int position, String text, char start, char end) throws ConvertException {
        Assert.isTrue(start <= end, "start should be <= end");

        long resultValue = 0;
        int radix = end - start + 1;
        long multiplication = 1;
        long digitValue = 0;
        for (; position < text.length(); position++) {
            char c = text.charAt(position);
            if (c >= start && c <= end) {
                resultValue = resultValue * radix + (c - start);
                if (resultValue < 0L)
                    throw new ConvertException("Error coordinate value " + resultValue);

                digitValue += multiplication;
                multiplication *= radix;
            } else {
                break;
            }
        }

        resultValue += digitValue;
        if (resultValue < 0L)
            throw new ConvertException("Error coordinate value " + resultValue);
        return new ReadValueResult(position, resultValue);
    }

    public static class ReadValueResult {
        private final int position;
        private final long value;

        public ReadValueResult(int position, long value) {
            this.position = position;
            this.value = value;
        }

        public int getPosition() {
            return position;
        }

        public long getValue() {
            return value;
        }
    }
}
