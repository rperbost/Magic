package binder;

public interface Binder {
	public boolean contains(Card card);
	public void addCard(Card card);
	public int size();
}
