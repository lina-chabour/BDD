import java.util.List;



public class DBDef {
	
	private static DBDef INSTANCE;
	private List<RelDef> definition;
	private int compteur;
	
	private DBDef() {
	}
	
	public static synchronized DBDef getInstance() {
		
		if(INSTANCE == null) {
			INSTANCE =  new DBDef();
			return INSTANCE;
		}
		else 
			return INSTANCE;
	}	
		
		public void init() {
			
		}
		
		public void finish() {
			
		}
		
		public void addRelation(RelDef a) {
			definition.add(a);
			this.compteur++;
		}
		
		
}
