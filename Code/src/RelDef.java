import java.util.List;

public class RelDef {
	private String nom;
	private int nbcol;
	private List<String> type;
	int fileIdx;
	int recordSize;
	int slotCount;
	
	
	public RelDef(String nom, int nbcol, List<String> type,int fileIdx,int recordSize,int slotCount) {
		this.nom=nom;
		this.nbcol=nbcol;
		this.type=type;
		this.fileIdx=fileIdx;
		this.recordSize=recordSize;
		this.slotCount=slotCount;
		
	}


	public List<String> getType() {
		return type;
	}
	
	
	
	
	
	
}
