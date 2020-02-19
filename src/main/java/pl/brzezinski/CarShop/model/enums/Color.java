package pl.brzezinski.CarShop.model.enums;

public enum  Color {

    BLACK("black"),
    WHITE("white"),
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    YELLOW("yellow"),
    GREY("grey"),
    OTHER("other");

    private String description;

    Color(String description) {
        this.description = description;
    }
}
