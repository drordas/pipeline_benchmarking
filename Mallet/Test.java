import cc.mallet.pipe.*;
import cc.mallet.types.*;
import java.util.List;
import java.util.Iterator;
import java.util.Arrays;

public class Test {
   public static final int NUM_TESTS=100000;

   public Test(){
      Instance instance=new Instance(new String("The data"),
                              new String("The target"),
                              new String("The name"),
                              new String("The source"));

      List<Instance> instanceList=Arrays.asList(instance);
      long start,end;

      //Step 1
      Pipe p=new NullPipe();
      System.gc();

      start=System.currentTimeMillis();
      for (int i=0;i<NUM_TESTS;i++){
          Iterator<Instance> it=p.newIteratorFrom(instanceList.iterator());
          while(it.hasNext())
              it.next();
      }
      end=System.currentTimeMillis();
      System.out.println("Time 1: "+ (end-start));

      
      //Step 2
      p=new SerialPipes( new Pipe[]{
         new NullPipe(),
         new NullPipe()
      });
      System.gc();

      start=System.currentTimeMillis();
      for (int i=0;i<NUM_TESTS;i++){
          Iterator<Instance> it=p.newIteratorFrom(instanceList.iterator());
          while(it.hasNext())
              it.next();
      }
      end=System.currentTimeMillis();
      System.out.println("Time 2: "+ (end-start));

      //Step 3
      //Non é posible en Paralelo
      System.out.println("Time 3: unssupported");

   }



   public static void main(String args[]){
      new Test();
   }
}

class NullPipe extends Pipe {
    public Instance pipe(Instance carrier){
        return carrier;
    }
}



