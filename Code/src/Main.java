
import java.io.IOException;
import java.nio.ByteBuffer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			DBManager inst = DBManager.getInstance();
			ByteBuffer buf = ByteBuffer.allocate(Constants.pageSize);
			ByteBuffer buff = ByteBuffer.allocate(Constants.pageSize);
			buff.putChar('d');
			
			inst.init();
			DiskManager disk = DiskManager.getInstance();
		
			PageId i3= new PageId(3,2);
			
			BufferManager bm = BufferManager.getInstance();
			System.out.println(bm.getPage(i3));
			
			
			
			
		}
	
	}
