package tech.craftchain.beauty_shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ImcService {
    /**
     * calculate IMC value and determine the corresponding advise
     * @param weight weight of the patient in KG
     * @param height height of the patient in X.YY meters
     * @return a Map of IMC value as Key and advise as Value
     */
    public Map<Double, String> findImcInfo(double weight, double height) {
        var imc = calculateImc(weight, height);
        return Map.of(imc, determineAdvise(imc));
    }

    public double calculateImc(double weight, double height) {
        log.info("Calculate imc by weight: {} and height: {}", weight, height);
        return Math.round(weight / Math.pow(height, 2));
    }

    public String determineAdvise(double imc) {
        var advise = ImcEnum.getImcByValue(imc).getAdvise();
        log.info("Determine advise: [{}] by imc value : {}", advise, imc);
        return advise;
    }
}
enum ImcEnum {
    SOUS_POIDS("Sous poids", null, 18.4),
    POIDS_NORMAL("Poids Normal", 18.5, 24.9),
    SUR_POIDS("Sur poids", 25.0, 29.9),
    OBESITE_CLASS_I("Obésité Classe I", 30.0, 34.9),
    OBESITE_CLASS_II("Obésité Classe II", 35.0, 39.9),
    OBESITE_CLASS_III("Obésité Classe III", 40.0, null),;

    private final Double lowValue;
    private final Double highValue;
    private final String advise;
    ImcEnum(String advice, Double lowValue, Double highValue) {
        this.advise = advice;
        this.lowValue = lowValue;
        this.highValue = highValue;
    }
    String getAdvise() {
        return advise;
    }
    private Double lowValue() {
        return lowValue;
    }
    private Double highValue() {
        return highValue;
    }

    static ImcEnum getImcByValue(double value) {
        ImcEnum imcEnum = null;
        if (18.4 >= value) {
            imcEnum = ImcEnum.SOUS_POIDS;
        } else if (18.5 <= value && value <= 24.9) {
            imcEnum = ImcEnum.POIDS_NORMAL;
        } else if (25.0 <= value && value <= 29.9) {
            imcEnum = ImcEnum.SUR_POIDS;
        } else if (30.0 <= value && value <= 34.9) {
            imcEnum = ImcEnum.OBESITE_CLASS_I;
        } else if (35.0 <= value && value <= 39.9) {
            imcEnum = ImcEnum.OBESITE_CLASS_II;
        } else if (40.0 <= value) {
            imcEnum = ImcEnum.OBESITE_CLASS_III;
        }
        return imcEnum;
    }
}
