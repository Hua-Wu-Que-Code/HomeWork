package 实验代码;

public class Calculator {

    private static int result; //静态变量，用于存储运行结果

    public void add(int n){
        result = result + n;
    }
    public void subtract(int n){
        result = result - 1; //减n错写成减1了
    }
    public void multiply(int n){

        result = result + n; //*号写成+号

    }
    public void divide(int n,int m){

        if(n!=0 && m > 10)
        {m = m / n;}
        else
        {
            System.out.println("除数不能为0");
            result=0;
        }
        result =n;   // 错把m写成n
    }
    public void square(int n){
        result = n * n;
    }
    public void squareRoot(int n){
        for(;;);
    }
    public void clear(){ //将结果清零
        result = 0;
    }
    public int getResult(){
        return result;
    }
}