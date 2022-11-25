package peaksoft.enums;

public enum StudyFormat {
    ONLINE("Online FORMAT"),
    OFFLINE("Offline FORMAT");
    private final String displayValue;

    StudyFormat(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
