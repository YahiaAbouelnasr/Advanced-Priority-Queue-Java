
public class Tester {
	    public static void main(String[] args) {
	        AdvancedPQ<Integer, String> apq = new AdvancedPQ<>(true); // Start in Min-Heap mode

	        System.out.println("=== INSERTING ELEMENTS ===");
	        for (int i = 10; i >= 1; i--) {
	            apq.insert(i, "Val" + i);
	        }

	        System.out.println("Size after inserts: " + apq.size());
	        System.out.println("Top (should be min): " + apq.top()); // (1, Val1)

	        System.out.println("\n=== TOGGLING TO MAX HEAP ===");
	        apq.toggle();
	        System.out.println("Heap mode: " + apq.state());
	        System.out.println("Top (should be max): " + apq.top()); // (10, Val10)

	        System.out.println("\n=== INSERTING MORE ELEMENTS TO ALLOW RESIZE ===");
	        for (int i = 11; i <= 20; i++) {
	            apq.insert(i, "Val" + i);
	        }
	        System.out.println("Size after more inserts: " + apq.size());
	        System.out.println("Top: " + apq.top()); // Should still be max

	        System.out.println("\n=== REMVE TOP ===");
	        System.out.println("Removed: " + apq.removeTop());
	        System.out.println("New top: " + apq.top());

	        System.out.println("\n=== Testimng replaceKey & replaceValue ===");
	        AdvancedPQ.Entry<Integer, String> e = apq.insert(25, "Special");
	        System.out.println("Inserted: " + e);
	        System.out.println("Old key: " + apq.replaceKey(e, 5));
	        System.out.println("Old value: " + apq.replaceValue(e, "Updated"));
	        System.out.println("After changes: " + e);

	        System.out.println("\n=== Testing remove(Entry) ===");
	        AdvancedPQ.Entry<Integer, String> toRemove = apq.insert(100, "RemoveMe");
	        System.out.println("Before removal, contains: " + toRemove);
	        apq.remove(toRemove);
	        System.out.println("Removed entry. New top: " + apq.top());

	        System.out.println("\n=== Testing peekAt(n) ===");
	        try {
	            System.out.println("3rd entry in current heap order: " + apq.peekAt(3));
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }

	        System.out.println("\n=== TESTING TOGGLE BACK TO MIN-HEAP ===");
	        apq.toggle();
	        System.out.println("Heap mode: " + apq.state());
	        System.out.println("Top (should now be min): " + apq.top());

	        System.out.println("\n=== Testing isEmpty() ===");
	        System.out.println("Is empty? " + apq.isEmpty());

	        System.out.println("\n=== Testing merge() ===");
	        AdvancedPQ<Integer, String> other = new AdvancedPQ<>(true); // same mode
	        other.insert(1, "OtherOne");
	        other.insert(2, "OtherTwo");
	        System.out.println("Other queue size before merge: " + other.size());
	        apq.merge(other);
	        System.out.println("Merged. New size: " + apq.size());
	        System.out.println("Top after merge (min): " + apq.top());

	        System.out.println("\n=== FINAL DUMP ===");
	        for (int i = 1; i <= apq.size(); i++) {
	            System.out.println(i + "th: " + apq.peekAt(i));
	        }

	        System.out.println("\n=== SUCCESSFULLY TESTED 20+ CASES ===");
	    }
	}



