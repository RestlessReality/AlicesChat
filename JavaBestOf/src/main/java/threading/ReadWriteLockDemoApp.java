package com.qaware.mentoring.threading;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * Example how to use a {@link ReadWriteLock}.
 * It consists of a ReadLock and a WriteLock.
 * The idea is that it is safe if multiple Threads hold the ReadLock at the same time.
 * However as soon as the WriteLock is acquired, Threads which want to acquire a ReadLock have to wait
 * until the WriteLock is released.
 * <p>
 * Note the typical construct when using Lock classes:
 * <p>
 * Lock lock = new Lock();
 * lock.lock();
 * try {
 * // Do something
 * } finally {
 * lock.unlock();   // Will be called even if an exception is thrown in the try block.
 * }
 * <p>
 * The important part is the finally {} block which ensures that the Lock is released in any case.
 * <p>
 * There are several specialized Lock classes apart from ReadWriteLock, e.g. {@link StampedLock}
 * or {@link java.util.concurrent.locks.ReentrantLock}.
 */
public class ReadWriteLockDemoApp {

    private static int sharedResource = 0;

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                System.out.println("Writing thread acquired lock. Writing takes some time. Reading threads have to wait.");
                TimeUnit.SECONDS.sleep(3);
                sharedResource = 42;
            } catch (InterruptedException e) {
                System.err.println("Interrupted writer thread");
            } finally {
                System.out.println("Writing thread releases lock.");
                lock.writeLock().unlock();
            }
        });

        Runnable readTask = () -> {
            System.out.println("Reading thread trying to get lock...");
            lock.readLock().lock();
            try {
                System.out.println("Reading thread acquired lock!");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Resource value is: " + sharedResource);
            } catch (InterruptedException e) {
                System.err.println("Interrupted writer thread");
            } finally {
                lock.readLock().unlock();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Termination interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("Killing non-finished tasks.");
            }
            executor.shutdownNow();
        }
    }
}
