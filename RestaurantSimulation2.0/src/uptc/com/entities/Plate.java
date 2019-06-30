package uptc.com.entities;

public class Plate {
    private int idProduct;
    private String productName;
    private double ratingProbability;

    public Plate(int id, String name, double ratingProbability) {
        this.idProduct = id;
        this.productName = name;
        this.ratingProbability = ratingProbability;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int id) {
        this.idProduct = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setName(String productName) {
        this.productName = productName;
    }

    public double getRatingProbability() {
        return ratingProbability;
    }

    public void setRatingProbability(double ratingProbability) {
        this.ratingProbability = ratingProbability;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + idProduct +
                ", name='" + productName + '\'' +
                ", ratingProbability=" + ratingProbability +
                '}';
    }
}