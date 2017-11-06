public class Employee {
    private String name;
    private String job;
    private int salary;


    public Employee(String line) {
        String[] inp = line.split(" ");
        name = inp[0];
        job = inp[1];
        salary = Integer.parseInt(inp[2]);
    }


    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getSalary() {
        return salary;
    }
}
