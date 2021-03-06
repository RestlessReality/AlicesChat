package threading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * When do I need threading?
 *
 * When you don't want to block other processes, running parallel to you. Consider:
 * - a UI which should be always working
 * - executing time-consuming tasks, like writing to disk
 * - being able to process several requests at the same time,
 *   like several clients trying to connect to a server
 */

/**
 * Demonstrates that accessing Collection objects from several threads can be dangerous.
 * Most Collections are not thread safe. (Because thread safety incurs performance costs)
 * <p>
 * If you want to access a Collection object from several Threads you should use
 * one
 * - of the thread-safe Collection wrappers, e.g. Collections.synchronizedList(...) or
 * - a thread safe implementation of the Collection (e.g. {@link ConcurrentHashMap}).
 *
 * Be careful that even if you use a thread-safe Collection, that only means
 * that calling a method of the Collection will not cause a race condition, i.e. it is atomic on the Collection.
 * Successive calls to the Collection object that rely on each other, might still cause wrong results.
 * For example if several Threads execute a piece of code like this:
 * if (!list.empty()) {
 *     list.remove(element);
 * }
 * you should synchronize the whole block, otherwise the Collection could have
 * been changed by another Thread between the call to empty() and the call to remove().
 */

public class CollectionsAreNotThreadSafeApp {

    private static final int COLLECTION_SIZE = 100_000;

    private static final int INCREMENTS = COLLECTION_SIZE;
    private static final int DECREMENTS = INCREMENTS;

    private static final int FIRST_IDX = 0;
    private static final Object DUMMY_LIST_ELEMENT = new Object();

    // In order to get the collection thread safe you can use a wrapper:
//     private static List<Object> sharedState = Collections.synchronizedList(new ArrayList<>(COLLECTION_SIZE + INCREMENTS));
    private static List<Object> sharedState = new ArrayList<>(COLLECTION_SIZE + INCREMENTS);

    public static void main(String[] args) {
        for (int i = 0; i < COLLECTION_SIZE; ++i) {
            sharedState.add(DUMMY_LIST_ELEMENT);
        }
        System.out.println("Size before threads start: " + sharedState.size());

        Thread addingThread = unsafeAddingThread();
        Thread removingThread = unsafeRemovingThread();

        addingThread.start();
        removingThread.start();

        try {
            addingThread.join();
            removingThread.join();
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
