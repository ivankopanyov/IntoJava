package model.enums;

/**
 * Перечисление полов.
 */
public enum Gender {

    /**
     * Мужской пол.
     */
    male {
        @Override
        public String toString() {
            return "m";
        }
    },

    /**
     * Женский пол.
     */
    female {
        @Override
        public String toString() {
            return "f";
        }
    }
}
