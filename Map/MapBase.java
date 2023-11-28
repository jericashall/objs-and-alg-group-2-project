package Map;
import java.util.ArrayList;
import java.util.Arrays;

public class MapBase<Key extends Comparable<Key>, Value>{
		@SuppressWarnings("hiding")
		public class Record<Key, Value>{
			public Key key;
			public Value value;
			
			public Record(Key key, Value value){
				this.key = key;
				this.value = value;
			}
		}		
		
		protected Record<Key,Value>[] table;
		protected int size;
		
		@SuppressWarnings("unchecked")
		public MapBase(int size, Record<Key,Value>... dummy) {
			this.size = size;
			table = Arrays.copyOf(dummy, size);
		}
		
		@SuppressWarnings("unchecked")
		public MapBase(Record<Key,Value>... dummy) {
			this.size = 191;
			table = Arrays.copyOf(dummy, size);
		}
		
		public Value get(Key key) {
			final int index = lookUp(key);
			if(index == -1) {
				return null;
			}
			
			Record<Key,Value> p = table[index];

			return p != null ? p.value : null;
		}
		
		public void put(Key key, Value value) {
			final int index = lookUp(key);
			if(index == -1) {
				throw new RuntimeException("Table is full");
			}
			
			Record<Key,Value> p = table[index];
			if (p == null) {
				table[index] = new Record<Key,Value>(key, value);
			} else {
				p.value = value;
			}
		}
		
		public void printTable() {			
			for(int i = 0; i < size; i++) {
				Record<Key,Value> p = table[i];
				if(table[i] != null) {
					System.out.println("index=" + i + " key=" + p.key + " value=" + p.value);
				}
			}
		}
		
		public ArrayList<Record<Key,Value>> getEntries() {
			ArrayList<Record<Key,Value>> entries = new ArrayList<>();
			for(Record<Key,Value> rec : table) {
				if(rec != null) {
					entries.add(rec);
				}
			}
			
			return entries;
		}
		
		protected static <Key> int hash(Key key) {
			int num = 0;
			if(key instanceof Integer) {
				num = (int) key;
			} else if(key instanceof String){
				String str = (String) key;
				for(int i = 0; i < str.length(); i++) {
					char c = str.charAt(0);
					num += (int) c;
				}
			}
			
			return (num>>8)|((num&0xff)<<16);
		}
		
		protected int hashIndex(int hashCode) {
			return hashCode%size;
		}

		protected int lookUp(Key key) {
			final int startIndex = hashIndex(hash(key));
			int i = startIndex;
			int offset = 1;
			
			while(table[i] != null && table[i].key.compareTo(key) != 0) {
				i += offset;
				offset += 2;
				i %= size;
				
				if(i == startIndex) {
					break;
				}
			}
			
			return i;
		}
}

