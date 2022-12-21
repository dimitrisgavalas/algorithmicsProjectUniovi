package algstudent.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.lang.Object;
public class TrominoForkJoin {

	/*
	 * Hello sir .
	 * i just copied an example and tried to work on that.
	 * unfortnnatly wihtout success
	 * 
	 * if work is small enough
	 * do perform the work directly
	 * else do break up the work into smaller components 
	 * do perform the resolution of components in parallel 
	 * do wait for results*/
	
	public static void main(String[] args) {
		ForkJoin pool = new ForkJoin();//task pool with the level
		double[] data = new double[8];//tiles to work with
		
		//innitialize numbersthat alternate 
		for(int i=0; i<data.length; i++) {
			data[i] = (double)(((i%2)== 0)? i : -i);
		}
		
		//
		RecursiceTaskSum task = new RecursiveTaskSum(data,0,data.length);
		
		long t1 = System.currentTimeMillis();//measure time
		double result = pool.invoke(task);//invoke method forks the task and waits for the result
		long t2 = System.currentTimeMillis();//measure time
		
		System.out.println("Elapse Time: " + (t2-t1) + " ms");
		System.out.println("Result: " + result);


	}
	
	
}


class NewTask extends RecursiveAction
{
	private long Load = 0;
	
	public NewTask(long Load) { this.Load = Load; }

	protected void compute()
	{
		// fork tasks into smaller subtasks
		List<NewTask> subtasks = new ArrayList<NewTask>();
		subtasks.addAll(createSubtasks());
		
		for (RecursiveAction subtask : subtasks) {
			subtask.fork();
		}
	}
	
	// function to create and add subtasks
	private List<NewTask> createSubtasks()
	{
		// create subtasks
		List<NewTask> subtasks = new ArrayList<NewTask>();
		NewTask subtask1 = new NewTask(this.Load / 2);
		NewTask subtask2 = new NewTask(this.Load / 2);
		NewTask subtask3 = new NewTask(this.Load / 2);
		
		// to add the subtasks
		subtasks.add(subtask1);
		subtasks.add(subtask2);
		subtasks.add(subtask3);
		
		return subtasks;
	}
}
class JavaForkJoingetActivethreadcountExample1 {
	public static void main(final String[] arguments)
		throws InterruptedException
	{
		// get no. of available core available
		int proc = Runtime.getRuntime().availableProcessors();
		
		System.out.println("Number of available core in the processor is: "
			+ proc);
			
		// get no. of threads active
		ForkJoinPool Pool = ForkJoinPool.commonPool();
		
		System.out.println("Number of active thread before invoking: "
			+ Pool.getActiveThreadCount());
			
		NewTask t = new NewTask(400);
		
		Pool.invoke(t);
		
		System.out.println("Number of active thread after invoking: "
			+ Pool.getActiveThreadCount());
		System.out.println("Common Pool Size is: "
						+ Pool.getPoolSize());
	}
}
