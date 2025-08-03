package jms;

public class Command {
    private String operator;
    private int value;

    // Default constructor needed for Jackson
    public Command() {}

    public Command(String operator, int value) {
        this.operator = operator;
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public int getValue() {
        return value;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
