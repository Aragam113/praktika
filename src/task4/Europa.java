package task4;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Europa {
    private List<HistoricalDivision> divisions;

    public Europa() {
        this.divisions = new ArrayList<>();
    }
    public void addDivision(HistoricalDivision division) {
        divisions.add(division);
    }
    public void printDivisions() {
        if (divisions.isEmpty()) {
            System.out.println("Нет изменений в территориальном делении.");
        } else {
            for (HistoricalDivision division : divisions) {
                System.out.println(division);
            }
        }
    }
    public static class HistoricalDivision {
        private String countryName;
        private int startYear;
        private int endYear;
        public HistoricalDivision(String countryName, int startYear, int endYear) {
            this.countryName = countryName;
            this.startYear = startYear;
            this.endYear = endYear;
        }
        public String getCountryName() {
            return countryName;
        }
        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }
        public int getStartYear() {
            return startYear;
        }
        public void setStartYear(int startYear) {
            this.startYear = startYear;
        }
        public int getEndYear() {
            return endYear;
        }
        public void setEndYear(int endYear) {
            this.endYear = endYear;
        }
        @Override
        public String toString() {
            return "Государство: " + countryName + ", Начало существования: " + startYear +
                    ", Конец существования: " + (endYear == -1 ? "настоящее время" : endYear);
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HistoricalDivision that = (HistoricalDivision) o;
            return startYear == that.startYear && endYear == that.endYear &&
                    Objects.equals(countryName, that.countryName);
        }
        @Override
        public int hashCode() {
            return Objects.hash(countryName, startYear, endYear);
        }
    }
}
