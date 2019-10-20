import java.util.ArrayList;
import java.util.List;

import sun.tools.tree.ThisExpression;

public class DBManager {
	private static DBManager INSTANCE;
	private List<String> types = new ArrayList<String>();

	private DBManager() {

	}

	public static synchronized DBManager getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new DBManager();
			return INSTANCE;
		} else
			return INSTANCE;
	}

	public void init() {
		DBDef.getInstance().init();
	}

	public void finish() {
		DBDef.getInstance().finish();
	}

	public void ProcessCommand(String chaine) {
		String[] ch = chaine.split(" ");
		types = null;
		for (int i = 3, j = 0; i < ch.length; i++, j++) {
			types.add(j, ch[i]);
		}

		CreateRelation(ch[1], Integer.parseInt(ch[2]), types);

	}

	public static void CreateRelation(String nom, int nbcol, List<String> types) {
		RelDef reldef = new RelDef(nom, nbcol, types);
		DBDef.getInstance().addRelation(reldef);
		DBDef.getInstan
	}
}