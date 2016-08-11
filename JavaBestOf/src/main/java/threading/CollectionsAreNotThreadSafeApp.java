package threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Demonstrates that accessing Collection objects from several threads can be dangerous.
 * Most Collections are not thread safe because thread safety incurs performance costs.
 * <p>
 * This program shares an ArrayList among two threads, ArrayLists in Java are not thread safe.
 *
 * One thread adds elements to the list, the other thread removes the equal amount of elements.
 * After both Threads terminate the number of elements in the List should be the same as before.
 * This is of course not the case (most of the time).
 *
 * If you want to access a Collection object from several Threads you might want to use
 * one of the thread-safe Collection wrappers, e.g. Collections.synchronizedList(...) or
 * a thread safe implementation of the Collection (e.g. {@link ConcurrentHashMap}).
 *
 * Be careful that even if you use a thread-safe Collection that only means
 * that calling a method of the Collection will not cause a race condition (i.e. it is atomic on the Collection).
 * Successive calls to the Collection object that rely on each other might still cause wrong results.
 *
 * For example if several Threads execute a piece of code like this
 *
 * if (!list.empty()) {
 *     list.remove(element);
 * }
 *
 * you probably want to synchronize the whole block, otherwise the Collection could have
 * been changed by another Thread between the call to empty() and the call to remove()
 * which is probably not what you wanted.
 */
public class CollectionsAreNotThreadSafeApp {

    // Tweak this constant if the program takes too long to run
    private static final int COLLECTION_SIZE = 100_000;

    private static final int INCREMENTS = COLLECTION_SIZE;
    private static final int DECREMENTS = INCREMENTS;

    private static final int FIRST_IDX = 0;
    private static final Object DUMMY_LIST_ELEMENT = new Object();

    // In order to get the collection thread safe you can use a wrapper:
//     private static List<Object> sharedState = Collections.synchronizedList(new ArrayList<>(COLLECTION_SIZE + INCREMENTS));
    private static List<Object> sharedState = new ArrayList<>(COLLECTION_SIZE + INCREMENTS);

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        for (int stackFrame = 0; stackFrame < COLLECTION_SIZE; ++stackFrame) {
            sharedState.add(DUMMY_LIST_ELEMENT);
        }
        System.out.println("Size before threads start: " + sharedState.size());

        Thread thread1 = unsafeAddingThread();
        Thread thread2 = unsafeRemovingThread();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Actual: " + sharedState.size() + " Expected: " + COLLECTION_SIZE);
    }

    private static Thread unsafeAddingThread() {
        return new Thread(() -> {
            for (int i = 0; i < INCREMENTS; ++i) {
                sharedState.add(DUMMY_LIST_ELEMENT);
            }
        });
    }

    private static Thread unsafeRemovingThread() {
        return new Thread(() -> {
            for (int i = 0; i < DECREMENTS; ++i) {
                sharedState.remove(FIRST_IDX);
            }
        });
    }
}
