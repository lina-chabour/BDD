import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;


public class BufferManager {
	private static BufferManager INSTANCE;
	private ArrayList<Frame> bufferpool;
	
	private BufferManager() {
		bufferpool = new ArrayList<Frame>();
		for (int i=0; i<Constants.frameCount ; i++)	{
			bufferpool.add(new Frame());
		}
	}
	
	public static synchronized BufferManager getInstance() {
		
		if(INSTANCE == null) {
			INSTANCE =  new BufferManager();
			return INSTANCE;
		}
		else 
			return INSTANCE;
	}	
	
	public ByteBuffer getPage(PageId pageId) throws IOException {
		
		for(int i=0; i<bufferpool.size();i++) {
			Frame fr = bufferpool.get(i);
			if (fr.getPageId() == null) continue;
			if (fr.getPageId().equals(pageId)) {
				fr.incrementerPin();
				return fr.getBuffer();
			}
		}
		
		for(int i=0; i<bufferpool.size();i++) { 
			Frame fr = bufferpool.get(i);
			if (fr.getPageId().equals(null)) {
			DiskManager.getInstance().ReadPage(pageId, fr.getBuffer());
			fr.incrementerPin();
			return fr.getBuffer();
			}
		}
		
		for(int i=0; i<bufferpool.size();i++) { 
			Frame fr = bufferpool.get(i);
			if (fr.getPinCount() == 0) {
				if(fr.getDirty()==1) {
					DiskManager.getInstance().Writepage(pageId, fr.getBuffer());
				}
			
				fr.setDirty(0) ;
			    DiskManager.getInstance().ReadPage(pageId, fr.getBuffer());
			    fr.incrementerPin();
			    return fr.getBuffer();	
			}
			
		}
		
		System.out.println("bug BM");
		return null;
		
	}
			
		

	public void freePage(PageId pageId, int valdirty) {
		for(int i=0; i<bufferpool.size();i++) {
			Frame fr = bufferpool.get(i);
			if (fr.getPageId() == null) continue;
			if (fr.getPageId().equals(pageId)) {
				fr.decrementerPin();
				fr.setDirty(valdirty);
			}
		}
	}
	
	
	public void flushAll() throws IOException {
		for(int i=0; i<bufferpool.size();i++) {
			Frame fr = bufferpool.get(i);
			if(fr.getDirty()==1) {
				DiskManager.getInstance().Writepage(fr.getPageId(), fr.getBuffer());
			}
			fr = new Frame();
		}
	}
	
	
}
