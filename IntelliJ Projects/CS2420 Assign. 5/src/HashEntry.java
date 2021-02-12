public class HashEntry
{
    public String word = "";   // the element
    public boolean isActive;  // false if marked deleted

    public HashEntry( String word )
    {
        this( word, true );
    }

    public HashEntry( String word, boolean i )
    {
        this.word  = word;
        isActive = i;
    }

    public void setWord(String word) {
        this.word = word;
    }
}