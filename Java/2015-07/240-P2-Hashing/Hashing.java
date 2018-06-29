/*
 * Project 2: Hashing, Rachel Chiang, CS 240-E01
 * 
 * This class generates hash tables and takes keys, creates hash codes with the key, and
 * compresses the hash code so that it will fit in the hash table. It also has put(String,
 * double) and addEntry(int, String, double, int), which insert new Entries to the hash
 * table. Resize(int) and transfer(Entry[]) are used to increase the size of the hash
 * table by taking all the old Entries, re-compressing their hash values, and placing
 * them into a new table. The last four methods, printTableInfo(), findMinAvg(), 
 * findMaxAvg(), and printKeyInfo(String), are used to display information about the
 * hash table and its Entries. Finally, there is a nested class Entry, which are used
 * as what is saved in the hash table. As a singly linked list, the table thus uses
 * separate chaining to deal with potential collisions. 
 */
class Hashing {
	/*
	 * The static integer INITIAL_CAPACITY is set to 43, which is the initial size
	 * of the hash table.
	 */
	private static int INITIAL_CAPACITY = 43;
	
	/*
	 * The size integer represents the number of individual Entries in the hash table.
	 * The threshold is like the 'maximum' capacity of Entries that the hash table can
	 * contain before resizing. Collisions keeps track of how many collisions occur
	 * (how many linked chains there are).
	 */
	private int size, threshold, collisions;
	
	/*
	 * The loadFactor is set to 75%. It is used to determine the threshold. When the hash
	 * table is filled 75%, it will resize.
	 */
	private static double LOAD_FACTOR = 0.75;
	
	/*
	 * This is the declaration of a new array full of Entries, initialized to length 41.
	 */
	private Entry[] table = new Entry[INITIAL_CAPACITY];
	
	/*
	 * The size is initialized to zero and the threshold to the table's length times .75.
	 */
	public Hashing() {
		size = 0;
		threshold = (int)(table.length * LOAD_FACTOR);
	}
	
	/*
	 * This method generates the hash code, which is dependent on the passed String.
	 * Each character is represented by a Unicode value which, if it is at an even
	 * index, it will use polynomial hashing with a=33 to create the hash code. If
	 * it is an odd index, it will use a=37.
	 */
	public int hashValue(String s) {
		int hash = 0;
		int n = s.length(), a;
		for (int i = 0; i < n; i++) {
			if (i%2==0) { // if even index
				a = 33;
			}
			else { // if odd index
				a = 37;
			}
			hash = a * hash + s.charAt(i);
		}
		return hash;
	}
	
	/*
	 * This method returns an integer value which will represent the index of a key
	 * for the hash table. It simply ensures that the index is a positive integer
	 * value [0, table.size). The arguments accepted are the hash value and the
	 * capacity of the table.
	 */
	public int compress(int hash, int capacity) {
		return Math.abs(hash%capacity);
	}
	
	/*
	 * This method evaluates how it should insert a passed key with its score into the
	 * hash table. If it does not exist in the hash table already, it will call
	 * addEntry(int, String, double, int); otherwise it will increase the already existing
	 * score and number of scores.
	 */
	public void put(String key, double score) {
		int h = hashValue(key);
		int bucketIdx = compress(h, table.length);
		boolean contains = false;
		for (Entry e = table[bucketIdx]; e!=null; e=e.next) {
			// goes to the correct bucket in the table and traverses the linked list
			// until it reaches null, but also checks if the key/hash values are already
			// in the table
			if (e.hashValue == h && key.equals(e.key)) {
				// if the key is already in the table, it will increase the sumOfScores and
				// numOfScores accordingly
				e.sumOfScores += score;
				e.numOfScores++;
				// If the key and hash value are already inside of the table, contains will
				// be set to true
				contains = true;
			}
		}
		// if the key and hash value are not in the bucket and
		if (!(contains)) {
			// and it will add a new entry to the head of the linked list
			addEntry(h, key, score, bucketIdx);
		}
	}
	
	/*
	 * This method adds a new entry to the linked list, storing the hash value, the key,
	 * the score, and the next (the old head, meaning the new one is added as the head of
	 * the singly linked list). It also increases the size of the table, and if the size
	 * is greater than or equal to the threshold of the hash table, the hash table will
	 * resize.
	 */
	public void addEntry(int hash, String key, double score, int bucketIndex) {
		table[bucketIndex] = new Entry(hash, key, score, table[bucketIndex]);
		size++;
		if (size >= threshold) {
			resize(2*table.length);
		}
	}

	/*
	 * This method resizes the table. A new table that is an array of Entries is created, and
	 * then the transfer(Entry[]) method is called, and the original table is set to the newly
	 * created table. The threshold is reset to appropriate amount.
	 */
	public void resize(int newSize) {
		Entry[] newTable = new Entry[newSize];
		table = transfer(newTable);
		threshold = (int)(newSize * LOAD_FACTOR);
	}
	
	/*
	 * This method returns a new table. It takes all of the old values from
	 * the original table, re-compresses it to generate indexes for the new,
	 * larger table, and inserts the Entries into the new table with those new
	 * indexes.
	 */
	public Entry[] transfer(Entry[] newTable) {
		Entry[] src = table;
		int newCapacity = newTable.length;
		for (int j = 0; j < src.length; j++) {
			Entry e = src[j];
			if (e != null) {
				src[j] = null;
				do {
					Entry next = e.next;
					int i = compress(e.hashValue, newCapacity);
					e.next = newTable[i];
					newTable[i] = e;
					e = next;
				} while (e != null);
			}
		}
		return newTable;
	}
	
	/*
	 * This method counts the number of collisions in the table. It traverses the table, and where
	 * there exists at least one Entry, it checks to see if there are more, and for each extra
	 * Entry in the bucket, it increments the collision up by one.
	 */
	public void countCollisions() {
		for (int i = 0; i < table.length; i++) {
			Entry e = table[i];
			if (e != null) {
				while (e.next != null) {
					e = e.next;
					collisions++;
				}
			}
		}
	}
	
	/*
	 * This method prints out information regarding the hash table: the number of collisions,
	 * the size of the table, and the number of different names in the table.
	 */
	public void printTableInfo() {
		countCollisions();
		System.out.println("# of collisions: " + collisions);
		System.out.println("Size of table:   " + table.length + "\n");
		System.out.println("# of names:      " + size);
	}
	
	/*
	 * This method determines the minimum average score of the Entries in the hash table
	 * and prints the results to the screen; that is, it prints the minimum score
	 * and the keys that had such score.
	 */
	public void findMinAvg() {
		Entry min = null; // Entry has to be initialized
		boolean initialized = false;
		for (int i=0; i<table.length; i++) {
			Entry e = table[i];
			if (e != null) {
				if (!(initialized)) { // Wanted to actually set it to something
					// I decided to create a copy of the Entry e rather than simply pointing to the Entry
					// because it became more convenient to add a new link with the same average or throw
					// out the whole list if a smaller average was found.
					min = new Entry(e.hashValue, e.key, e.sumOfScores, null); 
					min.numOfScores = e.numOfScores;
					initialized = true;
				}
				while (e != null) {
					// checks if e is less than the currently stored min
					if (e.sumOfScores/e.numOfScores < min.sumOfScores/min.numOfScores) {
						min = new Entry(e.hashValue, e.key, e.sumOfScores, null);
						min.numOfScores = e.numOfScores;
					}
					// Also checks if it is equal to and prevents duplication
					else if (e.sumOfScores/e.numOfScores == min.sumOfScores/min.numOfScores
							&& !(e.key.equals(min.key)) && e.hashValue != min.hashValue) {
						Entry minNext = new Entry(e.hashValue, e.key, e.sumOfScores/e.numOfScores, min);
						minNext.numOfScores = e.numOfScores;
						min = minNext;
					}
					e = e.next;
				}
			}
		}
		
		System.out.printf("Minimum average: %.3f\n", min.sumOfScores/min.numOfScores);
		while (min != null) {
			System.out.println("   " + min.key);
			min = min.next;
		}
	}
	
	/*
	 * This method determines the maximum average score of the Entries in the hash table
	 * and prints the results to the screen; that is, it prints the maximum score
	 * and the keys that had such score. It's essentially the same thing as findMinAvg()
	 * except with different field names and checks for greater than rather than less than.
	 */
	public void findMaxAvg() {
		Entry max = null;
		boolean initialized = false;
		for (int i=0; i<table.length; i++) {
			Entry e = table[i];
			if (e != null) {
				if (!(initialized)) {
					max = new Entry(e.hashValue, e.key, e.sumOfScores, null);
					max.numOfScores = e.numOfScores;
					initialized = true;
				}
				while (e != null) {
					if (e.sumOfScores/e.numOfScores > max.sumOfScores/max.numOfScores) {
						max = new Entry(e.hashValue, e.key, e.sumOfScores, null);
						max.numOfScores = e.numOfScores;
					}
					else if (e.sumOfScores/e.numOfScores == max.sumOfScores/max.numOfScores
							&& !(e.key.equals(max.key)) && e.hashValue != max.hashValue) { // prevents duplicates
						Entry maxNext = new Entry(e.hashValue, e.key, e.sumOfScores/e.numOfScores, max);
						maxNext.numOfScores = e.numOfScores;
						max = maxNext;
					}
					e = e.next;
				}
			}
		}
		
		System.out.printf("Maximum average: %.3f\n", max.sumOfScores/max.numOfScores);
		while (max != null) {
			System.out.println("   " + max.key);
			max = max.next;
		}
	}
	
	/*
	 * This method prints the name, average score, and number of scores of the given
	 * key (name).
	 */
	public void printKeyInfo(String key) {
		int h = hashValue(key);
		int bucketIdx = compress(h, table.length);
		boolean found = false;
		Entry e = table[bucketIdx];
		// The !found condition is actually not super necessary, but there's no point in
		// continuing the loop if the Entry was found.
		while (e != null && !found) {
			if (e.hashValue==h && key.equals(e.key)) {
				System.out.println("   Name: " + e.key + "   Avg: " + e.sumOfScores/e.numOfScores +
						"   # Scores: " + e.numOfScores);
				found = true;
			}
			e = e.next;
		}
		if (!(found)) {
			System.out.println("   " + key + " not found.");
		}
	}
	
	
	/*
	 * This class represents the Entries that are stored in the hash table. The key, hashValue,
	 * sumOfScores, numOfScores, and next are saved.
	 */
	class Entry {
		/*
		 * The key is the name. The hashValue is the integer that the key is hashed into, which
		 * can be compressed to provide an index value. The sumOfScores and numOfScores keep
		 * track of the scores and number of times the key has been passed in. The next field
		 * holds the next Entry in the linked list.
		 */
		String key;
		int hashValue;
		double sumOfScores=0;
		int numOfScores=0;
		Entry next;
		
		/*
		 * This method initializes the values, next, key, sumOfScores, and the hashValue, and
		 * it increments the numOfScores up by one. It is only called when making a new Entry;
		 * that is, it is only called if the key has not existed in the hash table yet.
		 */
		Entry(int h, String k, double score, Entry n) {
			next = n;
			key = k;
			sumOfScores = score;
			numOfScores++;
			hashValue = h;
		}
		
	}
	
}
