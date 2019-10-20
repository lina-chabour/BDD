import java.nio.ByteBuffer;

public class Frame implements Comparable<Frame> {
		private ByteBuffer buffer;
		private PageId pageId;
		private int pinCount;
		private int dirty;
		private long lastused;
		
		
		public Frame() {
			this.buffer=ByteBuffer.allocate(Constants.pageSize);
			this.pageId = null;
			this.pinCount=0;
			this.dirty=0;
			this.lastused=-1;
		}


		public ByteBuffer getBuffer() {
			return buffer;
		}


		public void setBuffer(ByteBuffer buffer) {
			this.buffer = buffer;
		}


		public PageId getPageId() {
			return pageId;
		}


		public void setPageId(PageId pageId) {
			this.pageId = pageId;
		}
	

		public int getDirty() {
			return dirty;
		}


		public void setDirty(int dirty) {
			if (dirty==1) this.dirty=1;	
		}


		public long getLastused() {
			return lastused;
		}


		public void setLastused(long lastused) {
			this.lastused = lastused;
		}


		public int getPinCount() {
			return pinCount;
		}
		
		
		public void incrementerPin() {
			this.pinCount++;
		}
		
		public void decrementerPin() {
			if (this.pinCount>0) this.pinCount--;
			if (this.pinCount==0) this.lastused=System.currentTimeMillis();
		}

		
		public int compareTo(Frame o) {
			if(o.getPageId()==this.pageId) return 0;
			else return 1;
		}
		
		
}
