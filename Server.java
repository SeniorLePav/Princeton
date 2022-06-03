public class Server {
    private Queue<String> list = new Queue<String>();      
    private int load;                                      

    
    public void add(String user) {
        list.enqueue(user);
        load++;
    }

    
    public String toString() {
        
        String s = "";
        for (String user : list)
            s += user + " ";
        return s;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
     
        Server[] servers = new Server[n];
        for (int i = 0; i < n; i++)
            servers[i] = new Server();

       
        for (int j = 0; j < n; j++) {
            String user = "user" + j;
            int i = StdRandom.uniform(n);
            servers[i].add(user);
        }

        
        for (int i = 0; i < n; i++)
            StdOut.println(i + ": " + servers[i]);
    }
}