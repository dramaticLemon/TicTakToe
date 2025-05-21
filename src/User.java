public class User {
    char markPlay;
    boolean priorityPlay;

    public User(char markPlay, boolean priorityPlay) {
        this.markPlay = markPlay;
        this.priorityPlay = priorityPlay;
    }

    @Override
    public String toString () {
        return "User{" +
                "markPlay=" + markPlay +
                ", priorityPlay=" + priorityPlay +
                '}';
    }
}
