package com.kos.horses.structures;

import org.junit.Assert;
import org.junit.Test;

public class CoordTest {

    @Test
    public void transpose() {
        Assert.assertEquals( new Coord(0,0),new Coord(0,0).transpose());
        Assert.assertEquals( new Coord(10,45),new Coord(45,10).transpose());
        Assert.assertEquals( new Coord(200,200),new Coord(200,200).transpose());
        Assert.assertEquals( new Coord(-70,1975),new Coord(1975,-70).transpose());
    }

    @Test
    public void constructWithStep(){
        Coord coord= new Coord(678,190);
        Step step = new Step(20,4009);

        Coord stepCoord = new Coord(coord,step);

        Assert.assertEquals( new Coord(698,4199),stepCoord);
    }
}