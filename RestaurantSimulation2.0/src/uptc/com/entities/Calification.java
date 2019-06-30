package uptc.com.entities;

public class Calification {

    private int id;
    private int score;
    private Product product;

    public Calification() {
    }

    public Calification(int id, int score, Product product) {
        this.id = id;
        this.score = score;
        this.product = product;
    }
   
    public int getIdConsumption() {
		return id;
	}

	public void setIdConsumption(int idConsumption) {
		this.id = idConsumption;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", rating=" + score +
                ", product=" + product +
                '}';
    }
}