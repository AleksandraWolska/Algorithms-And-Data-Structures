import java.util.Comparator;

class Vertex implements Comparator<Vertex> {
    public int miasto;
    public int koszt;
    public Vertex() { } //empty constructor

    public Vertex(int miasto, int koszt) {
        this.miasto = miasto;
        this.koszt = koszt;
    }
    @Override
    public int compare(Vertex v1, Vertex v2)
    {
        if (v1.koszt < v2.koszt)
            return -1;
        if (v1.koszt > v2.koszt)
            return 1;
        return 0;
    }
}