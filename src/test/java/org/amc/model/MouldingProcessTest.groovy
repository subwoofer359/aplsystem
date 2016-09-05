package org.amc.model

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class MouldingProcessTest {

    @Test
    void temperatureTest() {
        MouldingProcess process = new MouldingProcess();
        process.barrelTemperature.barrelTemperature1 = 1;
        assert process.barrelTemperature.barrelTemperature1 == 1;
    }
    
    @Test
    void injectionTest() {
        MouldingProcess process = new MouldingProcess();
        process.injection.injectionSpeed1 = 12;
        assert process.injection.injectionSpeed1 == 12;
    }

}
