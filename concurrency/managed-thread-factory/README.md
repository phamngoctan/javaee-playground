# ManagedThreadFactory approach for concurrency in java ee
Using ManagedThreadFactory which JAVA EE provided.  
ManagedThreadFactory factory;
Thread thread = factory.newThread(new MyTask(1));  
out.println("Starting thread");  
thread.start();