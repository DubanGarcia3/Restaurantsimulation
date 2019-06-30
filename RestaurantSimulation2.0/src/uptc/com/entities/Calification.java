package uptc.com.entities;

public class Calification {

    private int idConsumption;
    private int score;
    private Product product;

    public Calification() {
    }

    public Calification(int id, int score, Product product) {
        this.idConsumption = id;
        this.score = score;
        this.product = product;
    }
   
    public int getIdConsumption() {
		return idConsumption;
	}

	public void setIdConsumption(int idConsumption) {
		this.idConsumption = idConsumption;
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
                "id=" + idConsumption +
                ", rating=" + score +
                ", product=" + product +
                '}';
    }
}