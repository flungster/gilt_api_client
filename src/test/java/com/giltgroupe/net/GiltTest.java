package com.giltgroupe.net;

import com.giltgroupe.model.Gilt;
import com.giltgroupe.model.sale.Sale;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GiltTest {

    @Test public void testLoad() {
        Gilt gilt = GiltFetcher.getInstance();
        assert(gilt != null);
        gilt.load("https://api.gilt.com/v1/sales/women/active.json?apikey=5aa7344bd866dd8128b82bb868811442");
    

        Collection<Sale> activeSales = gilt.getActiveSales();
        for (Sale sale : activeSales) {
            assert (sale.getName() != "");
        }
    }

}