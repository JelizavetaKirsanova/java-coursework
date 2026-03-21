package inheritance.stack;

import java.util.Stack;

public class LoggingStack extends Stack<Integer> {

    @Override
    public Integer push(Integer item) {
        System.out.println("pushing" + item);


        return super.push(item);
    }


    @Override
    public synchronized Integer pop() {
        Integer popped = super.pop();

        System.out.println("popping" + popped);



        return popped;
    }


    public void pushAll(int ... elements){
        for (int element : elements) {
            push(element);

        }
    }
}
