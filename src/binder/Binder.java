package binder;

public interface Binder {
	public boolean contains(Card card);
	public void addCard(Card card);
	public int size();
	public Card get(int i);
	public Card get(String name);
	public boolean contains(String name);
}
