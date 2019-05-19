package com.kos.horses.problems.horses;

import com.kos.horses.utils.ConvertException;
import org.junit.Assert;
import org.junit.Test;

public class HorseProblemFactoryTest {

    @Test
    public void build() throws ConvertException {

        HorseProblemParams problemParams = HorseProblemFactory.build(56, 128, "b4", "c9");

        Assert.assertEquals(56, problemParams.getBoard().getWidth());
        Assert.assertEquals(128, problemParams.getBoard().getHeight());

        Assert.assertEquals(1, problemParams.getStart().getX());
        Assert.assertEquals(3, problemParams.getStart().getY());

        Assert.assertEquals(2, problemParams.getEnd().getX());
        Assert.assertEquals(8, problemParams.getEnd().getY());
    }

    @Test
    public void buildString() throws ConvertException {

        HorseProblemParams problemParams = HorseProblemFactory.build("56", "128", "b4", "c9");

        Assert.assertEquals(56, problemParams.getBoard().getWidth());
        Assert.assertEquals(128, problemParams.getBoard().getHeight());

        Assert.assertEquals(1, problemParams.getStart().getX());
        Assert.assertEquals(3, problemParams.getStart().getY());

        Assert.assertEquals(2, problemParams.getEnd().getX());
        Assert.assertEquals(8, problemParams.getEnd().getY());
    }
}