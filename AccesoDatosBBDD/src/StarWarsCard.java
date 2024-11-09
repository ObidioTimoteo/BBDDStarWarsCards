
public class StarWarsCard {
	private String nameCard;
	private int numberCard;
	private String typeCard;
	
	public StarWarsCard(String nameCard, int numberCard, String typeCard) {
		this.nameCard = nameCard;
		this.numberCard = numberCard;
		this.typeCard = typeCard;
	}

	public String getNameCard() {
		return nameCard;
	}

	public void setNameCard(String nameCard) {
		this.nameCard = nameCard;
	}

	public int getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(int numberCard) {
		this.numberCard = numberCard;
	}

	public String getTypeCard() {
		return typeCard;
	}

	public void setTypeCard(String typeCard) {
		this.typeCard = typeCard;
	}

	@Override
	public String toString() {
		return "StarWarsCard [nameCard=" + nameCard + ", numberCard=" + numberCard
				+ ", typeCard=" + typeCard + "]";
	}
	
	
	
	
	
}
