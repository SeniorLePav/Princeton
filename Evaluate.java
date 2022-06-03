public class Evaluate {
    public static void main(String[] args) { 
        Stack<String> ops  = new Stack<String>();
        Stack<Double> values = new Stack<Double>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("("));
            else if (s.equals("+")){
				ops.push(s)}
            else if (s.equals("-")){
				ops.push(s)}
            else if (s.equals("*")){
				ops.push(s)}
            else if (s.equals("/")){
				ops.push(s)}
            else if (s.equals("sqrt")){
				ops.push(s)}
            else if (s.equals(")")) {
                String op = ops.pop();
                double v = values.pop();
                if      (op.equals("+"))    v = values.pop() + v;
                else if (op.equals("-"))    v = values.pop() - v;
                else if (op.equals("*"))    v = values.pop() * v;
                else if (op.equals("/"))    v = values.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                values.push(v);
            }
            else values.push(Double.parseDouble(s));
        }
        StdOut.println(values.pop());
    }
}
