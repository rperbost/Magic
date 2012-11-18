package binder;

public class BoosterFactory extends RarityBinder {
	public BoosterFactory(Binder binder){
		
	}
	
	public Booster getBooster(){
		return new Booster();
	}
	
}
