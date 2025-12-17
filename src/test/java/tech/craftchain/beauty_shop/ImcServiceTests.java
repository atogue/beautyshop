package tech.craftchain.beauty_shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.craftchain.beauty_shop.service.ImcService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ImcServiceTests {
    @Autowired
    private ImcService imcService;

    @Test
    void determineImc_with_weight0() {
        assertEquals(0, imcService.calculateImc(0,1.60));
    }

    @Test
    void determineImc_with_weight_higher() {
        assertEquals(27.0, imcService.calculateImc(70,1.60));
    }

    @Test
    void determineImc_advise() {
        assertEquals("Sous poids", imcService.determineAdvise(1.60));
    }

    @Test
    void find_imc_by_weight_and_height() {
        assertEquals(Map.of(19.0, "Poids Normal"), imcService.findImcInfo(60, 1.80));
    }
}
