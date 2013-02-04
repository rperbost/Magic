package binder;

public interface Binder {
	public void addCard(Card card);
	public boolean contains(Card card);
	public boolean contains(String name);
	public Card get(int i);
	public Card get(String name);
	public Card get(String set, String id);
	public Card getByRef(String ref);
	public int size();
}
