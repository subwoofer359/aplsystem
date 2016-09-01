package org.amc.servlet

import org.junit.Before;
import org.junit.Test;

class MaterialControllerAddTest {
    MaterialSearchController controller;
    
    @Before
    void setup() {
        controller = new MaterialSearchController();
        controller.init();
    }
    
    @Test
    void addMaterialTest() {
        String view = controller.addMaterial();
        assert view == MaterialSearchController.ITEM_VIEW;
    }
}
