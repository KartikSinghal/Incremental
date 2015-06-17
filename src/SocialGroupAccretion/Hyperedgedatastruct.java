package SocialGroupAccretion;

public class Hyperedgedatastruct{
    int count;
    int vertices;
    int index;
    public Hyperedgedatastruct(int index){
     this.count=0;
     this.vertices=0;
     this.index=index;
    }
    public int getCount(){return this.count;}
    public int getVertices(){return this.vertices;}
    public int getIndex(){return this.index;}
    
    public void setCount(int count){this.count=count;}
    public void setVertices(int vertices){this.vertices=vertices;}
    public void setIndex(int index){this.index=index;}
}