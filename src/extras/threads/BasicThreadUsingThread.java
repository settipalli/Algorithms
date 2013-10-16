/*
 * The MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * ****************************************************************************
 */

package extras.threads;

import java.util.Date;

/**
 * Implements a basic thread by extending the Thread class based on the
 * documentation at {@link http://docs.oracle.com/javase/tutorial/essential/
 * concurrency/runthread.html}
 * 
 */

class CustomThread extends Thread {
    final int sleepInterval = 2000; // 2 seconds.


    @Override
    public void run() {
        while (true) {
            try {
                this.sleep(sleepInterval);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted at: " + new Date());
                e.printStackTrace();
                break;
            }
            System.out.println("Hello from thread at: " + new Date());
        }
    }
}


public class BasicThreadUsingThread {
    final static int sleepInterval = 2000;


    public static void main(String[] args) {
        Thread t = new CustomThread();
        System.out.println("[main]: Starting thread ...");
        t.start();
        System.out
                .println("[main]: Waiting for the thread to complete its cycle.");

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(sleepInterval);
            } catch (InterruptedException e) {
                System.out.println("[main]: Was interrupted at: " + new Date());
                e.printStackTrace();
            }
            System.out
                    .println("[main]: Waiting for the thread to complete its cycle at: "
                            + new Date());
        }

        System.out.println("[main]: About to interrupt the thread at: "
                + new Date());
        t.interrupt();
        System.out.println("[main]: Thread interrupted at: " + new Date());
        System.out.println("[main]: Waiting for the thread to complete.");
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out
                    .println("[main]: Was interrupted while waiting for the Thread to join: "
                            + new Date());
            e.printStackTrace();
        }
        System.out.println("[main]: Thread completed at: " + new Date());
    }
}
